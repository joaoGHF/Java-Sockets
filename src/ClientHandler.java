import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Long id;

    public ClientHandler(Socket clientSocket, Long id) {
        this.clientSocket = clientSocket;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = "";
            while (!inputLine.equalsIgnoreCase("{@break}")) {
                inputLine = in.readLine();
                System.out.println("Cliente " + this.id + " -> " + inputLine);
                System.out.println("Servidor  -> " + "OK");
                out.println("OK");
            }

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
