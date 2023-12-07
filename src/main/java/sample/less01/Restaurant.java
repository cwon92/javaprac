package sample.less01;

import org.springframework.stereotype.Component;

//@Component
public class Restaurant {
	
	private String name ;
	private Chef chef ;
	
	public String getName() {
		System.out.println("Restaurant.getName() 메서드입니다.");
		return name;
	}
	public void setName(String name) {
		System.out.println("Restaurant.setName() 메서드입니다.");
		this.name = name;
	}
	public Chef getChef() {
		System.out.println("Restaurant.getChef() 메서드입니다.");
		return chef;
	}
	public void setChef(Chef chef) {
		System.out.println("Restaurant.setChef() 메서드입니다.");
		this.chef = chef;
	}
	
	public Restaurant() {
		System.out.println("Restaurant 의 기본생성자입니다.");
	}
	
	public Restaurant(String name, Chef chef) {
		System.out.println("Restaurant 의 모든 필드 초기화 생성자입니다.");
		this.name = name;
		this.chef = chef;
		
		System.out.println("name: " + this.name);
		System.out.println("chef: " + this.chef.toString());
	}
	
	
	
	
	

}
