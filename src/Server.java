import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** <p>A classe {@code Server} tem como objetivo iniciar o servidor e tratar a criação de um {@code ClientHandler} para cada {@code Client}</p>
 * @see Client
 * @see ClientHandler
 */
public class Server {
    private static Long clientId = 0L;
    private ServerSocket serverSocket;
    private ExecutorService executor;

    /** <p>Constructor da classe {@code Server}.</p>
     * @param port do tipo {@code int} que representa a porta do servidor que cada {@code Client} deve conectar-.
     * @param nThreads do tipo {@code int} que representa o número máximo de threads que podem ser geradas.
     */
    public Server(int port, int nThreads) {
        try {
            serverSocket = new ServerSocket(port);
            executor = Executors.newFixedThreadPool(nThreads);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** <p>O metodo {@code start()} e responsável por sempre esperar a conexão de um {@code Client} para atribuí-lo um {@code ClientHandler} e um {@code clientId}.</p>
     */
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

    /** <p>O metodo {@code main} atribuí um novo objeto {@code Server} para a variável {@code server} usando os atributos {@code int port} para o número da porta que o servidor deve abrir e {@code int nThreads} para o número de {@code Threads} permitido. Depois e utilizado o metodo {@link Server#start()} para continuidade da execução do servidor.</p>
     * @param args - sem uso de argumentos da linha de comando.
     * <p>@implNote Os valores de {@code int port} e {@code int nThreads} podem ser alterados de acordo com suas necessidades. E em caso de alteração da porta do servidor, deve-se conferir uma alteração para a porta do servidor em {@link Client}.</p>
     */
    public static void main(String[] args) {
        try {
            int port = 5555;
            int nThreads = 10;
            Server server;
            server = new Server(port, nThreads);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
