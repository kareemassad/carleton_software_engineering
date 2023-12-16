package org.example.controller;

import org.example.repository.AddressBookRepository;
import org.example.repository.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.springframework.http.ResponseEntity;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/addressBooks/{id}")
    public @ResponseBody ResponseEntity<AddressBook> getAddressBook(@PathVariable Long id) {
        AddressBook addressBook = addressBookRepository.findById(id).orElse(null);
        if (addressBook != null) {
            return ResponseEntity.ok(addressBook);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/addressBooks/{id}/view")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        AddressBook addressBook = addressBookRepository.findById(id).orElse(null);
        if (addressBook != null) {
            model.addAttribute("addressBook", addressBook);
            model.addAttribute("buddies", addressBook.getBuddies());
            return "addressbook";
        }
        model.addAttribute("error", "Address book not found");
        return "error";  // You'll need to create an error.html template to display this message.
    }

    @PostMapping("/addressBooks/{addressBookId}/addBuddy")
    public String addBuddy(@PathVariable Long addressBookId, @ModelAttribute BuddyInfo buddyInfo) {
        AddressBook addressBook = addressBookRepository.findById(addressBookId).orElse(null);
        if (addressBook != null) {
            buddyInfo = buddyInfoRepository.save(buddyInfo);  // Save buddy info to get an ID
            addressBook.addBuddyInfo(buddyInfo);
            addressBookRepository.save(addressBook);  // Save address book with the new buddy info
            return "redirect:/addressBooks/" + addressBookId + "/view";
        }
        return "error";
    }

    @PostMapping("/api/addressBooks/{addressBookId}/addBuddy")
    public @ResponseBody ResponseEntity<BuddyInfo> addBuddyApi(@PathVariable Long addressBookId, @RequestBody BuddyInfo buddyInfo) {
        AddressBook addressBook = addressBookRepository.findById(addressBookId).orElse(null);
        if (addressBook != null) {
            buddyInfo = buddyInfoRepository.save(buddyInfo);  // Save buddy info to get an ID
            addressBook.addBuddyInfo(buddyInfo);
            addressBookRepository.save(addressBook);  // Save address book with the new buddy info
            return ResponseEntity.ok(buddyInfo);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/addressBooks/{addressBookId}/addBuddy")
    public String showAddBuddyForm(@PathVariable long addressBookId, Model model) {
        model.addAttribute("addressBookId", addressBookId);
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "addBuddy";
    }

    @PostMapping("/createAddressBook")
    public String createAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return "redirect:/addressBooks/" + addressBook.getId() + "/view";
    }

    @PostMapping("/api/createAddressBook")
    @ResponseBody
    public ResponseEntity<AddressBook> createAddressBookApi() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return ResponseEntity.ok(addressBook);
    }

    @GetMapping("/createAddressBook")
    public String showCreateAddressBookForm(Model model) {
        return "createAddressBook";
    }

    @GetMapping("/api/addressBooks/{id}/view")
    @ResponseBody
    public ResponseEntity<AddressBook> viewAddressBookJson(@PathVariable Long id) {
        AddressBook addressBook = addressBookRepository.findById(id).orElse(null);
        if (addressBook != null) {
            return ResponseEntity.ok(addressBook);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping("/spa")
    public String spaEntryPoint() {
        return "index";
    }
}

