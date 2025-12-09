package org.example.addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.sql.SQLException;

public class ColectionAddressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private PersonDAO personDAO = new PersonDAO();

    public ColectionAddressBook() {
        personList.addAll(personDAO.getPersons());
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void testData() {
        personList.add(new Person("Романія", "12345"));
        personList.add(new Person("Оксана", "12346"));
        personList.add(new Person("Марія", "12347"));
    }

    @Override
    public void add(Person person) {
        personList.add(person);
        try {
            addPersonToBD(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Person person) {
        personList.remove(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
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
