package kr.or.tech.board.model.vo;

import java.sql.Date;

public class SupportTech {
	private int boardNo; //게시글번호
	private String title; //제목
	private String contents; //내용
 	private Date date; //작성날짜
	private int hits; //조회수
	private int partnerNo; //협력사 담당자(게시글작성자)
	private String partnerId;
	private String partnerCode;
	private String boardCode; //게시판코드
	private String fileName; //파일명
	private String stateCode; //진행상황코드
	private String stateName; //진행상황명 
	private int mClerkNo; //제조사 담당자
	private String mClerkId; //제조사담당자아이디
	private String mClerkCode; //소속코드
	public SupportTech() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SupportTech(int boardNo, String title, String contents, Date date, int hits, int partnerNo, String partnerId,
			String partnerCode, String boardCode, String fileName, String stateCode, String stateName, int mClerkNo,
			String mClerkId, String mClerkCode) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.hits = hits;
		this.partnerNo = partnerNo;
		this.partnerId = partnerId;
		this.partnerCode = partnerCode;
		this.boardCode = boardCode;
		this.fileName = fileName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.mClerkNo = mClerkNo;
		this.mClerkId = mClerkId;
		this.mClerkCode = mClerkCode;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getPartnerNo() {
		return partnerNo;
	}
	public void setPartnerNo(int partnerNo) {
		this.partnerNo = partnerNo;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
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
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getmClerkNo() {
		return mClerkNo;
	}
	public void setmClerkNo(int mClerkNo) {
		this.mClerkNo = mClerkNo;
	}
	public String getmClerkId() {
		return mClerkId;
	}
	public void setmClerkId(String mClerkId) {
		this.mClerkId = mClerkId;
	}
	public String getmClerkCode() {
		return mClerkCode;
	}
	public void setmClerkCode(String mClerkCode) {
		this.mClerkCode = mClerkCode;
	}
	
	
	
}
