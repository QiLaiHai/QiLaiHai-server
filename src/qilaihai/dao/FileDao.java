package qilaihai.dao;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import qilaihai.domain.FileMap;

public interface FileDao extends BaseDao<FileMap> {
	/**
	 * 储存文件
	 * @param file 文件
	 * @param fileName 文件名，不包含扩展名
	 * @param suffix 文件扩展名
	 * @return null为储存失败
	 */
	Serializable save(File file, String fileName, String suffix);
	List<FileMap> getByFileName(String fileName);
	/**
	 * 通过文件的哈希码获取唯一文件.
	 * @param hashCode
	 * @return
	 */
	FileMap getByHashCode(String hashCode);
	FileMap get(Integer id);
}
