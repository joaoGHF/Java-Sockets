import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** A classe {@code ClientHandler} é responsável pelo atendimento e cada cliente, recebendo, enviando e cifrando mensagens.
 * @implements Runnable
 * @see Client
 * @see Encryption
 * @see Server
 */
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Long id;

    /** Constructor da classe {@code ClientHandler}.
     * @param clientSocket do tipo {@code Socket} e que representa o cliente a ser atendido.
     * @param id do tipo {@code long} e que representa o {@code ID} do cliente.
     */
    public ClientHandler(Socket clientSocket, long id) {
        this.clientSocket = clientSocket;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * <p> O método {@code run} foi sobrescrito para o tratamento de cada {@link Client} obtido no servidor {@link Server} e também o ciframento com uso da classe {@link Encryption}. </p>
     * @return void
     * @see Encryption
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = "";
            while (!inputLine.equalsIgnoreCase("{@break}")) {
                inputLine = in.readLine();

                System.out.println("Cliente " + this.id + " -> " + inputLine);
                String sha256Hash = Encryption.encrypt_SHA_256(inputLine);

                String response = new String(inputLine +";HASH SHA256: " + sha256Hash + ";");

                System.out.println("Servidor  -> " + response + "\n");
                out.println(response);
            }

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
