package utils;

public class PageMaker {
	
	private Criteria cri;	
	private int totalCount;	// 총 게시글의 수
	private int startPage;	// 화면에 보여질 첫번째 페이지 번호
	private int endPage;	// 화면에 보여질 마지막 페이지 번호
	private boolean prev;	// <이전> 버튼 표시 여부
	private boolean next;	// <다음> 버튼 표시 여부
	private int cntPageNum = 10;
	private int realEndPage;
	
	// Getter&Setter&ToString
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		// 멤버변수 초기화
		fieldInit();
	}
	
	/*
	 * ㅇ 페이지 넘버링 예시
		현재: 3페이지
		1) 3 / 10(페이지 버튼의 수) => 올림(0.xxxx)
		2) 1 * 10(페이지 버튼의 수) = 10 
		
		ex) 12 / 10 => (올림)1.2 => 2 * 10 = 20
		
		> 실제 마지막 페이지
		총 게시글의 수: 77
		Math.ceil(77 / (double)10(페이지당 게시글의 수)) => (올림)7.7 => 8
	 */
	public void fieldInit() {
		// (1) 표시할 끝 페이지 번호 계산
		// 끝 페이지 번호 = 올림(현재 페이지 번호 / 화면당 페이지 버튼의 수) * 화면당 페이지 버튼의 수
		endPage = (int)(Math.ceil(cri.getPageNum() / (double)cntPageNum)
				* cntPageNum);	// Math.ceil: 실수 값의 천장(올림 값)
		
		// (2) 시작 페이지 번호 계산
		// 시작 페이지번호 = 끝 페이지 번호 - 화면당 페이지당 버튼의 수 + 1 
		startPage = endPage - cntPageNum + 1;
		
		// (3) 실제 끝 페이지번호 계산
		realEndPage = (int)(Math.ceil(totalCount / (double)cri.getRowsPerPage()));
		
		// (4) 실제 끝 페이지 번호 값 수정
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// (5) 이전, 다음 버튼 표시 여부 결정
		// 시작 페이지 번호가 1일 경우 '이전 버튼 필요 없음.
		prev = (startPage == 1 ? false : true);
		next = (endPage * cri.getRowsPerPage() < totalCount ? true : false);
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCntPageNum() {
		return cntPageNum;
	}
	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}
	public int getRealEndPage() {
		return realEndPage;
	}
	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", cntPageNum=" + cntPageNum + ", realEndPage="
				+ realEndPage + "]";
	}
}
