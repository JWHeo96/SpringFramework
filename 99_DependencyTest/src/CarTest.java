
public class CarTest {

	public static void main(String[] args) {
		
		// Car내의 Tire객체를 사용하는 방법 (1) - Constructor
		Tire t1 = new Tire();
		Car c1 = new Car(t1); // b멤버의 메모리 할당
		c1.t.size = 19;
		System.out.println("Tire1의 사이즈는 " + c1.t.size);
		
		// Car내의 Tire객체를 사용하는 방법 (2) - Setter
		Car c2 = new Car();
		c2.setTire(t1);
		c2.t.size = 29;
		System.out.println("Tire2의 사이즈는 " + c2.t.size);
		
	}

}
