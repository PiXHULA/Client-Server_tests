package AddressBook_Response_Initi.Server;

import AddressBookSerialized.Server.Database;
import AddressBookSerialized.Server.Initiator;
import AddressBookSerialized.Server.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Database db = new Database();

    public Server() throws IOException {
        int portNumber = 12345;
        try(
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();

        ObjectOutputStream oOutStream = new ObjectOutputStream(
                clientSocket.getOutputStream());
        ObjectInputStream oInStream = new ObjectInputStream(
                clientSocket.getInputStream());
        ){
            Object inputLine;
            Person outputLine;

            oOutStream.writeObject(new Initiator());

            while ((inputLine = (String)oInStream.readObject()) != null){

                outputLine = db.getPersonBySomething(((String)inputLine).trim());
                if(outputLine == null){
                    oOutStream.writeObject(new Response(false));
                }
                else{
                    oOutStream.writeObject(new Response(true,outputLine));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
    }
}
