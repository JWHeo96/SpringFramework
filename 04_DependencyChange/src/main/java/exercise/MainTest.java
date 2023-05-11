package exercise;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");

		Player player = (Player)container.getBean("player");
		
		player.userPlayerWeapon();
		
		// ������ �����̳� ����
		container.close();
	}
}
