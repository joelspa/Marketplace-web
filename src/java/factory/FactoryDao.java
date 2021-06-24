package factory;

import conexionMysql.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class FactoryDao {

	protected static FactoryDao instancia;
	public static FactoryDao getFactoryInstance(){
		if(instancia == null){
                    Configuracion config = Configuracion.getConfiguracion();
                    if(config.getDbEngine().equals("MySQL"));
                            instancia = FactoryDaoMySQL.getFactoryInstance();
		}
		return instancia;
	}

	public abstract AnuncioDao getNewAnuncioDao();

	public abstract FileDao getNewFileDao();

	public abstract UsuarioDao getNewUsuarioDao();

}

