package AddressBook.Client;

import AddressBook.Server.Database;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    Socket socketToServer;
    //InetAddress ipAddress = InetAddress.getLocalHost();
    String ipAddress = "127.0.0.1";

    public Client() throws IOException {
        try (
                Socket addressSocket = new Socket(ipAddress, 12345);
                PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(addressSocket.getInputStream()));
        )
        {
            String fromServer;
            String fromUser;

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                    //fromUser = "";
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
        }

    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }
}
