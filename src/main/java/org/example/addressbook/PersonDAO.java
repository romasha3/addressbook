package org.example.addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {

    public ObservableList<Person> getPersons() {
        ObservableList<Person> personList = FXCollections.observableArrayList();
        String query = "SELECT pip, phone FROM addressbook";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Person person = new Person(resultSet.getString("pip"), resultSet.getString("phone"));
                person.setOldPip(person.getPip());
                person.setOldPhone(person.getPhone());
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public void savePerson(Person person) {
        String sql = "INSERT INTO addressbook (pip, phone) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, person.getPip());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.executeUpdate();

            person.setOldPip(person.getPip());
            person.setOldPhone(person.getPhone());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(Person person) {
        String sql = "DELETE FROM addressbook WHERE pip = ? AND phone = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, person.getPip());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        String sql = "UPDATE addressbook SET pip = ?, phone = ? WHERE pip = ? AND phone = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, person.getPip());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.setString(3, person.getOldPip());
            preparedStatement.setString(4, person.getOldPhone());

            preparedStatement.executeUpdate();

            person.setOldPip(person.getPip());
            person.setOldPhone(person.getPhone());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
