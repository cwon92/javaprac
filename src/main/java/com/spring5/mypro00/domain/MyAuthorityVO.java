package com.spring5.mypro00.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyAuthorityVO {
	
	private String userId;
	private String authority;//고객등급(골드 플래 등등)
	
}
