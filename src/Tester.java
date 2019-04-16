import java.util.HashMap;
import java.util.Map;

public class Tester {

    public static void main(String[] args) {

        XMLWorker xmlWorker=new XMLWorker();
        HashMap<String,Double> over= xmlWorker.parse("D:\\Books\\MTS\\1.xml");
        for (Map.Entry entry:over.entrySet()
             ) {
            emailSender.send("a.aksenov@multi-menu.net","Rjvcjvjk0","a.aksenov@multi-menu.com"
            ,entry.getKey().toString(), Double.parseDouble(entry.getValue().toString()));
        }

    }
}
