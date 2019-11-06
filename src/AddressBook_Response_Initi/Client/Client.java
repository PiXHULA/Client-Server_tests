package AddressBook_Response_Initi.Client;

import AddressBookSerialized.Server.Initiator;
import AddressBook_Response_Initi.Server.Response;

import java.io.*;
import java.net.Socket;

public class Client {
    public Client() throws IOException, ClassNotFoundException {
        String hostAddress = "127.0.0.1";
        int portNumber = 12345;

        try (
                Socket addressSocket = new Socket(hostAddress, portNumber);

                ObjectOutputStream oOutStream = new ObjectOutputStream(
                        addressSocket.getOutputStream());
                ObjectInputStream oInStream = new ObjectInputStream(
                        addressSocket.getInputStream());) {

                Object fromServer;
                String fromUser;
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in));

            while ((fromServer = oInStream.readObject()) != null) {

                if (fromServer instanceof Initiator) {
                    System.out.println("Connection established.");
                    System.out.println("What person would you like to look up?");

                } else if (fromServer instanceof Response) {
                    if (!((Response) fromServer).getSuccess()) {
                        System.out.println("Personen finns inte i databasen");
                    } else {
                        System.out.println(((Response) fromServer)
                                .getPerson());
                    }
                }
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    oOutStream.writeObject(fromUser);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client c = new Client();
    }
}
