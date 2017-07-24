package com.jinpaihushi.jphs.goods.model;

/**
 * 多图上传
 * @author Administrator
 *
 */
public class ImageType {

	/**
	 * pc端图片地址 
	 */
	private String pcurl;
	
	/**
	 * 移动端图片地址 
	 */
	private String moveurl;

	/**
	 * pc端图片id
	 */
	private String pcid;
	
	/**
	 * 移动端图片id
	 */
	private String moveid;

	public String getPcurl() {
		return pcurl;
	}

	public void setPcurl(String pcurl) {
		this.pcurl = pcurl;
	}

	public String getMoveurl() {
		return moveurl;
	}

	public void setMoveurl(String moveurl) {
		this.moveurl = moveurl;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public String getMoveid() {
		return moveid;
	}

	public void setMoveid(String moveid) {
		this.moveid = moveid;
	}

	@Override
	public String toString() {
		return "ImageType [pcurl=" + pcurl + ", moveurl=" + moveurl + ", pcid=" + pcid + ", moveid=" + moveid
				+ ", getPcurl()=" + getPcurl() + ", getMoveurl()=" + getMoveurl() + ", getPcid()=" + getPcid()
				+ ", getMoveid()=" + getMoveid() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
