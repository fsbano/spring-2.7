package io.fsbano.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import io.fsbano.entity.Contact;
import io.fsbano.repository.ContactRepository;

@RestController
@Tag(name = "contact", description = "the Contact API")
@RequestMapping("/api")
public class ContactController {
    
    @Autowired
    private ContactRepository _contactRepository;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public List<Contact> Get() {
        return _contactRepository.findAll();
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contact> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Contact> contact = _contactRepository.findById(id);
        if(contact.isPresent())
            return new ResponseEntity<Contact>(contact.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contact", method =  RequestMethod.POST)
    public Contact Post(@Valid @RequestBody Contact contact)
    {
        return _contactRepository.save(contact);
    }

    @RequestMapping(value = "/contact/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Contact> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Contact newContact)
    {
        Optional<Contact> oldContact = _contactRepository.findById(id);
        if(oldContact.isPresent()){
            Contact contact = oldContact.get();
            contact.setName(newContact.getName());
            contact.setEmail(newContact.getEmail());
            _contactRepository.save(contact);
            return new ResponseEntity<Contact>(contact, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Contact> contact = _contactRepository.findById(id);
        if(contact.isPresent()){
            _contactRepository.delete(contact.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}