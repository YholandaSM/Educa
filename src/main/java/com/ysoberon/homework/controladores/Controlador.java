package com.ysoberon.homework.controladores;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Usuario;
import com.ysoberon.homework.servicios.IAlumnoServicio;
import com.ysoberon.homework.servicios.ICategoriaServicio;
import com.ysoberon.homework.servicios.ICursoServicio;
import com.ysoberon.homework.servicios.IUsuarioServicio;
import com.ysoberon.homework.util.Utils;

 

 

@Controller
public class Controlador {

	/*
	 * @Value("${educaapp.ruta.imagenes}") private String ruta;
	 */
 

	@Autowired
	private IAlumnoServicio alumnoServicio;

	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICategoriaServicio categoriaServicio;
	
	@Autowired
	private ICursoServicio cursoServicio;
	
 	@Autowired
 	private PasswordEncoder passwordEncoder;
	
	 

	// Ruta donde se guardarán las imágenes
	// @Value("${educapadres.ruta.imagenes}")
	// private String ruta;

	// La página de login es la página de inicio
	 
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String mostrarLogin(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler=
				new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null); 
		
		return "redirect:/login";
	}
	
	
	

	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = usuarioServicio.buscarPorEmail(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/home";
	}
	
 

	@GetMapping("/home")
	public String mostrarPagPrincipal(Model model, Authentication auth) {
		Usuario usuario = new Usuario();
	//	usuario.setId_usuario(1);
		String email=auth.getName();
		  usuario = usuarioServicio.buscarPorEmail(email);
		List<Alumno> lista = alumnoServicio.findAlumnosByUsuario(usuario);
		model.addAttribute("alumnos", lista);
		return "home";

	}

	 
	@GetMapping("/nuevoAlumno")
	public String crearNuevoAlumno(Alumno alumno) {

		return "formNuevoAlumno";

	}
	
	
	@GetMapping("/nuevaPlantilla")
	public String crearNuevaPlantilla(@ModelAttribute Plantilla Plantilla) {

		return "formNuevaPlantilla";

	}
	
	@PostMapping("/guardarPlantilla")
	public String guardarPlantilla(@ModelAttribute Plantilla plantilla, BindingResult result, Model model) {
	 return "";
	}
	
	@GetMapping("/registro")
	public String mostrarformRegistro(Usuario usuario) {

		return "formRegistro";
		
	}
	@PostMapping("/guardarAlumno")
	public String insertarAlumno(Alumno alumno, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		if (result.hasErrors()) {
			return "formNuevoAlumno";
		}

		if (!multiPart.isEmpty()) {
			String ruta = "c:/educa/img-alumnos/"; // Windows
			String nombreImagen = Utils.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				alumno.setImagen(nombreImagen);
			}
		}
		alumnoServicio.guardarAlumno(alumno);
		attributes.addFlashAttribute("msg", "Alumno guardado");

		return "redirect:/home";

	}
	
	@PostMapping("/guardarUsuario")
	public String insertarUsuario(Usuario usuario, BindingResult result, RedirectAttributes attributes )
			 {
		
		String pwdPlano=usuario.getPassword();
		String pwdEncriptado=passwordEncoder.encode(pwdPlano);
		usuario.setPassword(pwdEncriptado);
	 
		if (result.hasErrors()) {
			return "formRegistro";
		}

		usuario.setEstatus(1);
		usuarioServicio.guardar(usuario);
		attributes.addFlashAttribute("msg", "Usuario guardado");

		return "redirect:/home";

	}

	

	@GetMapping("/eliminarAlumno/{id}")
	public String eliminar(@PathVariable("id") int id_alumno, RedirectAttributes attributes) {
		alumnoServicio.eliminarAlumno(id_alumno);
		attributes.addFlashAttribute("msg", "Alumno eliminado");
		return "redirect:/home";
	}

	@GetMapping("/editarAlumno/{id}")
	public String editar(@PathVariable("id") int id_alumno, Model model) {
		Alumno alumno = alumnoServicio.findById(id_alumno);
		model.addAttribute("alumno", alumno);
		return "formNuevoAlumno";
	}
	
	
	
	/**
	 * Agregamos al Model la lista de Categorias: De esta forma nos evitamos agregarlos en los metodos
	 * crear y editar. 
	 * @return
	 */	
	@ModelAttribute
	public void setGenericos(Model model, Authentication auth){
		model.addAttribute("categorias", categoriaServicio.buscarTodas());	
		model.addAttribute("cursos", cursoServicio.buscarTodos());	
		if(auth!=null) {
		Usuario usuario = usuarioServicio.buscarPorEmail(auth.getName());
		
		
		model.addAttribute("alumnos", alumnoServicio.findAlumnosByUsuario(usuario));	
		}
	}

	/*
	 * Método que da formato a las fechas
	 */
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
	
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto ) {
		
		return texto +" : " + passwordEncoder.encode(texto);
		
	}

}
