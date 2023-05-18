package utils;

/*
 * ���� �������� ���õ� ���� ����
 * - ���� ������ ��ȣ
 * - �������� �Խñ��� ��
 */
public class Criteria {
	
	private int pageNum;	// ���� ������ ��ȣ
	private int rowsPerPage;	// �������� �Խñ��� ��
	
	public Criteria() {
		this(1,10);	// �ٸ� ������ ȣ��
	}
	
	public Criteria(int pageNum, int rowsPerPage) {
		super();
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum <= 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		if (rowsPerPage <= 0 || rowsPerPage > 20) {
			this.rowsPerPage = 20;
		} else {
			this.rowsPerPage = rowsPerPage;
		}
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
	
	
}