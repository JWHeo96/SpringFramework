
public class CarTest {

	public static void main(String[] args) {
		
		// Car���� Tire��ü�� ����ϴ� ��� (1) - Constructor
		Tire t1 = new Tire();
		Car c1 = new Car(t1); // b����� �޸� �Ҵ�
		c1.t.size = 19;
		System.out.println("Tire1�� ������� " + c1.t.size);
		
		// Car���� Tire��ü�� ����ϴ� ��� (2) - Setter
		Car c2 = new Car();
		c2.setTire(t1);
		c2.t.size = 29;
		System.out.println("Tire2�� ������� " + c2.t.size);
		
	}

}
