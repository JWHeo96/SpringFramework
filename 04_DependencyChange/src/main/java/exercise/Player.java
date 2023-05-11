package exercise;

public class Player {
	
	private Weapon weapon;
	
	Player(){
		
	}
	
	Player(Weapon weapon){
		this.weapon = weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void userPlayerWeapon() {
		weapon.useWeapon();
	}
}
