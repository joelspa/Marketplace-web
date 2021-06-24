package factory;

import conexionMysql.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class FactoryDaoMySQL extends FactoryDao{

	private FactoryDaoMySQL(){
	}

	public static FactoryDao getFactoryInstance(){
		if(instancia==null)
			instancia = new FactoryDaoMySQL();
		return instancia;
	}

	@Override
	public AnuncioDao getNewAnuncioDao(){
		return new AnuncioDaoMySQL();
	}

	@Override
	public FileDao getNewFileDao(){
		return new FileDaoMySQL();
	}

	@Override
	public UsuarioDao getNewUsuarioDao(){
		return new UsuarioDaoMySQL();
	}

}

