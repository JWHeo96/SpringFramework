package exercise;

public class Spike implements Weapon{
	
	public Spike() {
		System.out.println("Spike ��ü ����!");
	}
	
	@Override
	public void useWeapon() {
		System.out.println("Use Spike!");
	}
}
