package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private String userName="root";
    private String password="root";
    private String connectionUrl="jdbc:mysql://localhost/mts?useUnicode=true&useJDBCComplian" +
            "tTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    void connect() throws ClassNotFoundException, SQLException {
        //используемый драйвер
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection= DriverManager.getConnection(connectionUrl,userName,password)){



        }
    }

}
