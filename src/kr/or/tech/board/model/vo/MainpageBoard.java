package kr.or.tech.board.model.vo;

import java.util.ArrayList;

public class MainpageBoard {
	private Notice selectNotice;
	private ArrayList<Notice> recentNotice;
	private ArrayList<ShrTech> recentShare;
	private ArrayList<SupportTech> recentSupport;
	
	public MainpageBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainpageBoard(Notice selectNotice, ArrayList<Notice> recentNotice, ArrayList<ShrTech> recentShare,
			ArrayList<SupportTech> recentSupport) {
		super();
		this.selectNotice = selectNotice;
		this.recentNotice = recentNotice;
		this.recentShare = recentShare;
		this.recentSupport = recentSupport;
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
	public ArrayList<ShrTech> getRecentShare() {
		return recentShare;
	}
	public void setRecentShare(ArrayList<ShrTech> recentShare) {
		this.recentShare = recentShare;
	}
	public ArrayList<SupportTech> getRecentSupport() {
		return recentSupport;
	}
	public void setRecentSupport(ArrayList<SupportTech> recentSupport) {
		this.recentSupport = recentSupport;
	}
	
	
	
}
