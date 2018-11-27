package kr.or.tech.board.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo; //공지사항 글 번호
	private String noticeTitle; //공지사항 글 제목
	private String noticeContent; //공지사항 글 내용
	private Date noticeDate; //글 작성일
	private int noticeHits; //글 조회수
	private int memberNo; //작성자 회원번호
	private String memberName; //작성자이름
	private String boardCode; //게시판코드
	private String noticeGrade; //공지사항 등급(긴급, 일반, 중요 등등)
	private String noticeGradeName; //등급이름
	private String noticeFile; //첨부파일이름
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeDate, int noticeHits, int memberNo,
			String memberName, String boardCode, String noticeGrade, String noticeGradeName, String noticeFile) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.noticeHits = noticeHits;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.boardCode = boardCode;
		this.noticeGrade = noticeGrade;
		this.noticeGradeName = noticeGradeName;
		this.noticeFile = noticeFile;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getNoticeHits() {
		return noticeHits;
	}
	public void setNoticeHits(int noticeHits) {
		this.noticeHits = noticeHits;
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
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getNoticeGrade() {
		return noticeGrade;
	}
	public void setNoticeGrade(String noticeGrade) {
		this.noticeGrade = noticeGrade;
	}
	public String getNoticeGradeName() {
		return noticeGradeName;
	}
	public void setNoticeGradeName(String noticeGradeName) {
		this.noticeGradeName = noticeGradeName;
	}
	public String getNoticeFile() {
		return noticeFile;
	}
	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}
	
	

}
