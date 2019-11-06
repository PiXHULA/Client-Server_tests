package AddressBookSerialized.Client;

import AddressBookSerialized.Server.Person;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client() throws IOException {
        String ipAddress = "127.0.0.1";
        int portNumber = 12345;
        try (
                Socket addressSocket = new Socket(ipAddress, portNumber);
                ObjectOutputStream outStream = new ObjectOutputStream(addressSocket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(addressSocket.getInputStream())
        )
        {
            Object fromServer;
            String fromUser;

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));

            while ((fromServer = inStream.readObject()) != null) {
                if (fromServer instanceof Person){
                    Person pers=(Person)fromServer;
                    System.out.println("Server: " +pers);
                }

                if (fromServer instanceof String){
                    String s=(String) fromServer;
                    System.out.println("Server: " +  s);
                }
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    outStream.writeObject(fromUser);
                }
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ipAddress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    ipAddress);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }
}
