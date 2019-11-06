package AddressBook_Response_Initi.Server;

import AddressBookSerialized.Server.Person;
import java.io.Serializable;

public class Response implements Serializable {

    boolean success;
    Person person;

    public Response(){}

    public Response(boolean success){
        this.success = success;
    }

    public Response(boolean success, Person person){
        this.success = success;
        this.person = person;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
