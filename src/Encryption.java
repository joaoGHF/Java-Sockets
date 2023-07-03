import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/** <p>A classe {@code Encryption} contem metodos estáticos para a cifrar uma {@code String} inicial em uma {@code String} contendo um {@code Hash} de {@code SHA-256}</p>
 */
public class Encryption {
    /** <p>O metodo privado {@code encrypt()} e responsável por converter a {@code String} em um {@code byte[]} contendo os bytes encriptados.</p>
     * @param input - do tipo {@code String} a ser cifrado.
     * @return <ul>
     * <li>A função {@link Encryption#byteToHash(byte[])} ou;</li>
     * <li>{@code null} caso ocorra uma exceção no bloco {@code try}</li></ul>
     */
    private static String encrypt(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return byteToHash(encodedHash);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /** <p>A função {@codebytesToHex()} e utilizada para converter o {@code byte[]} em uma representação hexadecimal legível.</p>
     * @param bytes - o {@code array de bytes} a serem convertidos para a representação hexadecimal.
     * @return uma {@code String} com a representação hexadecimal.
     */
    private static String byteToHash(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }


    /** <p>O metodo {@code encrypt_SHA_256} e usado para obter o conjunto hexadecimal dos bytes cifrados. Fazendo uso do metodo encapsulado {@link Encryption#encrypt(String)} que por sua vez usa como retorno o metodo {@link Encryption#byteToHash(byte[])}.</p>
     * @param text do tipo {@code String} que deve ser cifrado em um {@code HASH SHA-256}.
     * @return uma {@code String} cifrada em {@code SHA-256}.
     */
    public static String encrypt_SHA_256(String text) {
        return encrypt(text);
    }
}
