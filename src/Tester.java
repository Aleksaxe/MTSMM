import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class Tester {

    public static void main(String[] args) throws IOException {

        outFile outFile=new outFile();
        XMLWorker xmlWorker;
        HashMap over = new HashMap();
        File folder = new File("D:\\Books\\MTS\\fileTreeTest2");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                xmlWorker = new XMLWorker();
                over = xmlWorker.parse(file.getPath());
            }outFile.write(over);
        }
        emailSender.send("D:\\Books\\MTS\\1.property",over);





    }
}
