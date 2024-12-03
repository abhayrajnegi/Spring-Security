package com.eazybytes.springsection1.Repository;

import com.eazybytes.springsection1.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}