package org.example.repository;

import org.example.model.AddressBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    Optional<AddressBook> findById(Long aLong);
}