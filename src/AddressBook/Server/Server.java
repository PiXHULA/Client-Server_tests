package AddressBook.Server;

import javax.imageio.stream.ImageInputStream;
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

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            //String outputLine;
            Person outputLine;

            out.println("Sök efter en person");

            while ((inputLine = in.readLine()) != null) {

                System.out.println("Getting message " + inputLine);
                outputLine = db.getPersonByAddress(inputLine.trim());
                if (outputLine == null) {
                    out.println("Personen finns inte i databasen");
                } else {
                    out.println(outputLine);
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
