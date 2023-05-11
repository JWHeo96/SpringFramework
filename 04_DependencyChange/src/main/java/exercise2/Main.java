package exercise2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		ColorManager colorManager = (ColorManager)container.getBean("colorManager");
		
		colorManager.printColor();
		
		container.close();
	}
}
