package kr.or.tech.board.model.vo;

import java.util.ArrayList;

public class SptPageData {
	private ArrayList<SupportTech> list;
	private String pageNavi;
	public SptPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SptPageData(ArrayList<SupportTech> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<SupportTech> getList() {
		return list;
	}
	public void setList(ArrayList<SupportTech> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
