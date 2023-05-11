package exercise;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");

		Player player = (Player)container.getBean("player");
		
		player.userPlayerWeapon();
		
		// 스프링 컨테이너 종료
		container.close();
	}
}
