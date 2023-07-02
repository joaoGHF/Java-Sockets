import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class FineServer extends Thread {
    private ServerSocket serverSocket;

    public FineServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Aguardando cliente na porta " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Conectado com " + server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("Mensagem do cliente: " + in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Server -> Obrigado por conectar-se com " + server.getLocalSocketAddress());

                server.close();
                in.close();
                out.close();

            } catch (SocketTimeoutException e) {
                System.out.println("Tempo de conexão esgotado");
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 5555;
        try {
            Thread t = new FineServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
