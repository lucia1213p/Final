package kr.or.tech.board.model.vo;

import java.sql.Date;

public class ShrTechAnswer {
	private int answNo;
	private String answCont;
	private Date answDate;
	private int shrNo;
	private int memberNo;
	private String memberId;
	private String answAddopt;
	private String boardCode;
	public ShrTechAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShrTechAnswer(int answNo, String answCont, Date answDate, int shrNo, int memberNo, String memberId,
			String answAddopt, String boardCode) {
		super();
		this.answNo = answNo;
		this.answCont = answCont;
		this.answDate = answDate;
		this.shrNo = shrNo;
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.answAddopt = answAddopt;
		this.boardCode = boardCode;
	}
	public int getAnswNo() {
		return answNo;
	}
	public void setAnswNo(int answNo) {
		this.answNo = answNo;
	}
	public String getAnswCont() {
		return answCont;
	}
	public void setAnswCont(String answCont) {
		this.answCont = answCont;
	}
	public Date getAnswDate() {
		return answDate;
	}
	public void setAnswDate(Date answDate) {
		this.answDate = answDate;
	}
	public int getShrNo() {
		return shrNo;
	}
	public void setShrNo(int shrNo) {
		this.shrNo = shrNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAnswAddopt() {
		return answAddopt;
	}
	public void setAnswAddopt(String answAddopt) {
		this.answAddopt = answAddopt;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	
	
}
