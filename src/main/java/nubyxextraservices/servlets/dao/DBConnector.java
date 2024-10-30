package nubyxextraservices.servlets.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import desktopframework.Log;
import nubyxextraservices.servlets.constant.PropertiesVars;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {
    private static HikariDataSource dataSource;

    public static HikariDataSource generateConnectionFromPool(){
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(PropertiesVars.getPOSTGRESQL_URL());
            config.setUsername(PropertiesVars.getPOSTGRESQL_USER());
            config.setPassword(PropertiesVars.getPOSTGRESQL_PASSWORD());
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            Log.info("Obteniendo conexión");
            return new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            dataSource = generateConnectionFromPool();
            Log.info("Conexión obtenida");
        }
        return dataSource.getConnection();
    }
}
