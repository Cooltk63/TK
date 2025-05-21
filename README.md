import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import java.io.*;
import net.sf.jasperreports.engine.JasperPrint;

public class Utilities {

    public static Object deserialize(String path) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(path));
             ValidatingObjectInputStream vois = new ValidatingObjectInputStream(fis)) {

            // Allow only JasperPrint and any other safe classes if needed
            vois.accept("net.sf.jasperreports.engine.JasperPrint");

            return vois.readObject();
        }
    }
}


vois.accept("net.sf.jasperreports.engine.base.JRBaseStyle");
vois.accept("net.sf.jasperreports.engine.base.JRBasePrintPage");
// Add more based on stack trace