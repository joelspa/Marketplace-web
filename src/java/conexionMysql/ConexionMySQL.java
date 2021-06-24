package conexionMysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class ConexionMySQL extends Conexion {

    public static Conexion getOrCreate(){
        if(objSingleton==null)
            objSingleton = new ConexionMySQL();
        return objSingleton;
    }

    private ConexionMySQL() {
        Configuracion objConfiguracion =
                Configuracion.getConfiguracion();
        this.host = objConfiguracion.getDbHost();
        this.dataBase = objConfiguracion.getDbName();
        this.instance = objConfiguracion.getDbInstace();
        this.port = objConfiguracion.getDbPort();
        this.userName = objConfiguracion.getDbUser();
        this.password = objConfiguracion.getDbPassword();
    }

    public void conectar() {
        if (this.estaConectado()) {
            return;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                String sUrl = "jdbc:mysql://" + this.host + "/" + this.dataBase + "?autoReconnect=true&useSSL=false";
                System.out.println(sUrl);
                objConnection = DriverManager.getConnection(sUrl, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void comenzarTransaccion() {
        if (!this.estaConectado()) {
            this.conectar();
        }

        try {
            objConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void terminarTransaccion() {
        try {
            objConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            if (this.estaConectado()) {
                objConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet ejecutar(String query) {
        try {
            Statement stmt = objConnection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean estaConectado() {
        if (this.objConnection == null) {
            return false;
        }
        try {
            if (this.objConnection.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            this.objConnection = null;
            return false;
        }
        return true;
    }

    public int ejecutarSimple(String query) {
        try {
            Statement stmt = objConnection.createStatement();
            int nb = stmt.executeUpdate(query);
            return nb;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int ejecutarInsert(String query) {
        try {
            Statement stmt = objConnection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
