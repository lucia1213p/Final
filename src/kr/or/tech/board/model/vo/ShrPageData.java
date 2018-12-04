package kr.or.tech.board.model.vo;

import java.util.ArrayList;

public class ShrPageData {
	private ArrayList<ShrTech> list;
	private String pageNavi;
	public ShrPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShrPageData(ArrayList<ShrTech> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<ShrTech> getList() {
		return list;
	}
	public void setList(ArrayList<ShrTech> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
