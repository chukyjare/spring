package com.example.demo.tablonanuncios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TablonController {

  private List<Anuncio> anuncios = new ArrayList<>();

  @Autowired
  private Usuario usuario;

  public TablonController() {
    anuncios.add(new Anuncio("Pepe", "Hola caracola", "Saludo matutino"));
    anuncios.add(new Anuncio("Juan", "Vendo moto", "5500€"));
  }

  @RequestMapping("/")
  public String tablon(Model model, HttpSession session) {

    model.addAttribute("anuncios", anuncios);
    model.addAttribute("bienvenida", session.isNew());

    return "tablon";
  }

  @RequestMapping("/anuncio/nuevo")
  public String nuevoAnuncio(Model model, Anuncio anuncio) {

    anuncios.add(anuncio);
    usuario.setNombre(anuncio.getNombre());
    usuario.incAnuncios();

    return "anuncio_guardado";

  }

  @RequestMapping("/anuncio/{num}")
  public String verAnuncio(Model model, @PathVariable int num) {

    Anuncio anuncio = anuncios.get(num - 1);

    model.addAttribute("anuncio", anuncio);

    return "ver_anuncio";
  }

  @RequestMapping("/anuncio/nuevo_form")
  public String nuevoAnuncioForm(Model model) {

    model.addAttribute("nombre", usuario.getNombre());
    model.addAttribute("num_anuncios", usuario.getNumAnuncios());

    return "nuevo_anuncio";
  }

}