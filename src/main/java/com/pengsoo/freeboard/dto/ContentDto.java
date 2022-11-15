package com.pengsoo.freeboard.dto;

public class ContentDto {

	private int mid;
	private String mWriter;
	private String mContent;
	
	public ContentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContentDto(int mid, String mWriter, String mContent) {
		super();
		this.mid = mid;
		this.mWriter = mWriter;
		this.mContent = mContent;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getmWriter() {
		return mWriter;
	}
	public void setmWriter(String mWriter) {
		this.mWriter = mWriter;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	
	
}
