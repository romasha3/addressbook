package org.example.addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.sql.SQLException;

public class ColectionAddressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();

    private ObservableList<Person> backupList = FXCollections.observableArrayList();

    private PersonDAO personDAO = new PersonDAO();

    public ColectionAddressBook() {
        personList.addAll(personDAO.getPersons());

        backupList.addAll(personList);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public ObservableList<Person> getBackupList() {
        return backupList;
    }

    public void fillTestData() {
        if (personList.isEmpty()) {
            Person person1 = new Person("Романія", "12345");
            Person person2 = new Person("Оксана", "12346");
            Person person3 = new Person("Марія", "12347");

            personList.addAll(person1, person2, person3);

            backupList.addAll(person1, person2, person3);
        }
    }

    @Override
    public void add(Person person) {
        personList.add(person);
        backupList.add(person);
        try {
            addPersonToBD(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Person person) {
        personList.remove(person);
        backupList.remove(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
        backupList.remove(person);
        deletePersonFromBD(person);
    }

    public void update(Person person) {
        new PersonDAO().updatePerson(person);
    }

    public void addPersonToBD(Person person) throws SQLException {
        personDAO.savePerson(person);
    }

    public void deletePersonFromBD(Person person) {
        personDAO.deletePerson(person);
    }

}