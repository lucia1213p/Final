package kr.or.tech.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberName;
	private String memberPwd;
	private String memberPhone;
	private String memberAddr;
	private String memberEmail;
	private String memberJoin;
	private String memberActive;
	private char memberGender;
	private String memberGrade;
	private String memCode;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberNo, String memberId, String memberName, String memberPwd, String memberPhone,
			String memberAddr, String memberEmail, String memberJoin, String memberActive, char memberGender,
			String memberGrade, String memCode) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.memberEmail = memberEmail;
		this.memberJoin = memberJoin;
		this.memberActive = memberActive;
		this.memberGender = memberGender;
		this.memberGrade = memberGrade;
		this.memCode = memCode;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberJoin() {
		return memberJoin;
	}
	public void setMemberJoin(String memberJoin) {
		this.memberJoin = memberJoin;
	}
	public String getMemberActive() {
		return memberActive;
	}
	public void setMemberActive(String memberActive) {
		this.memberActive = memberActive;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public String getMemCode() {
		return memCode;
	}
	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}
	
	
}
