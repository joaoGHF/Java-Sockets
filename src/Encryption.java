import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Encryption {
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

    private static String byteToHash(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }

    public static String encrypt_SHA_256(String text) {
        return encrypt(text);
    }
}
