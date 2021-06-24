package conexionMysql;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Configuracion {

    private String dbHost;
    private String dbInstace;
    private String dbUser;
    private String dbPassword;
    private String dbName;
    private String dbEngine;
    private String dbPort;
    
    private static Configuracion config;

    private Configuracion() {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            InputStream in = Configuracion.class.getClassLoader().getResourceAsStream("config.xml");
            Document doc = builder.build(in);
            Element appConfig = doc.getRootElement();
            Element dbConfig= appConfig.getChild("db-config");
            this.dbEngine = dbConfig.getChild("db-engine").getText();
            this.dbHost = dbConfig.getChild("db-host").getText();
            this.dbPort = dbConfig.getChild("db-port").getText();
            this.dbInstace = dbConfig.getChild("db-instance").getText();
            this.dbName = dbConfig.getChild("db-name").getText();
            this.dbUser = dbConfig.getChild("db-user").getText();
            this.dbPassword = dbConfig.getChild("db-password").getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Configuracion getConfiguracion(){
        if(config == null)
            config = new Configuracion();
        return config;
    }

    public String getDbEngine() {
        return dbEngine;
    }

    public void setDbEngine(String dbEngine) {
        this.dbEngine = dbEngine;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbInstace() {
        return dbInstace;
    }

    public void setDbInstace(String dbInstace) {
        this.dbInstace = dbInstace;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }
}
