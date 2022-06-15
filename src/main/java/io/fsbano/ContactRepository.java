package io.fsbano;

import org.springframework.data.jpa.repository.JpaRepository;
import io.fsbano.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
