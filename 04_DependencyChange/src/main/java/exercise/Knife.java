package exercise;

public class Knife implements Weapon{
	
	public Knife() {
		System.out.println("Knife ��ü ����!");
	}
	@Override
	public void useWeapon() {
		System.out.println("Use Knife!");
	}
}
