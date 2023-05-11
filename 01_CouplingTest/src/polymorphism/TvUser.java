package polymorphism;

public class TvUser {

	public static void main(String[] args) {
		// SamsungTV 클래스 사용하여 구현
		// TV tv = new SamsungTV();
		// LgTV 객체로 수정
		TV tv = new LgTV();
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
