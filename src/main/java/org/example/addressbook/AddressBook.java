package org.example.addressbook;

import model.Person;

public interface AddressBook {
    void add(Person person);

    void remove(Person person);

    void delete(Person person);

}
