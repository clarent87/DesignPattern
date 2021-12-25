package com.design.combined.djview;

public interface BeatModelInterface {

	// ** 컨트롤러에서 모델에게 사용자 입력을 전달하기 위한 메소드 **
  void initialize();

  void on();

  void off();

	void setBPM(int bpm);

	// ***뷰와, 컨트롤러에서 상태를 확인하거나, 옵저버 등록/삭제를 위한 메소드

	int getBPM();

  void registerObserver(BeatObserver o);

  void removeObserver(BeatObserver o);

  void registerObserver(BPMObserver o);

  void removeObserver(BPMObserver o);
}
