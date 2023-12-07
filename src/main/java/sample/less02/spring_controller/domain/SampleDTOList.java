package sample.less02.spring_controller.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleDTOList {
	
	private List<SampleDTO> list ; 
	
	public SampleDTOList() {
		this.list = new ArrayList<SampleDTO>();
	}

}
