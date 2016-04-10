package com.shsun.view.listview.cell;

public class CellData {

	private String mImageUrl;
	private String mText;

	public CellData(String url, String text) {
		this.mImageUrl = url;
		this.mText = text;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public String getText() {
		return mText;
	}
}
