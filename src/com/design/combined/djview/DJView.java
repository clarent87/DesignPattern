package com.design.combined.djview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// 이게 UI 쪽.
public class DJView implements ActionListener, BeatObserver, BPMObserver {

  BeatModelInterface model; // 모델에 대한 ref. getBPM이랑 모델의 구독 기능만 이용
  ControllerInterface controller; // 컨트롤러 ref
  JFrame viewFrame;
  JPanel viewPanel;
  BeatBar beatBar;
  JLabel bpmOutputLabel;

  // 아래는 사용자 interface용
  JFrame controlFrame;
  JPanel controlPanel;
  JLabel bpmLabel;
  JTextField bpmTextField;
  JButton setBPMButton;
  JButton increaseBPMButton;
  JButton decreaseBPMButton;
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem startMenuItem;
  JMenuItem stopMenuItem;

  public DJView(ControllerInterface controller, BeatModelInterface model) {
    this.controller = controller;
    this.model = model;
    // 옵저버 등록
    model.registerObserver((BeatObserver) this);
    model.registerObserver((BPMObserver) this);
  }

  public void createView() {
    // Create all Swing components here
    viewPanel = new JPanel(new GridLayout(1, 2));
    viewFrame = new JFrame("View");
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewFrame.setSize(new Dimension(100, 80));
    bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
    beatBar = new BeatBar();
    beatBar.setValue(0);
    JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
    bpmPanel.add(beatBar);
    bpmPanel.add(bpmOutputLabel);
    viewPanel.add(bpmPanel);
    viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
    viewFrame.pack();
    viewFrame.setVisible(true);
  }


  // 버튼 같은곳에 listener세팅. 이떄 익명class를 쓰거나 그냥 this를 넘김
  public void createControls() {
    // Create all Swing components here
    JFrame.setDefaultLookAndFeelDecorated(true);
    controlFrame = new JFrame("Control");
    controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    controlFrame.setSize(new Dimension(100, 80));

    controlPanel = new JPanel(new GridLayout(1, 2));

    menuBar = new JMenuBar();
    menu = new JMenu("DJ Control");
    startMenuItem = new JMenuItem("Start");
    menu.add(startMenuItem);
    startMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        controller.start();
      }
    });
    stopMenuItem = new JMenuItem("Stop");
    menu.add(stopMenuItem);
    stopMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        controller.stop();
      }
    });
    JMenuItem exit = new JMenuItem("Quit");
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });

    menu.add(exit);
    menuBar.add(menu);
    controlFrame.setJMenuBar(menuBar);

    bpmTextField = new JTextField(2);
    bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
    setBPMButton = new JButton("Set");
    setBPMButton.setSize(new Dimension(10, 40));
    increaseBPMButton = new JButton(">>");
    decreaseBPMButton = new JButton("<<");
    setBPMButton.addActionListener(this);
    increaseBPMButton.addActionListener(this);
    decreaseBPMButton.addActionListener(this);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

    buttonPanel.add(decreaseBPMButton);
    buttonPanel.add(increaseBPMButton);

    JPanel enterPanel = new JPanel(new GridLayout(1, 2));
    enterPanel.add(bpmLabel);
    enterPanel.add(bpmTextField);
    JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
    insideControlPanel.add(enterPanel);
    insideControlPanel.add(setBPMButton);
    insideControlPanel.add(buttonPanel);
    controlPanel.add(insideControlPanel);

    bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    controlFrame.getRootPane().setDefaultButton(setBPMButton);
    controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

    controlFrame.pack();
    controlFrame.setVisible(true);
  }

  public void enableStopMenuItem() {
    stopMenuItem.setEnabled(true);
  }

  public void disableStopMenuItem() {
    stopMenuItem.setEnabled(false);
  }

  public void enableStartMenuItem() {
    startMenuItem.setEnabled(true);
  }

  public void disableStartMenuItem() {
    startMenuItem.setEnabled(false);
  }

  // 사용자가 increase, decrease, set button 호출시 사용됨
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == setBPMButton) {
      int bpm = Integer.parseInt(bpmTextField.getText());
      controller.setBPM(bpm);
    } else if (event.getSource() == increaseBPMButton) {
      controller.increaseBPM();
    } else if (event.getSource() == decreaseBPMButton) {
      controller.decreaseBPM();
    }
  }

  // 옵저버로 this가 등록되었기 때문에.
  // update에 대한 로직은 컨트롤러 없이 여기서 처리하나봄..
  public void updateBPM() {
    if (model != null) {
      int bpm = model.getBPM();
      if (bpm == 0) {
        if (bpmOutputLabel != null) {
          bpmOutputLabel.setText("offline");
        }
      } else {
        if (bpmOutputLabel != null) {
          bpmOutputLabel.setText("Current BPM: " + model.getBPM());
        }
      }
    }
  }

  public void updateBeat() {
    if (beatBar != null) {
      beatBar.setValue(100);
    }
  }
}
