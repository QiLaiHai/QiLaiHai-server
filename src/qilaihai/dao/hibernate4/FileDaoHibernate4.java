package qilaihai.dao.hibernate4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.List;

import qilaihai.dao.FileDao;
import qilaihai.domain.FileMap;

public class FileDaoHibernate4 extends BaseDaoHibernate4<FileMap> implements
        FileDao {

	private String mStorePath;

	/**
	 * 如若文件或者文件名为null则取消保存
	 */
	@Override
	public Serializable save(File file, String fileName, String suffix) {

		if (file == null || fileName == null) {
			return null;
		}
		FileMap fileMap = new FileMap();
		fileMap.setFile(file);
		fileMap.setFileName(fileName + (suffix == null ? "" : "." + suffix));
		try {
			String md5 = getMd5ByFile(file);
			fileMap.setHashCode(md5);

			StringBuilder sb = new StringBuilder();
			sb.append(mStorePath).append("/").append(md5);

			if (suffix != null) {
				sb.append(".").append(suffix);
			}

			File fileOut = new File(sb.toString());

			InputStream fin = new FileInputStream(file);
			OutputStream fout = new FileOutputStream(fileOut);

			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = fin.read(buffer)) > 0) {
				fout.write(buffer, 0, length);
			}

			fin.close();
			fout.close();

		} catch (Exception e) {
			return null;
		}

		return save(fileMap);
	}

	@Override
	public List<FileMap> getByFileName(String fileName) {
		// TODO:
		return null;
	}

	@Override
	public FileMap getByHashCode(String hashCode) {
		// TODO:
		return null;
	}

	public String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(
			        FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);

			StringBuffer sb = new StringBuffer(16);
			for (int l = value.length(); l < 16; l++) {
				sb.append("0");
			}
			sb.append(value);
			value = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value.toLowerCase();
	}

	/**
	 * @return the storePath
	 */
	public String getStorePath() {
		return mStorePath;
	}

	/**
	 * @param storePath
	 *            the storePath to set
	 */
	public void setStorePath(String storePath) {
		mStorePath = storePath;
	}

	@Override
    public FileMap get(Integer id) {
	    return get(FileMap.class, id);
    }

}
