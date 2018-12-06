package kr.or.tech.board.model.vo;

public class SptCategory {
	
	private String categoryCode;
	private String categoryName;
	public SptCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SptCategory(String categoryCode, String categoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
