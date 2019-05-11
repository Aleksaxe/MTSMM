package DB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionDB {

    public static void  connect() throws ClassNotFoundException, SQLException {
        //используемый драйвер
        Class.forName("com.mysql.cj.jdbc.Driver");

        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost/mts?useUnicode=true&characterEncoding=UTF-8&useJDBCComplian" +
                "tTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try(Connection connection= DriverManager.getConnection(connectionUrl, userName, password)){
            System.out.println("Connected");
            Statement statement=connection.createStatement();
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
                        "  `overrun` VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY (`PNumber`))\n" +
                        "  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;;\n");
                Enumeration enuKeys = properties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);
                    statement.executeUpdate("insert into overruns (PNumber,userEmail) " +
                            "values ('"+key+"','"+value+"') ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            statement.executeUpdate("update overruns set userName='А.Петрова' where PNumber='79124242502' ");
            statement.executeUpdate("update overruns set userName='Т.Наконечная' where PNumber='79130013308' ");

        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        connect();
    }
    /*

    try {
                File file = new File("E:\\!distrib\\git\\MTS\\email.property");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                Enumeration enuKeys = properties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);
                    statement.executeUpdate("insert into overruns (PNumber,userEmail) " +
                            "values ('"+key+"','"+value+"') ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


   CREATE TABLE `mts`.`overruns` (
  `PNumber` VARCHAR(12) NOT NULL,
  `userEmail` VARCHAR(100) NOT NULL,
  `userName` VARCHAR(100) NULL,
  `overrun` VARCHAR(45) NULL,
  PRIMARY KEY (`PNumber`))
  DEFAULT CHARACTER SET cp1251 COLLATE cp1251_general_ci;;

    * */
}
