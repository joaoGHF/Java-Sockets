import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/** A classe {@code Encryption} contém métodos estáticos para a cifrar uma {@code String} inicial em uma {@code String} contendo um {@code Hash} de {@code SHA-256}
 */
public class Encryption {
    /** O método privado {@code encrypt()} é responsável por converter a {@code String} em um {@code byte[]} contendo os bytes encriptados.
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

    /** A função {@codebytesToHex()} é utilizada para converter o {@code byte[]} em uma representação hexadecimal legível.
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


    /** O método {@code encrypt_SHA_256} é usado para obter o conjunto hexadecimal dos bytes cifrados. Fazendo uso do método encapsulado {@link Encryption#encrypt(String)} que por sua vez usa como retorno o método {@link Encryption#byteToHash(byte[])}.
     * @param text do tipo {@code String} que deve ser cifrado em um {@code HASH SHA-256}.
     * @return uma {@code String} cifrada em {@code SHA-256}.
     */
    public static String encrypt_SHA_256(String text) {
        return encrypt(text);
    }
}
