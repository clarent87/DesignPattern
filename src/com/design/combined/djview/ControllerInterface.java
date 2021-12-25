package com.design.combined.djview;


// 뷰에서 쓰이는 전략( 알고리즘 )
// 스트래티지 패턴 쓰려면 interface필요
public interface ControllerInterface {
	void start();
	void stop();
	void increaseBPM();
	void decreaseBPM();
 	void setBPM(int bpm);
}
