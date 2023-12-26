package com.spring5.mypro00.domain;

import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
public class MyAuthorityVO {
	
	private String userId ;
	private String authority ;  //고객등급(Gold, Silver, Bronze, VIP,...)

}
