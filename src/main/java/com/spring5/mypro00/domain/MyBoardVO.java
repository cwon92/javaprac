package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@EqualsAndHashCode
@ToString
//@Data
public class MyBoardVO {
	private long bno ;
	private String btitle ;
	private String bcontent ;
	private String bwriter ;
	private Date bregDate ;
	private Timestamp bmodDate ;
	private int bviewCnt ;
	private int breplyCnt ;
	private int bdelFlag ; //0: 유지, 1: 삭제요청됨

	private List<MyBoardAttachFileVO> attachFileList;
}
