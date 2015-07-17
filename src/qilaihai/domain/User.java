package qilaihai.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="t_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable  {

	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	// ------------------- Field ---------------

	private Integer mId;
	private String mPassword;
	private String mNickName;
	private String mPhoneNumber;
	private Date mBirthday;
	private Integer mGender;
	private School mSchool;

	// -------------------------------

	public User() {

	}

	/**
	 * @return the id
	 */
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return mId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		mId = id;
	}

	/**
	 * @return the passwordHashed
	 */
	@Column(name="user_password", nullable=false)
	public String getPassword() {
		return mPassword;
	}

	/**
	 * @param passwordHashed
	 *            the passwordHashed to set
	 */
	public void setPassword(String password) {
		mPassword = password;
	}

	/**
	 * @return the nickName
	 */
	@Column(name="user_nickname")
	public String getNickName() {
		return mNickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		mNickName = nickName;
	}

	/**
	 * @return the phoneNumber
	 */
	@Column(name="user_phonenumber", nullable=false)
	public String getPhoneNumber() {
		return mPhoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		mPhoneNumber = phoneNumber;
	}

	/**
	 * @return the birthday
	 */
	@Column(name="user_birthday")
	public Date getBirthday() {
		return mBirthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		mBirthday = birthday;
	}

	/**
	 * @return the gender
	 */
	@Column(name="user_gender")
	public Integer getGender() {
		return mGender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Integer gender) {
		mGender = gender;
	}

	/**
	 * @return the school
	 */
	@JoinColumn(name="user_School")
	@ManyToOne(targetEntity=School.class)
	public School getSchool() {
		return mSchool;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public void setSchool(School school) {
		mSchool = school;
	}

	/**
	 * 使用电话作为哈希码
	 */
	@Override
	public int hashCode() {
		return mPhoneNumber == null ? 0 : mPhoneNumber.hashCode();
	}

	/**
	 * 使用电话和密码作为判断依据
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof User)) {
			return false;
		}
		if (mPhoneNumber == null || mPassword == null) {
			return false;
		}
		User ano = (User)obj;
		return mPhoneNumber.equals(ano.mPhoneNumber)
				&& mPassword.equalsIgnoreCase(ano.mPassword);
	}

}
