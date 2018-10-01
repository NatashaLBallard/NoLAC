package com.nolac.demo.repositories;

import com.nolac.demo.contact.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
//    void delete(long id);
}