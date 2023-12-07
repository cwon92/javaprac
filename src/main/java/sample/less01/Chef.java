package sample.less01;

import org.springframework.stereotype.Component;

import lombok.ToString;

@Component
@ToString
public class Chef {

	private String cname;
	private String cid;
	
	public String getCname() {
		System.out.println("Chef.getCname() 메서드입니다.");
		return cname;
	}
	public void setCname(String cname) {
		System.out.println("Chef.setCname() 메서드입니다.");
		this.cname = cname;
		System.out.println("cname: " + this.cname);
		System.out.println("===========================");
	}
	public String getCid() {
		System.out.println("Chef.getCid() 메서드입니다.");
		return cid;
	}
	public void setCid(String cid) {
		System.out.println("Chef.setCid() 메서드입니다.");
		this.cid = cid;
		System.out.println("cid: " + this.cid);
		System.out.println("===========================");
	}
	
	public Chef() {
		System.out.println("Chef 클래스의 기본생성자입니다.");
	}
	
	public Chef(String cname, String cid) {
		System.out.println("Chef 클래스의 모든 필드 초기화 생성자입니다.");
		this.cname = cname;
		this.cid = cid;
		System.out.println("cname: " + this.cname);
		System.out.println("cid: " + this.cid);
	}
	
	
	
}
