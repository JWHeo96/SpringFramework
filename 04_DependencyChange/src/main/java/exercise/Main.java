package exercise;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Gun gun = new Gun();
		// new Gun, new Knife, new Spike
		Weapon weapon = new Gun();
		
		Player player = new Player(weapon);
		
		// 무기 변경 출력
		player.userPlayerWeapon();
		
		
	}
}
