import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** <p>A classe {@code Client} é usada como socket para conectar-se ao {@link Server} e executar comunicações com {@link ClientHandler}, usando a cifra {@code SHA-256} disponível em {@link Encryption}.</p>
 * <p>Usando como valores padrão para o {@code serverAddress} e {@code serverPort}, respctivamente {@code "127.0.0.1"} e {@code 5555}.</p>
 * @see Encryption
 * @see Server
 * @see ClientHandler
 */
public class Client {

    String serverAddress;
    int serverPort;

    /** <p>Constructor que recebe os parâmetros {@code serverAddress} e {@code serverPort}.<p>
     * @param serverAddress - {@code String} para o endereço do servidor
     * @param serverPort - {@code int} para a porta do servidor
     */
    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /** <p>Método {@code start} executa toda a conexão e comunicação com o {@code ServerSocket}. Usa fluxos de entrada e saída para receber {@code inputs} do usuário pelo terminal através do {@code BufferedReader stdIn} e do servidor pelo {@code BufferedReader in} e, envia mensagens para o servidor com o {@code PrintWriter out}.</p>
     */
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

    /** <p>Método main que cria um objeto do tipo {@code Client} chamado {@code client} com os parâmetros {@code String serverAddress} e {@code int serverPort}. Para então executar o método {@code start()} de {@code client}.</p>
     * @param args - sem uso de argumentos da linha de comando.
     * <p>@implNote Os valores de {@code String serverAddress} e {@code int serverPort} podem ser alterados de acordo com suas necessidades. E em caso de alteração da porta do servidor, deve-se conferir uma alteração para a porta do servidor em {@link Server}.</p>
     */
    public static void main(String[] args) {

        String serverAddress = "127.0.0.1";
        int serverPort = 5555;

        Client client = new Client(serverAddress, serverPort);
        client.start();
    }
}
