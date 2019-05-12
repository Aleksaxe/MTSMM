package DB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class MonthlyReportTxt {


    void write(ResultSet resultSet) throws IOException, SQLException {
        // Создание файла для записи отчета
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMYYYY", Locale.ENGLISH);
        File monthlyReportTxt=new File("E:\\!distrib\\git\\MTS\\"+dateFormat.format(currentDate)+".txt");
        if (monthlyReportTxt.exists()){
            monthlyReportTxt.delete();
        }
        monthlyReportTxt.createNewFile();
        //----------------------------------------------
        FileWriter writer = new FileWriter(monthlyReportTxt);

        while (resultSet.next()) {
            writer.write(resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +resultSet.getInt(4)+"\n");

        }
        writer.flush();
        writer.close();
    }


}

