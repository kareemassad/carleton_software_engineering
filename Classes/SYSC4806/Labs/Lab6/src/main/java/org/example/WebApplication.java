package org.example;

import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.example.repository.AddressBookRepository;
import org.example.repository.BuddyInfoRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;

@SpringBootApplication
public class WebApplication {

    private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository){
        return (args -> {
            try{
            BuddyInfo b1 = new BuddyInfo("Kareem", "1234567890");
            BuddyInfo b2 = new BuddyInfo("Quatey", "696969");

            AddressBook addressBook = new AddressBook();
            addressBook.addBuddyInfo(b1);
            addressBook.addBuddyInfo(b2);

            addressBookRepository.save(addressBook);

            log.info("Buddies found with findAll():");
            log.info("---------------------");
            for (BuddyInfo buddyInfo: buddyInfoRepository.findAll()){
                log.info(buddyInfo.toString());
            }
            log.info("");
            } catch (DataAccessException e) {
                log.error("Data access error ", e);
            }
        });
    }

}
