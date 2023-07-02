import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    String serverAddress;
    int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput = "";
            while (!userInput.equalsIgnoreCase("{@break}")) {
                System.out.println("Mensagem cliente:");
                userInput = stdIn.readLine();

                String sha256Hash = Encryption.encrypt_SHA_256(userInput);
                String verif = new String("HASH SHA256 Mensagem: " + sha256Hash + ";");
                System.out.println(verif);

                out.println(userInput);

                System.out.println("Servidor -> " + in.readLine() + "\n");
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 5555;

        Client client = new Client(serverAddress, serverPort);
        client.start();
    }
}
