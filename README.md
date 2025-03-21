import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamToByteArray {
    public static byte[] convertStreamToByteArray(InputStream keyStream) throws IOException {
        if (keyStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = keyStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
}