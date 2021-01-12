package com.company.poly;

public class TVUser {

	public static void main(String[] args) {
		//다형성
		
		/* TV lg = new SamsungTV(); */
		TV lg = new LgTV(new AppleSpeaker());
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
	}

}
