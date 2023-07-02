import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Long clientId = 0L;
    private ServerSocket serverSocket;
    private ExecutorService executor;

    public Server(int port, int nThreads) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            executor = Executors.newFixedThreadPool(nThreads);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Servidor iniciado, aguardando conexões");

        while (true) {

            try {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, clientId++);
                executor.execute(clientHandler);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        executor.shutdown();

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            int port = 5555;
            Server server;
            server = new Server(port, 10);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
