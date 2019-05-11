import DB.ConnectionDB;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class Tester {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DB.ConnectionDB db=new ConnectionDB();

        outFile outFile=new outFile();
        XMLWorker xmlWorker;
        HashMap over = new HashMap();

        //Временный вариант забор файлов из указанной директории
        File folder = new File("E:\\!distrib\\git\\MTS\\fileTreeTest2");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                xmlWorker = new XMLWorker();
                over = xmlWorker.parse(file.getPath());
            }outFile.write(over);

        }
        //emailSender.send("E:\\!distrib\\git\\MTS\\1.property",over);





    }
}
