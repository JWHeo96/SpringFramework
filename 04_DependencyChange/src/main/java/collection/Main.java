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
		// List 데이터 확인
		for(String addr :addrList) {
			System.out.println(addr);
		}
		System.out.println(addrList);

		// SET 데이터 확인
		Set<String> movies = bean.getMovieList();
		Iterator<String> it = movies.iterator();
		
		while(it.hasNext()) {	// 다음 처리할 데이터 확인
			System.out.println(it.next());
		}
		
		// MAP 데이터 확인
		Map<String, String> scoreMap = bean.getPrefList();
		
		// 키에 대한 목록을 조회
		Set<String> keySet = scoreMap.keySet();
		// 각 키에 대한 값을 알아낸다.
		for(String key : keySet) {
			System.out.println(key + " : " + scoreMap.get(key));
		}
		
		// Properties 타입 의존성 주입
		/*
		 * Properties props = bean.getPropList(); Set<String> items =
		 * (Set)(props.keySet());
		 * 
		 * for(String item : items) { System.out.println(item + ":" +
		 * props.getProperty(item)); }
		 */
		
		

	}
}
