package io.fsbano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.fsbano.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
