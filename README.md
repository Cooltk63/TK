import org.apache.commons.io.serialization.ValidatingObjectInputStream;

import java.io.*;

public class SafeDeserializer {

    public static Object deserialize(String path) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(path));
             ValidatingObjectInputStream vois = new ValidatingObjectInputStream(fis)) {

            // Allow only safe classes (replace/add as needed)
            vois.accept("java.util.ArrayList");
            vois.accept("java.lang.String");
            vois.accept("com.yourcompany.YourSafeClass"); // Replace with actual allowed class

            return vois.readObject();
        }
    }
}