package qilaihai.domain;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="t_school")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class School implements Serializable {
	/**
	 * 第一版
	 */
	private static final long serialVersionUID = 1L;
	
	// --------- field --------- 
	private Integer mId;
	private String mName;

	// --------- method ---------
	
	public School() {
		
	}
	
	public School(String hehe) {
		System.out.println(hehe);
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name="school_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return mId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		mId = id;
	}
	/**
	 * @return the name
	 */
	@Column(name="school_name", nullable=false, length=30)
	public String getName() {
		return mName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * 使用名字作为哈希码
	 */
	@Override
	public int hashCode() {
		return mName == null ? 0 : mName.hashCode();
	}

	/**
	 * 使用名字作为判断依据
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof School)) {
			return false;
		}
		if (mName == null) {
			return false;
		}
		School ano = (School)obj;
		return mName.equalsIgnoreCase(ano.mName);
	}
}
