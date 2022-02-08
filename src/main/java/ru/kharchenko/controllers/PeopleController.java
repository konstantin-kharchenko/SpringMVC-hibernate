package ru.kharchenko.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharchenko.models.Person;
import ru.kharchenko.service.Service;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final Service service;

    @Autowired
    public PeopleController(Service service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people",service.getPersonDAO().readAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person=service.getPersonDAO().read(id);
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "people/new";
        service.getPersonDAO().create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", service.getPersonDAO().read(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person) {
        service.getPersonDAO().update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("person") Person person/*@PathVariable("id") int id*/) {
        service.getPersonDAO().delete(person);
        return "redirect:/people";
    }
}

