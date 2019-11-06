package AddressBookSerialized.Server;

import java.io.Serializable;

public class Person implements Serializable {
    String name;
    String birthday;
    String phoneNumber;
    String address;

    public Person(String name, String birthday, String phoneNumber, String address) {
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Namn: " + getName()
                + " Adress: " + getAddress()
                + " Telefonnummer: " + getPhoneNumber()
                + " Födelsedatum: " + getBirthday();
    }

}
