package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionInterface {
    public Connection getConnection() throws SQLException;

}
