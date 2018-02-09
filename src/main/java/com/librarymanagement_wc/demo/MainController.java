package com.librarymanagement_wc.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    LibraryRepository libraryRepository;

    //Index page
    @RequestMapping("/")
    public String index(){
        return "index";
    }


    //List Books page
    @RequestMapping("/list")
    public String listBooks(Model model){
        model.addAttribute("libraries", libraryRepository.findAll());
        return "list";
    }


    //Add Books page
    @GetMapping("/add")
    public String addBooksForm(Model model){
        model.addAttribute("library", new Library());
        return "bookform";
    }



    //Process New Book
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("library") Library library, BindingResult result) {
        if (result.hasErrors()) {
            return "bookform";
        }
        libraryRepository.save(library);
        return "redirect:/";
    }


    //View Details of Book
    @RequestMapping("/detail/{id}")
    public String showBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("library", libraryRepository.findOne(id));
        return "showdetails";
    }



    //Update of Book
    @RequestMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("library", libraryRepository.findOne(id));
        return "bookform";
    }


    //Delete Book
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        libraryRepository.delete(id);
        return "redirect:/";
    }













}
