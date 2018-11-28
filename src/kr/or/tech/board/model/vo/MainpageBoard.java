package kr.or.tech.board.model.vo;

import java.util.ArrayList;

public class MainpageBoard {
	private Notice selectNotice;
	private ArrayList<Notice> recentNotice;
	public MainpageBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainpageBoard(Notice selectNotice, ArrayList<Notice> recentNotice) {
		super();
		this.selectNotice = selectNotice;
		this.recentNotice = recentNotice;
	}
	public Notice getSelectNotice() {
		return selectNotice;
	}
	public void setSelectNotice(Notice selectNotice) {
		this.selectNotice = selectNotice;
	}
	public ArrayList<Notice> getRecentNotice() {
		return recentNotice;
	}
	public void setRecentNotice(ArrayList<Notice> recentNotice) {
		this.recentNotice = recentNotice;
	}
	
	
}
