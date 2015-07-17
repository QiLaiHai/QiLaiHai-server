package qilaihai.domain;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_filemap")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileMap implements Serializable {
	/**
	 * 第一版
	 */
	private static final long serialVersionUID = 1L;

	// ------- filed ----------
	Integer mId;
	String mHashCode;
	String mFileName;
	
	File mFile;
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name="file_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return mId;
	}

	/**
	 * @return the hashCode
	 */
	@Column(name="file_code", nullable=false, length=33)
	public String getHashCode() {
		return mHashCode;
	}

	/**
	 * @return the fileName
	 */
	@Column(name="file_name", nullable=false, length=65)
	public String getFileName() {
		return mFileName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		mId = id;
	}

	/**
	 * @param hashCode
	 *            the hashCode to set
	 */
	public void setHashCode(String hashCode) {
		mHashCode = hashCode;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		mFileName = fileName;
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode += mHashCode == null ? mHashCode.hashCode() : 0;
		hashCode += mFileName == null ? mFileName.hashCode() * 23 : 0;
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof FileMap)) {
			return false;
		}
		if (mFileName == null || mHashCode == null) {
			return false;
		}
		FileMap ano = (FileMap) obj;
		return mFileName.equals(ano.mFileName) && mHashCode.equals(ano.mHashCode);
	}

	/**
	 * 获取文件。非数据库字段
	 * @return the file
	 */
	@Transient
	public File getFile() {
		return mFile;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		mFile = file;
	}

}
