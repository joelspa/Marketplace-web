package dao;

import dto.Anuncio;
import dto.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class AnuncioDao {

    public abstract int insert(Anuncio obj) throws Exception;

    public abstract void update(Anuncio obj) throws Exception;
	
    public abstract ArrayList<Anuncio> getAnunciosByUsuarioId(int usuarioId);

    public abstract void delete(int id);

    public abstract ArrayList<Anuncio> getList();
    public abstract ArrayList<Anuncio> getListInm();
    public abstract ArrayList<Anuncio> getListEle();
    public abstract ArrayList<Anuncio> getListVeh();

    public abstract Anuncio get(int id);

}
