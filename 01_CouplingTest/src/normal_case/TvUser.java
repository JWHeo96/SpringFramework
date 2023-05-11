package normal_case;

public class TvUser {

	public static void main(String[] args) {
		// SamsungTV 클래스를 사용하여 구현
		/*
		SamsungTV tv = new SamsungTV();
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		*/
		
		// LgTV 클래스를 사용하여 구현
		// TV 객체를 SamsungTV에서 LgTV로 바꿀 경우
		// 객체 생성과 메소드 호출 모두 프로그래머가 변경해야 함.
		LgTV tv = new LgTV();
		
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
	}
}
