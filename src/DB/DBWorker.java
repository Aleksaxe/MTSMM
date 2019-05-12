package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class DBWorker {
    static private String userName = "root";
    static  private String password = "root";
    static private String connectionUrl = "jdbc:mysql://localhost/mts?useUnicode=true&characterEncoding=UTF-8&useJDBCComplian" +
            "tTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public void updateData(HashMap<String,Double> map) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            Statement statement=connection.createStatement();
            for (Map.Entry<String,Double> entry :map.entrySet()
            ) {

                statement.executeUpdate("update overruns set overrun='"+entry.getValue()+"' " +
                        "where PNumber='"+entry.getKey()+"' ");
            }


        }
    }

   public static void dropAndCreate() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            System.out.println("Connected");
            Statement statement = connection.createStatement();
            try {
                File file = new File("E:\\!distrib\\git\\MTS\\email.property");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();
                statement.executeUpdate("drop table overruns");
                statement.executeUpdate(" CREATE TABLE if not exists `mts`.`overruns` (\n" +
                        "  `PNumber` VARCHAR(12) NOT NULL,\n" +
                        "  `userEmail` VARCHAR(100) NOT NULL,\n" +
                        "  `userName` VARCHAR(100) NULL,\n" +
                        "  `overrun` numeric(45) NULL,\n" +
                        "  PRIMARY KEY (`PNumber`))\n" +
                        "  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;;\n");
                Enumeration enuKeys = properties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);
                    statement.executeUpdate("insert into overruns (PNumber,userEmail) " +
                            "values ('" + key + "','" + value + "') ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            statement.executeUpdate("update overruns set userName='А.Петрова' where PNumber='79124242502' ");
            statement.executeUpdate("update overruns set userName='Т.Наконечная' where PNumber='79130013308' ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void monthlyReport() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            System.out.println("Connected");
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * from overruns where overrun is not null");

            MonthlyReportTxt monthlyReportTxt=new MonthlyReportTxt();
            monthlyReportTxt.write(resultSet);

        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        //dropAndCreate();
        monthlyReport();
    }
    /*



     * */
}
