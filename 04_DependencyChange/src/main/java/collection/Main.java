package collection;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean = (CollectionBean)container.getBean("collectionBean");
		
		List<String> addrList = bean.getAddressList();
		// List ������ Ȯ��
		for(String addr :addrList) {
			System.out.println(addr);
		}
		System.out.println(addrList);

		// SET ������ Ȯ��
		Set<String> movies = bean.getMovieList();
		Iterator<String> it = movies.iterator();
		
		while(it.hasNext()) {	// ���� ó���� ������ Ȯ��
			System.out.println(it.next());
		}
		
		// MAP ������ Ȯ��
		Map<String, String> scoreMap = bean.getPrefList();
		
		// Ű�� ���� ����� ��ȸ
		Set<String> keySet = scoreMap.keySet();
		// �� Ű�� ���� ���� �˾Ƴ���.
		for(String key : keySet) {
			System.out.println(key + " : " + scoreMap.get(key));
		}
		
		// Properties Ÿ�� ������ ����
		/*
		 * Properties props = bean.getPropList(); Set<String> items =
		 * (Set)(props.keySet());
		 * 
		 * for(String item : items) { System.out.println(item + ":" +
		 * props.getProperty(item)); }
		 */
		
		

	}
}
