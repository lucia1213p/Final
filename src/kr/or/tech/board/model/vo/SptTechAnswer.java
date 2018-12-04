package kr.or.tech.board.model.vo;

import java.sql.Date;

public class SptTechAnswer {
	private int answNo;
	private String answCont;
	private Date answDate;
	private int sptNo;
	private int memberNo; //작성자
	private String memberId;
	private String answActive; 
	private String boardCode;
	public SptTechAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SptTechAnswer(int answNo, String answCont, Date answDate, int sptNo, int memberNo, String memberId,
			String answActive, String boardCode) {
		super();
		this.answNo = answNo;
		this.answCont = answCont;
		this.answDate = answDate;
		this.sptNo = sptNo;
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.answActive = answActive;
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
	public int getSptNo() {
		return sptNo;
	}
	public void setSptNo(int sptNo) {
		this.sptNo = sptNo;
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
	public String getAnswActive() {
		return answActive;
	}
	public void setAnswActive(String answActive) {
		this.answActive = answActive;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	
}
