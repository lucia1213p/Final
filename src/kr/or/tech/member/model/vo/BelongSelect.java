package kr.or.tech.member.model.vo;

public class BelongSelect {
	private String BelongCode;
	private String BelongName;
	public BelongSelect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BelongSelect(String belongCode, String belongName) {
		super();
		BelongCode = belongCode;
		BelongName = belongName;
	}
	public String getBelongCode() {
		return BelongCode;
	}
	public void setBelongCode(String belongCode) {
		BelongCode = belongCode;
	}
	public String getBelongName() {
		return BelongName;
	}
	public void setBelongName(String belongName) {
		BelongName = belongName;
	}
	
	
}
