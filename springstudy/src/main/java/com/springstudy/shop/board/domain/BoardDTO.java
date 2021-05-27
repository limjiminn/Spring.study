package com.springstudy.shop.board.domain;



import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	
	//여러개
	private List<BoardAttachDTO> attachList;
//	{
//		"bno": "",
//		"title" : "",
//		"replycnt" : "",
//		[{"uuid": ""},{"uploadPath":""},{}]
//	}

}
