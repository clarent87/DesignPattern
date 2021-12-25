package com.design.combined.djview;

public class HeartController implements ControllerInterface {

  HeartModelInterface model;
  DJView view;

  public HeartController(HeartModelInterface model) {
    this.model = model;
    view = new DJView(this, new HeartAdapter(model)); // DJVIEW는 원래 beatModel만 받음.. 그래서 어댑터 씀.
    view.createView();
    view.createControls();
    view.disableStopMenuItem();
    view.disableStartMenuItem();
  }

  public void start() {
  }

  public void stop() {
  }

  public void increaseBPM() {
  }

  public void decreaseBPM() {
  }

  public void setBPM(int bpm) {
  }
}



