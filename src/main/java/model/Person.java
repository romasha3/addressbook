package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private SimpleStringProperty pip = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    private String oldPip;
    private String oldPhone;

    public Person(String pip, String phone) {
        this.pip = new SimpleStringProperty(pip);
        this.phone = new SimpleStringProperty(phone);
        this.oldPip = pip;
        this.oldPhone = phone;
    }

    public String getPip() {
        return pip.get();
    }

    public void setPip(String pip) {
        this.pip.set(pip);
    }

    public StringProperty pipProperty() {
        return pip;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getOldPip() {
        return oldPip;
    }

    public void setOldPip(String oldPip) {
        this.oldPip = oldPip;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pip=" + pip.get() +
                ", phone=" + phone.get() +
                ", oldPip=" + oldPip +
                ", oldPhone=" + oldPhone +
                '}';
    }
}
