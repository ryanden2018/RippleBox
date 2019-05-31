import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;

class RippleBoxGraphics extends JComponent implements MouseMotionListener {
  int WIDTH = 600;
  int HEIGHT = WIDTH;
  int N = 300;
  double DT = 0.1;
  RippleBoxData rbd;

  RippleBoxGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    rbd = new RippleBoxData(N,DT,WIDTH);
    addMouseMotionListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for(int i=0; i<(int)(1/DT); i++) {
      rbd.update();
    }

      BufferedImage img = new BufferedImage(N,N,BufferedImage.TYPE_BYTE_GRAY);
      WritableRaster ras = img.getRaster();
      ras.setPixels(0,0,N,N,vals());
      ((Graphics2D)g).drawImage(img.getScaledInstance(N*2,N*2,Image.SCALE_DEFAULT),0,0,this);
  }

  double[] vals() {
    double[] result = new double[N*N];
    for(int i=0; i<N*N; i++) {
      result[i] =255*Math.min(0.99,Math.max(0.01,rbd.values[i]-0.5));
    }
    return result;
  }

  public void mouseMoved(MouseEvent e) {
    rbd.cursorX = e.getX();
    rbd.cursorY = e.getY();
  }

  public void mouseDragged(MouseEvent e) {
    rbd.cursorX = e.getX();
    rbd.cursorY = e.getY();
  }

  public void mouseExited(MouseEvent e) { }

  public void mouseEntered(MouseEvent e) { }

  public void mouseReleased(MouseEvent e) { }

  public void mousePressed(MouseEvent e) { }

  public void mouseClicked(MouseEvent e) { }
}