import DB.DBWorker;
import FileWorker.XmlGrab;
import XML.XMLWorker;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static DB.DBWorker.dropAndCreate;
import static DB.DBWorker.monthlyReport;


public class Tester {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        DBWorker db = new DBWorker();
        XMLWorker xmlWorker;
        HashMap<String, Double> over = new HashMap();

        dropAndCreate();


        //забираем файлы по указанному пути
        for (File file : XmlGrab.getFiles()) {
            if (file.isFile()) {
                //записываем перерасход в базу
                xmlWorker = new XMLWorker();
                db.updateData(xmlWorker.parse(file.getPath()));
            }
        }

        monthlyReport();
        //EmailWorker.emailSender.send("E:\\!distrib\\git\\MTS\\email.property",over);


    }
}
