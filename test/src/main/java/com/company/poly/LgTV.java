package com.company.poly;

public class LgTV implements TV {
	private Speaker speaker; //멤버변수로 선언
	
	/*
	 * public LgTV() { speaker = new SonySpeaker(); }
	 */
	public LgTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void turnOn() {
		System.out.println("LGTN - 전원 On");
	}
	public void turnOff() {
		System.out.println("LGTV - 전원 Off");
	}
	public void soundUp() {
		/*  System.out.println("LGTV - 볼륨 Up"); */
		speaker.volumeUp(); //생성한 객체 사용
		}
	public void soundDown() {
		/* System.out.println("LGTV - 볼륨 Down"); */
		speaker.volumeDown();
	}

}
