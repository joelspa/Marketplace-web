package dao;

import conexionMysql.Conexion;
import dto.File;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class FileDaoMySQL extends FileDao {

	@Override
	public int insert(File obj) throws Exception {
		Conexion objConexion = Conexion.getOrCreate();

			int id = 0;
			StringBuilder query = new StringBuilder("INSERT INTO file (fileName, path, temporal) VALUES (");
			query.append("'" + obj.getFileName() + "'," );
			query.append("'" + obj.getPath() + "'," );
			query.append("" + obj.getTemporal() + " " );
			query.append(")");
			id = objConexion.ejecutarInsert(query.toString());
			if(id == 0)
				throw new Exception("El registro no pudo ser insertado");
			objConexion.desconectar();
			return id;
	}
	@Override
	public void update(File obj) throws Exception {
		Conexion objConexion = Conexion.getOrCreate();

			StringBuilder query = new StringBuilder("UPDATE file SET ");
			query.append("fileName = '" + obj.getFileName() + "',");
			query.append("path = '" + obj.getPath() + "',");
			query.append("temporal = '" + obj.getTemporal() + "' ");
			query.append("WHERE fileId = " + obj.getFileId() );
			int upd = objConexion.ejecutarSimple(query.toString());
			if(upd == 0)
				throw new Exception("El registro no pudo ser actualizado");

			objConexion.desconectar();
	}
	@Override
	public void delete(int id){
		Conexion objConexion = Conexion.getOrCreate();
		StringBuffer query = new StringBuffer("DELETE FROM file ");
		query.append("WHERE fileId = " + id);
		objConexion.ejecutarSimple(query.toString());
		objConexion.desconectar();
	}

	@Override
	public File get(int id){
		try{
			Conexion objConexion = Conexion.getOrCreate();
			String query = "SELECT * FROM file WHERE fileId = " + id;
			ResultSet objResultSet = objConexion.ejecutar(query);
			if(objResultSet.next()){
				File obj = new File();
				int _fileId = objResultSet.getInt("fileId");
				obj.setFileId(_fileId);
				
				String _fileName = objResultSet.getString("fileName");
				obj.setFileName(_fileName);
				
				String _path = objResultSet.getString("path");
				obj.setPath(_path);
				
				int _temporal = objResultSet.getInt("temporal");
				obj.setTemporal(_temporal);
				
				return obj;
			}
		}catch(Exception ex){
			;
		}
		return null;
	}

	@Override
	public ArrayList<File> getList(){
		ArrayList<File>  registros = new ArrayList<File>();
		try{
			Conexion objConexion = Conexion.getOrCreate();
			String query = "SELECT * FROM file";
			ResultSet objResultSet = objConexion.ejecutar(query);
			while(objResultSet.next()){
				File obj = new File();
				int _fileId = objResultSet.getInt("fileId");
				obj.setFileId(_fileId);
				
				String _fileName = objResultSet.getString("fileName");
				obj.setFileName(_fileName);
				
				String _path = objResultSet.getString("path");
				obj.setPath(_path);
				
				int _temporal = objResultSet.getInt("temporal");
				obj.setTemporal(_temporal);
				
				registros.add(obj);
			}
		}catch(Exception ex){
			;
		}
		return registros;
	}

}

