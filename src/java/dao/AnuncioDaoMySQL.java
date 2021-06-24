package dao;

import conexionMysql.Conexion;
import dto.Anuncio;
import dto.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class AnuncioDaoMySQL extends AnuncioDao {

    @Override
    public int insert(Anuncio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO anuncio  ( usuarioId, titulo, categoria, descripcion, telefono, precio, estado, imagenFileId) VALUES (");
        
        String strFile = obj.getImagenFileId() == 0 ? "null" : Integer.toString(obj.getImagenFileId());
        
        query.append(obj.getUsuarioId() + ",");
        query.append("'" + obj.getTitulo()+ "',");
        query.append("'" + obj.getCategoria()+ "',");
        query.append("'" + obj.getDescripcion()+ "',");
        query.append("'" + obj.getTelefono()+ "',");
        query.append("'" + obj.getPrecio()+ "',");
        query.append("'" + obj.getEstado()+ "',");
        query.append(strFile);
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Anuncio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        
        String strFile = obj.getImagenFileId() == 0 ? "null" : Integer.toString(obj.getImagenFileId());
                
        StringBuilder query = new StringBuilder("UPDATE anuncio SET ");
        query.append("usuarioId = " + obj.getUsuarioId() + ",");
        query.append("titulo = '" + obj.getTitulo() + "',");
        query.append("categoria = '" + obj.getCategoria() + "',");
        query.append("descripcion = '" + obj.getDescripcion() + "',");
        query.append("telefono = '" + obj.getTelefono() + "',");
        query.append("precio = '" + obj.getPrecio() + "',");
        query.append("estado = '" + obj.getEstado()+ "',");
        query.append("imagenFileId = " + strFile);
        query.append(" WHERE anuncioId = " + obj.getAnuncioId());
        
        System.out.println(query.toString());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM anuncio ");
        query.append("WHERE anuncioId = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Anuncio get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE anuncioId = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _anuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_anuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);
                obj.setUsuarioId(_usuarioId);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);

                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);

                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);

                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<Anuncio> getList() {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE estado = '1'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _AnuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_AnuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);
                
                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);
                
                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);
                
                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }
    
    @Override
    public ArrayList<Anuncio> getListInm() {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE categoria = 'Inmuebles'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _AnuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_AnuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);
                
                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);
                
                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);
                
                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }
    
    @Override
    public ArrayList<Anuncio> getListEle() {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE categoria = 'Electronica'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _AnuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_AnuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);
                
                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);
                
                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);
                
                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }
    
    @Override
    public ArrayList<Anuncio> getListVeh() {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE categoria = 'Vehiculos'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _AnuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_AnuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);
                
                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);
                
                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);
                
                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<Anuncio> getAnunciosByUsuarioId(int usuarioId) {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE usuarioId = " + usuarioId ;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _anuncioId = objResultSet.getInt("anuncioId");
                obj.setAnuncioId(_anuncioId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _Titulo = objResultSet.getString("titulo");
                obj.setTitulo(_Titulo);

                String _Categoria = objResultSet.getString("categoria");
                obj.setCategoria(_Categoria);

                String _Descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_Descripcion);

                int _Telefono = objResultSet.getInt("telefono");
                obj.setTelefono(_Telefono);

                int _Precio = objResultSet.getInt("precio");
                obj.setPrecio(_Precio);
                
                int _Estado = objResultSet.getInt("estado");
                obj.setEstado(_Estado);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

}
