package AddressBookSerialized.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Database db = new Database();

    public Server() throws IOException {
        int port = 12345;
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();

                ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            Object inputLine;
            Person outputLine;

            //outStream.writeObject(new Person(null,null,null,"Vems adress vill du få ut?"));
           outStream.writeObject("Sök");

            while ((inputLine = (String)inStream.readObject()) != null) {

                outputLine = db.getPersonBySomething(((String)inputLine).trim());
                if (outputLine == null) {
                    outStream.writeChars("Personen finns inte i databasen");
                } else {
                    outStream.writeObject(outputLine);
                    /*
                    outStream.writeObject(outputLine.getName() +"\n" + outputLine.getAddress()
                    + "\n" + outputLine.getPhoneNumber() + "\n" + outputLine.getBirthday());

                     */
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
    }
}
