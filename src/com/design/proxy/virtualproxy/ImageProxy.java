package com.design.proxy.virtualproxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

class ImageProxy implements Icon {

  ImageIcon imageIcon; // 진짜 Icon 객체
  URL imageURL;
  Thread retrievalThread;
  boolean retrieving = false;

  public ImageProxy(URL url) {
    imageURL = url;
  }

	// 이 if문은 state 패턴으로 깔끔하게 할수 있다.
	// ImageProxy 에 State 변수 두고, IconLoading , NotLoading상태 class 만들면 됨
  public int getIconWidth() {
    if (imageIcon != null) {
      return imageIcon.getIconWidth();
    } else {
      return 800;
    }
  }

  public int getIconHeight() {
    if (imageIcon != null) {
      return imageIcon.getIconHeight();
    } else {
      return 600;
    }
  }

  public void paintIcon(final Component c, Graphics g, int x, int y) {
    if (imageIcon != null) {
      imageIcon.paintIcon(c, g, x, y);
    } else {
      g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
      if (!retrieving) {
        retrieving = true;

        retrievalThread = new Thread(new Runnable() {
          public void run() {
            try {
              imageIcon = new ImageIcon(imageURL, "CD Cover");
              c.repaint();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
        retrievalThread.start();
      }
    }
  }
}
