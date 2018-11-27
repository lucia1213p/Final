package kr.or.tech.board.model.vo;

import java.sql.Date;

public class NComment {
	private int commNo;
	private String commCont;
	private Date commDate;
	private String boardCode;
	private int boardNo;
	private int memberNo;
	private String memberName;
	public NComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NComment(int commNo, String commCont, Date commDate, String boardCode, int boardNo, int memberNo,
			String memberName) {
		super();
		this.commNo = commNo;
		this.commCont = commCont;
		this.commDate = commDate;
		this.boardCode = boardCode;
		this.boardNo = boardNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
	}
	public int getCommNo() {
		return commNo;
	}
	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}
	public String getCommCont() {
		return commCont;
	}
	public void setCommCont(String commCont) {
		this.commCont = commCont;
	}
	public Date getCommDate() {
		return commDate;
	}
	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}	
}
