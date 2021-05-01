package com.ysoberon.homework.controladores;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Respuesta;
import com.ysoberon.homework.modelo.RespuestasForm;
import com.ysoberon.homework.modelo.Usuario;
import com.ysoberon.homework.servicios.IAlumnoServicio;
import com.ysoberon.homework.servicios.ICategoriaServicio;
import com.ysoberon.homework.servicios.ICursoServicio;
import com.ysoberon.homework.servicios.IEjercicioServicio;
import com.ysoberon.homework.servicios.IPlantillaServicio;
import com.ysoberon.homework.servicios.IRespuestaServicio;
import com.ysoberon.homework.servicios.ITipoServicio;
import com.ysoberon.homework.servicios.IUsuarioServicio;
import com.ysoberon.homework.servicios.RespuestaServicio;
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
	private ITipoServicio tipoServicio;

	@Autowired
	private ICursoServicio cursoServicio;

	@Autowired
	private IPlantillaServicio plantillaServicio;

	@Autowired
	private IEjercicioServicio ejercicioServicio;

	@Autowired
	private IRespuestaServicio respuestaServicio;

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
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);

		return "redirect:/login";
	}

	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * 
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {

		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto
		// usuario.
		String username = authentication.getName();

		for (GrantedAuthority rol : authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}

		if (session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioServicio.buscarPorEmail(username);
			// System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}

		return "redirect:/home";
	}

	@GetMapping("/home")
	public String mostrarPagPrincipal(Model model, Authentication auth) {
		Usuario usuario = new Usuario();
		// usuario.setId_usuario(1);
		String email = auth.getName();
		usuario = usuarioServicio.buscarPorEmail(email);
		List<Alumno> lista = alumnoServicio.findAlumnosByUsuario(usuario);
		model.addAttribute("alumnos", lista);

		// Plantillas
		List<Plantilla> listaPlantillas = plantillaServicio.findPlantillasByUsuario(usuario);
		model.addAttribute("plantillas", listaPlantillas);

		return "home";

	}

	@GetMapping("/nuevoAlumno")
	public String crearNuevoAlumno(Alumno alumno) {

		return "formNuevoAlumno";

	}

	@GetMapping("/nuevaPlantilla")
	public String crearNuevaPlantilla(Plantilla Plantilla) {

		return "formNuevaPlantilla";

	}

	@GetMapping("/registro")
	public String mostrarformRegistro(Usuario usuario) {

		return "formRegistro";

	}

	@PostMapping("/guardarAlumno")
	public String insertarAlumno(Alumno alumno, BindingResult result, RedirectAttributes attributes, Model modelo,
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
		Usuario usuario = (Usuario) modelo.getAttribute("usuario");
		alumno.setUsuario(usuario);
		alumnoServicio.guardarAlumno(alumno);
		attributes.addFlashAttribute("msg", "Alumno guardado");

		return "redirect:/home";

	}

	@PostMapping("/guardarAlumnoPlantilla")
	public String insertarAlumnoPlantilla(Alumno alumno, BindingResult result, RedirectAttributes attributes,
			Model modelo) {

		if (result.hasErrors()) {
			return "formNuevoAlumno";
		}

		Alumno alumno1 = alumnoServicio.findById(alumno.getId_alumno());
		alumno1.agregarPlantilla(alumno.getPlantilla());
		alumnoServicio.guardarAlumno(alumno1);
		attributes.addFlashAttribute("msg", "Alumno guardado");

		return "redirect:/home";
		// return "formAgregarEjercicios";

	}

	@PostMapping("/guardarEjercicio")
	public String insertarEjercicio(Ejercicio ejercicio, BindingResult result, RedirectAttributes attributes,
			Model modelo, HttpSession session) {

		if (result.hasErrors()) {
			return "formNuevoAlumno";
		}

		Plantilla plantilla = (Plantilla) session.getAttribute("plantilla");
		ejercicio.setPlantilla(plantilla);
		ejercicioServicio.guardarEjercicio(ejercicio);
		session.setAttribute("ejercicio", ejercicio);
		 
		attributes.addFlashAttribute("msg", "Ejercicio guardado");

		return "redirect:/home";

	}

	@PostMapping("/guardarRespuestas")
	public String insertarRespuestas(@ModelAttribute("respuestasForm") RespuestasForm respuestasForm,
			BindingResult result, RedirectAttributes attributes, Model modelo, HttpSession session) {
        Ejercicio ejercicio=(Ejercicio) session.getAttribute("ejercicio");
		for (Respuesta respuesta : respuestasForm.getRespuestas()) {
			respuesta.setEjercicio(ejercicio);
			respuestaServicio.guardarRespuesta(respuesta);
		}

		System.out.println(modelo);
		return "redirect:/home";

	}

	@PostMapping("/guardarPlantilla")
	public String insertarPlantilla(Plantilla plantilla, BindingResult result, Model modelo,
			RedirectAttributes attributes)

	{

		if (result.hasErrors()) {
			return "formNuevaPlantilla";
		}

		Usuario usuario = (Usuario) modelo.getAttribute("usuario");

		plantilla.setUsuario(usuario);
		plantillaServicio.guardar(plantilla);
		List<Plantilla> lista = plantillaServicio.findPlantillasByNombre(plantilla.getNombre());
		Integer plantillaCreada = lista.get(0).getId_plantilla();
		System.out.println(plantillaCreada + "plantillaCreada");
		// return "redirect:/home";
		return "formAgregarEjercicios";

	}

	@PostMapping("/guardarUsuario")
	public String insertarUsuario(Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
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

	@GetMapping("/eliminarPlantilla/{id}")
	public String eliminarPlantilla(@PathVariable("id") int id_plantilla, RedirectAttributes attributes) {
		plantillaServicio.eliminarPlantilla(id_plantilla);
		attributes.addFlashAttribute("msg", "Plantilla eliminada");
		return "redirect:/home";
	}
	
	@GetMapping("/eliminarPlantillaAlumno/{id}")
	public String eliminarPlantillaAlumno(@PathVariable("id") int id_plantilla, RedirectAttributes attributes,HttpSession session) {
		Alumno alumno=(Alumno) session.getAttribute("alumno");
		Plantilla plantilla=plantillaServicio.findById(id_plantilla);
		alumno.eliminarPlantilla(plantilla);
		attributes.addFlashAttribute("msg", "Plantilla eliminada");
		return "redirect:/home";
	}

	@GetMapping("/editarAlumno/{id}")
	public String editar(@PathVariable("id") int id_alumno, Model model) {
		Alumno alumno = alumnoServicio.findById(id_alumno);
		model.addAttribute("alumno", alumno);
		return "formNuevoAlumno";
	}

	@GetMapping("/ventanaAlumno/{id}")
	public String mostrarVentanaAlumno(@PathVariable("id") int id_alumno, Model model,HttpSession session) {
		Alumno alumno = alumnoServicio.findById(id_alumno);
		List<Plantilla> plantillas=  alumno.getPlantillas();
		model.addAttribute("alumno", alumno);
		session.setAttribute("alumno", alumno);
		model.addAttribute("plantillasAlumno", plantillas);
		
		return "ventanaAlumno";

	}

	@GetMapping("/editarPlantilla/{id}")
	public String editarPlantilla(@PathVariable("id") int id_plantilla, Model model, HttpSession session) {
		Plantilla plantilla = plantillaServicio.findById(id_plantilla);

		session.setAttribute("plantilla", plantilla);
		model.addAttribute("plantillaAEditar", plantilla);
		model.addAttribute("ejercicios", ejercicioServicio.findEjerciciosByPlantilla(plantilla));
		return "formAgregarEjercicios";
	}

	@ModelAttribute("respuestasForm")
	public RespuestasForm populatePojos(RespuestasForm respuestasParam) {
		// Don"t forget to initialize the pojos list or else it won"t work
		RespuestasForm respuestasForm = new RespuestasForm();
		if (respuestasParam.getRespuestas()!=null) {

			List<Respuesta> respuestas = new ArrayList<Respuesta>();
			for (int i = 0; i < 3; i++) {
				respuestas.add(respuestasParam.getRespuestas().get(i));
			}
			respuestasForm.setRespuestas(respuestas);

		}
		return respuestasForm;
	}

	/**
	 * Agregamos al Model la lista de Categorias: De esta forma nos evitamos
	 * agregarlos en los metodos crear y editar.
	 * 
	 * @return
	 */
	@ModelAttribute
	public void setGenericos(Model model, Authentication auth, HttpSession session) {
		model.addAttribute("categorias", categoriaServicio.buscarTodas());
		model.addAttribute("tipos", tipoServicio.buscarTodos());
		model.addAttribute("cursos", cursoServicio.buscarTodos());
		if (auth != null) {
			Usuario usuario = usuarioServicio.buscarPorEmail(auth.getName());
			model.addAttribute("usuario", usuario);

			Plantilla plantilla = (Plantilla) session.getAttribute("plantilla");
			//if (plantilla != null) {
				//model.addAttribute("ejercicios", ejercicioServicio.findEjerciciosByPlantilla(plantilla));
			//}
			model.addAttribute("alumnos", alumnoServicio.findAlumnosByUsuario(usuario));

			model.addAttribute("plantillas", plantillaServicio.findPlantillasByUsuario(usuario));
			model.addAttribute("ejercicio", new Ejercicio());
			model.addAttribute("respuesta", new Respuesta());
			model.addAttribute("respuestasForm", new RespuestasForm());

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
	public String encriptar(@PathVariable("texto") String texto) {

		return texto + " : " + passwordEncoder.encode(texto);

	}

}
