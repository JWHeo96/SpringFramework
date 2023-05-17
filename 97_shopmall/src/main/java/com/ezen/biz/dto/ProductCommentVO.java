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
	private int pseq;	// 상품 일련번호
	private String content;	// 상품평 내용
	private String writer;	// 작성자
	private Date regdate;	// 작성일
	private	Date modifydate; // 수정일
}
