import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class RippleBox implements ActionListener {
  JFrame jfrm;
  RippleBoxGraphics rbg;

  RippleBox() {
    jfrm = new JFrame("RippleBox");
    rbg = new RippleBoxGraphics();

    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setBackground(Color.gray);
    jfrm.add(rbg, BorderLayout.CENTER);
    jfrm.pack();
    jfrm.setResizable(false);
    jfrm.setVisible(true);

    int delay = 200;
    Timer timer = new Timer(delay,this);
    while(true) {
      timer.start();
    }
  }

  public void actionPerformed(ActionEvent e) {
    rbg.repaint();
  }

  public static void main(String args[]) {
    new RippleBox();
  }
}