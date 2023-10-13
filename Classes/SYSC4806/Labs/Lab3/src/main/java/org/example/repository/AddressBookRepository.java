package org.example.repository;

import org.example.model.AddressBook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    Optional<AddressBook> findById(Long aLong);
}