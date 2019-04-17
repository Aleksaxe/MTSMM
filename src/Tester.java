import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Tester {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("D:\\Books\\MTS\\1.property")));
        XMLWorker xmlWorker = new XMLWorker();
        HashMap over = xmlWorker.parse("D:\\Books\\MTS\\1.xml");

        over.forEach((key, value) -> {
            String email = properties.getProperty((String) key) + "@multi-menu.com";
            emailSender.send("", "", email
                    , (String) key, Double.parseDouble(value.toString()));
        });

    }
}
