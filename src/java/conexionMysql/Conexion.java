package conexionMysql;

import java.sql.Connection;

/**
 *
 * @author Jose Carlos Gutierrez
 *
 * necesarios para conexiones con bases de datos típicas como Oracle, MySql, SqlServer,
 * etc.
 * Esta clase no se puede utilizar directamente, se la utiliza a través de instancias
 * de subclases de esta clase. Las instancias implementan los accesos y la forma de
 * los queries en cada caso.
 */
public abstract class Conexion implements IConexion {

    /**
     * El host/servidor de la base de datos
     */
    protected String host;
    /**
     * El nombre de la base de datos.
     */
    protected String dataBase;
    /**
     * El nombre de la instancia de la base de datos (caso Microsoft)
     */
    protected String instance;
    /**
     * El puerto de acceso a la base de datos.
     */
    protected String port;
    /**
     * El usuario con el cual se accede a la base de datos.
     */
    protected String userName;
    /**
     * La contraseña utilizada
     */
    protected String password;
    /**
     * El objeto de tipo conexión que es de tipo de la interfase JDBC. Entonces,
     * cualquier implementación de Driver de Java de acceso a datos debe respetar
     * esta interfase.
     */
    protected Connection objConnection;
    /**
     * Se maneja la conexión con el patrón de diseño Singleton. Esto para que haya
     * solo una instancia de la conexión y no se abran innecesariamente otras.
     */
    protected static Conexion objSingleton;

    /**
     * Implementación del patrón singleton. Este método permit obtener o crear
     * la única instancia de tipo Conexion que está permitida existir.
     *
     * @return Un único objeto de tipo conexión que existe en la aplicación.
     */
    public static Conexion getOrCreate() {
        if (objSingleton == null) {
            Configuracion config = Configuracion.getConfiguracion();
            if (config.getDbEngine().equals("MySQL")) {
                objSingleton = ConexionMySQL.getOrCreate();
            }
            /*if (config.getDbEngine().equals("Oracle")) {
                objSingleton = ConexionOracle.getOrCreate();
            }
            if (config.getDbEngine().equals("SQLServer")) {
                objSingleton = ConexionOracle.getOrCreate();
            }
			if (config.getDbEngine().equals("PostgreSQL")) {
                objSingleton = ConexionPostgreSQL.getOrCreate();
            }
			*/
        }
        objSingleton.conectar();
        return objSingleton;
    }

    /**
     * @return Returns the database.
     */
    public String getDataBase() {
        return dataBase;
    }

    /**
     * @return Returns the host.
     */
    public String getHost() {
        return host;
    }

    /**
     * @return Returns the instance.
     */
    public String getInstance() {
        return instance;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return Returns the port.
     */
    public String getPort() {
        return port;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return userName;
    }
}
