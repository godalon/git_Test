package com.mypt.dto;

public class TestPagingDto {
	
	private int totalRecord;	
	private int numPerPage;
	private int pagePerBlock;
	private int totalPage;
	private int totalBlock;
	private int nowPage;
	private int nowBlock;
	private int startPage;
	private int pageStart;
	private int pageEnd;
	
	public TestPagingDto() {}
	
	public TestPagingDto(int totalRecord, int nowPage) 
	{
		this.totalRecord=totalRecord;
		this.numPerPage=9;
		this.pagePerBlock=5;
		this.nowPage=nowPage;
		setPageInfo();
	}
	
	public TestPagingDto(int totalRecord, int numPerPage, int PagePerBlock, int nowPage) 
	{
		this.totalRecord= totalRecord;
		this.numPerPage= numPerPage;
		this.pagePerBlock= PagePerBlock;
		this.nowPage=nowPage;
		setPageInfo();	
	}	

	private void setPageInfo()
	{
//		this.totalPage=(int)Math.ceil((double)totalRecord/numPerPage);
		this.totalPage= totalRecord==0?1:(int)Math.ceil((double)totalRecord/numPerPage);
		this.totalBlock=(int)Math.ceil((double)totalPage/pagePerBlock);
		this.nowBlock=(int)Math.ceil((double)nowPage/pagePerBlock);
//		this.startPage=(nowPage*numPerPage)-numPerPage; //+1 되어야 하지 않나?
		this.startPage=(nowPage*numPerPage)-numPerPage+1; //startBoardNum
		this.pageStart= (nowBlock-1)*pagePerBlock+1; //현 블럭에서의 첫 페이지
//		this.pageEnd=(pageStart+pagePerBlock)<totalPage?pageStart+pagePerBlock:totalPage+1; 
		this.pageEnd=(pageStart+pagePerBlock-1)<totalPage ? pageStart+pagePerBlock-1 : totalPage; //현 블럭에서의 마지막 페이지
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) 
	{		
		this.totalRecord = totalRecord;
		
//		totalPage=(int)Math.ceil((double)totalRecord/numPerPage);
		totalPage= totalRecord==0?1:(int)Math.ceil((double)totalRecord/numPerPage);
		totalBlock=(int)Math.ceil((double)totalPage/pagePerBlock);
		pageEnd=(pageStart+pagePerBlock-1)<totalPage ? pageStart+pagePerBlock-1 : totalPage;	

	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalPage() {
		return totalRecord==0?1:(int)Math.ceil((double)totalRecord/numPerPage);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) 
	{
		this.nowPage = nowPage;
		nowBlock=(int)Math.ceil((double)nowPage/pagePerBlock);
		startPage=(nowPage*numPerPage)-numPerPage+1;
		pageStart= (nowBlock-1)*pagePerBlock+1; 
		pageEnd=(pageStart+pagePerBlock-1)<totalPage ? pageStart+pagePerBlock-1 : totalPage;

	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	
	
	
}
