package com.design.combined.djview;

public class BeatController implements ControllerInterface {

  BeatModelInterface model; // 모델
  DJView view;  // 뷰

  public BeatController(BeatModelInterface model) {
    this.model = model;
    view = new DJView(this, model); // 뷰는 받지 않고 만들어 준다.. 받아도될듯?
    view.createView();
    view.createControls();
    view.disableStopMenuItem();
    view.enableStartMenuItem();
    model.initialize();
  }

  public void start() {
    model.on();
    view.disableStartMenuItem();
    view.enableStopMenuItem();
  }

  public void stop() {
    model.off();
    view.disableStopMenuItem();
    view.enableStartMenuItem();
  }

  public void increaseBPM() {
    int bpm = model.getBPM();
    model.setBPM(bpm + 1);
  }

  public void decreaseBPM() {
    int bpm = model.getBPM();
    model.setBPM(bpm - 1);
  }

  public void setBPM(int bpm) {
    model.setBPM(bpm);
  }
}
