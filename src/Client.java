import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        String message;
        DataOutputStream out = null;
        String host = "127.0.0.1";
        int port = 5555;

        Socket socket;

        try {
            socket = new Socket(host, port);
            System.out.println("Conectado com " + socket.getRemoteSocketAddress());

            BufferedReader inLocal = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escreva a mensagem do servidor:");
            message = inLocal.readLine();

            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(message);

            DataInputStream in = new DataInputStream(socket.getInputStream());

            System.out.println(in.readUTF());

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
