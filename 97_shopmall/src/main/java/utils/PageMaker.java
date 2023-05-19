package utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private Criteria cri;	
	private int totalCount;	// �� �Խñ��� ��
	private int startPage;	// ȭ�鿡 ������ ù��° ������ ��ȣ
	private int endPage;	// ȭ�鿡 ������ ������ ������ ��ȣ
	private boolean prev;	// <����> ��ư ǥ�� ����
	private boolean next;	// <����> ��ư ǥ�� ����
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
		
		// ������� �ʱ�ȭ
		fieldInit();
	}
	
	/*
	 * �� ������ �ѹ��� ����
		����: 3������
		1) 3 / 10(������ ��ư�� ��) => �ø�(0.xxxx)
		2) 1 * 10(������ ��ư�� ��) = 10 
		
		ex) 12 / 10 => (�ø�)1.2 => 2 * 10 = 20
		
		> ���� ������ ������
		�� �Խñ��� ��: 77
		Math.ceil(77 / (double)10(�������� �Խñ��� ��)) => (�ø�)7.7 => 8
	 */
	public void fieldInit() {
		// (1) ǥ���� �� ������ ��ȣ ���
		// �� ������ ��ȣ = �ø�(���� ������ ��ȣ / ȭ��� ������ ��ư�� ��) * ȭ��� ������ ��ư�� ��
		endPage = (int)(Math.ceil(cri.getPageNum() / (double)cntPageNum)
				* cntPageNum);	// Math.ceil: �Ǽ� ���� õ��(�ø� ��)
		
		// (2) ���� ������ ��ȣ ���
		// ���� ��������ȣ = �� ������ ��ȣ - ȭ��� �������� ��ư�� �� + 1 
		startPage = endPage - cntPageNum + 1;
		
		// (3) ���� �� ��������ȣ ���
		realEndPage = (int)(Math.ceil(totalCount / (double)cri.getRowsPerPage()));
		
		// (4) ���� �� ������ ��ȣ �� ����
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// (5) ����, ���� ��ư ǥ�� ���� ����
		// ���� ������ ��ȣ�� 1�� ��� '���� ��ư �ʿ� ����.
		prev = (startPage == 1 ? false : true);
		next = (endPage * cri.getRowsPerPage() < totalCount ? true : false);
	}
	
	/*
	 * �Է� �Ķ����: Ŭ���� ������ ��ȣ
	 * ���: QueryString���� ������ ��ȣ�� �������� �׸���� ����� �ִ� �޼ҵ�
	 * ��: 3������ Ŭ��
	 * 		?pageNum=3&rowsPerPage=10
	 */
	public String makeQuery(int page) {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("pageNum", page)
				.queryParam("rowsPerPage", cri.getRowsPerPage())
				.build();
		
		return uri.toString();
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
