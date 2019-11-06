package AddressBook.Server;

import java.util.LinkedList;
import java.util.List;

public class Database {
    Person person;
    Person Elias = new Person("Elias", "890116",
            "1234567890", "Hättkvarnsgatan 7");
    Person Atef = new Person("Atef", "890425",
            "3333333333", "Sovskrapan 12");
    Person Joakim = new Person("Joakim", "890429",
            "0722510114", "Fläderstigen 4");
    Person Nadja = new Person("Nadja", "880623",
            "0763101720","Sågargatan 17");
    Person Fazli = new Person("Fazli", "911102",
            "1111111111", "Vällingbygränd 9");
    Person Fredrik = new Person ("Fredrik","921111",
            "2222222222", "Lidingögatan 2");

    List<Person>AddressBook = new LinkedList<>();

    public Database(){
        AddressBook.add(Elias);
        AddressBook.add(Atef);
        AddressBook.add(Joakim);
        AddressBook.add(Nadja);
        AddressBook.add(Fazli);
        AddressBook.add(Fredrik);
    }

    public List<Person> getAllPersons(){
        return AddressBook;
    }

    public Person getPersonByName(String s){
        for(Person p : AddressBook){
            if(s.equalsIgnoreCase(p.getName()))
                return p;
        }
        return null;
    }

    public Person getPersonByAddress(String s){
        for(Person p : AddressBook){
            if(s.equalsIgnoreCase(p.getAddress()))
                return p;
        }
        return null;
    }
    public Person getPersonByBirthday(String s){
        for(Person p : AddressBook){
            if(s.equalsIgnoreCase(p.getBirthday()))
                return p;
        }
        return null;
    }

}
