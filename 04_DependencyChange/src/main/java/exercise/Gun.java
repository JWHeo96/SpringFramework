package exercise;

public class Gun implements Weapon{
	
	public Gun() {
		System.out.println("Gun ��ü ����!");
	}
	@Override
	public void useWeapon() {
		System.out.println("Use Gun!");
	}
}
