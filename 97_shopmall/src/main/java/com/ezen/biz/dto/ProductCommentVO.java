package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentVO {
	private int comment_seq;
	private int pseq;	// ��ǰ �Ϸù�ȣ
	private String content;	// ��ǰ�� ����
	private String writer;	// �ۼ���
	private Date regdate;	// �ۼ���
	private	Date modifydate; // ������
}
