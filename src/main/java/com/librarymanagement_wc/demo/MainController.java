package com.librarymanagement_wc.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


//-----------------------


    @RequestMapping(value="/borrow", method=RequestMethod.GET)
    public String handlePost(@RequestParam(defaultValue = "return") String action ){

        if( action.equals("borrow") ){
            System.out.println("Book is checked out");
        }
        else if( action.equals("return") ){
            System.out.println("Book is not checked out");
        }
        return "borrow";
    }


//
//    @RequestMapping(value="/borrow", method=RequestMethod.GET)
//    public String handlePost(@RequestParam(defaultValue = "return") @ModelAttribute Library library, BindingResult result ){
//        // String borrowed = param.getParameter("borrow");
//        if(library.borrowed=="borrow") {
//            System.out.println("Book is checked out");
//        }
//        else {
//            System.out.println("Book is not checked out");
//        }
//        return "Library.get(id)";
//



//    @RequestMapping(params = "return", method = RequestMethod.POST)
//    public String saveUser(HttpServletRequest request, @ModelAttribute Library library, BindingResult result) {
//        // validate your result
//        // if no errors, save it and redirect to successView.
//        System.out.println("Book is checked out");
//        return "";
//    }
//
//

//    @RequestMapping(value = "/jbcLibrary.borrowed/{id}", method = RequestMethod.GET)
//    public String status(@RequestParam(defaultValue = "false") @ModelAttribute("library") Library library) {
//        //String input = library.getBorrowed();
//        String borrowed = param.getParameter("borrow");
//        if(library.borrowed=="borrow"){
//            System.out.println("Book is checked out");
//        }
//        else
//            System.out.println("Book is not checked out");
//
//        return "";
//    }





//
//    @RequestMapping("/checkbox-process")
//    //@RequestMapping(value = "/jbcLibrary.borrowed/{id}", method=RequestMethod.GET)
//    public String checked(@RequestParam(defaultValue = "false") @ModelAttribute("library")  Library library){
//        if (library.isBorrowed()== true) {
//            System.out.println("Book is checked out");
//
//        }
//
//        else
//            System.out.println("Book is not checked out");
//
//        return "";








}
