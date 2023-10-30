package org.example.controller;

import org.example.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.example.model.AddressBook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @RequestMapping("/")
    public @ResponseBody String greeting(){
        return "Hello, World";
    }

    @GetMapping("/addressBooks/{id}/view")
    public String viewAddressBook(@PathVariable Long id, Model model){
        AddressBook addressBook = addressBookRepository.findById(id).orElse(null);
        if (addressBook != null){
            model.addAttribute("buddies", addressBook.getBuddies());
        }
        return "addressbook";
    }

}
