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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_weibo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Weibo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer mId;
	// 发布者
	private User mPoster;
	// 文本
	private String mText;
	// 图片
	private String mPicture;
	// 点赞
	private Integer mAgreement;
	// 反对
	private Integer mDisagreement;
	// 发表日期
	private Date mPostTime;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "weibo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return mId;
	}

	/**
	 * @return the poster
	 */
	@JoinColumn(name = "weibo_poster")
	@ManyToOne(targetEntity = User.class)
	public User getPoster() {
		return mPoster;
	}

	/**
	 * @return the text
	 */
	@Column(name = "weibo_text")
	public String getText() {
		return mText;
	}

	/**
	 * @return the picture
	 */
	@Column(name = "weibo_picture")
	public String getPicture() {
		return mPicture;
	}

	/**
	 * @return the agreement
	 */
	@Column(name = "weibo_agreement")
	public Integer getAgreement() {
		return mAgreement;
	}

	/**
	 * @return the disagreement
	 */
	@Column(name = "weibo_disagreement")
	public Integer getDisagreement() {
		return mDisagreement;
	}

	/**
	 * @return the postTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "weibo_posttime")
	public Date getPostTime() {
		return mPostTime;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		mId = id;
	}

	/**
	 * @param poster
	 *            the poster to set
	 */
	public void setPoster(User poster) {
		mPoster = poster;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		mText = text;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(String picture) {
		mPicture = picture;
	}

	/**
	 * @param agreement
	 *            the agreement to set
	 */
	public void setAgreement(Integer agreement) {
		mAgreement = agreement;
	}

	/**
	 * @param disagreement
	 *            the disagreement to set
	 */
	public void setDisagreement(Integer disagreement) {
		mDisagreement = disagreement;
	}

	/**
	 * @param postTime
	 *            the postTime to set
	 */
	public void setPostTime(Date postTime) {
		mPostTime = postTime;
	}

	/**
	 * 利用poster、text、picture、postTime字段生成hash码
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += mPoster == null ? 0 : mPoster.hashCode();
		hash += mText == null ? 0 : mText.hashCode() * 13;
		hash += mPicture == null ? 0 : mPicture.hashCode() * 29;
		hash += mPostTime == null ? 0 : mPostTime.hashCode() * 37;
		return hash;
	}

	/**
	 * 利用poster、text、picture、postTime字段判断是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Weibo)) {
			return false;
		}
		if (mPoster == null || mText == null  || mPostTime == null) {
			return false;
		}
		// Picture是可选的，因此可以为null
		Weibo ano = (Weibo) obj;
		boolean isPictureEqual = 
				(mPicture == null && ano.mPicture == null) ||
				(mPicture.equals(ano.mPicture));
		return isPictureEqual &&
			   mPoster.equals(ano.mPoster) && 
			   mText.equals(ano.mText) &&
			   mPostTime.equals(ano.mPostTime);
	}
}
