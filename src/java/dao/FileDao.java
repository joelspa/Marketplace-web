package dao;

import dto.File;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class FileDao {

	public abstract int insert(File obj) throws Exception;

	public abstract void update(File obj) throws Exception;

	public abstract void delete(int id);

	public abstract ArrayList<File> getList();

	public abstract File get(int id);

}

