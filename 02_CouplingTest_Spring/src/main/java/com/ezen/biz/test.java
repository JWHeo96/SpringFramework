package com.ezen.biz;

public class test {

	public static void main(String[] args) {
		
		Tire t = new Tire();
		Car c = new Car(t); // b����� �޸� �Ҵ�
		c.t.size = 19;
		System.out.println(c.t.size);
		
		// �Ǵ�
		Car c1 = new Car();
		c1.setTire(new Tire());
		c1.t.size = 29;
		System.out.println(c1.t.size);
		
	}
}

class Car {
	Tire t = new Tire();	
	public Car() {
		
	}
	public Car(Tire t) {
		this.t = t;
	}
	public void setTire(Tire t) {
		this.t = t;
	}
}
class Tire{
	public Tire() {}
	int size;
}
