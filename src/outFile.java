import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

 class outFile {


     void write(HashMap over) throws IOException {
        //запись в файл
        File outover=new File("D:\\Books\\MTS\\test.txt");
        FileWriter writer=new FileWriter(outover,true);

        over.forEach((Pkey,Pvalue)->{
            try {

                writer.write(Pkey+" "+Pvalue+"\n");


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }



 }

