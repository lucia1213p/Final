package kr.or.tech.board.model.vo;

import java.sql.Date;

public class ShrTech {
	private int shareNo; //게시글번호
	private String shareTitle; //게시글제목
	private String shareCont; //내용
	private Date shareDate; //작성일
	private int shareHits; //조회수
	private int memberNo; //작성자번호
	private String memberId; //작성자이름
	private String boardCode; //게시판 코드
	private String fileName; //첨부파일명
	private String shareAddopt; //채택상태
	public ShrTech() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShrTech(int shareNo, String shareTitle, String shareCont, Date shareDate, int shareHits, int memberNo,
			String memberId, String boardCode, String fileName, String shareAddopt) {
		super();
		this.shareNo = shareNo;
		this.shareTitle = shareTitle;
		this.shareCont = shareCont;
		this.shareDate = shareDate;
		this.shareHits = shareHits;
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.boardCode = boardCode;
		this.fileName = fileName;
		this.shareAddopt = shareAddopt;
	}
	public int getShareNo() {
		return shareNo;
	}
	public void setShareNo(int shareNo) {
		this.shareNo = shareNo;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareCont() {
		return shareCont;
	}
	public void setShareCont(String shareCont) {
		this.shareCont = shareCont;
	}
	public Date getShareDate() {
		return shareDate;
	}
	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
	public int getShareHits() {
		return shareHits;
	}
	public void setShareHits(int shareHits) {
		this.shareHits = shareHits;
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
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getShareAddopt() {
		return shareAddopt;
	}
	public void setShareAddopt(String shareAddopt) {
		this.shareAddopt = shareAddopt;
	}
	
	
	
}
