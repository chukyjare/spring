package com.example.demo;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablonController {

  // Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
  // la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la
  // base de datos de la aplicación

  @Autowired
  private AnunciosRepository repository;

  @PostConstruct
  public void init() {
    repository.save(new Anuncio("Pepe", "Hola escarcha", "Saludo matinal"));
    repository.save(new Anuncio("Juan", "Hola soleada", "Saludo vespertino"));
  }

  @RequestMapping("/")
  public String tablon(Model model) {

    model.addAttribute("anuncios", repository.findAll());

    return "tablon";
  }

  @RequestMapping("/anuncio/nuevo")
  public String nuevoAnuncio(Model model, Anuncio anuncio) {

    repository.save(anuncio);

    return "anuncio_guardado";

  }

  @RequestMapping("/anuncio/{id}")
  public String verAnuncio(Model model, @PathVariable long id) {

    Optional<Anuncio> anunciox = repository.findById(id);

    System.out.println("anuncio:" + anunciox.get().getAsunto());
    Anuncio anuncio = new Anuncio(anunciox.get().getId(), anunciox.get().getNombre(), anunciox.get().getAsunto(),
        anunciox.get().getComentario());
    model.addAttribute("anuncio", anuncio);

    return "ver_anuncio";
  }

  @RequestMapping("/anuncio/eliminar/{id}")
  public String eliminarAnuncio(Model model, @PathVariable long id) {

    repository.deleteById(id);

    return "anuncio_eliminado";
  }

}
