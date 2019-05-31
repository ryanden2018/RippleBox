import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;

class RippleBoxGraphics extends JComponent implements MouseMotionListener {
  int WIDTH = 600;
  int HEIGHT = WIDTH;
  int N = 600;
  double DT = 0.2;
  RippleBoxData rbd;
  //int l;

  RippleBoxGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    rbd = new RippleBoxData(N,DT,WIDTH);
    addMouseMotionListener(this);
    //l = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    //l++;
    super.paintComponent(g);

      rbd.update();

      BufferedImage img = new BufferedImage(N,N,BufferedImage.TYPE_BYTE_GRAY);
      WritableRaster ras = img.getRaster();
      ras.setPixels(0,0,N,N,vals());
      ((Graphics2D)g).drawImage(img,0,0,this);
  }

  double[] vals() {
    double[] result = new double[N*N];
    for(int i=0; i<N*N; i++) {
      result[i] =255*Math.min(0.99,Math.max(0.01,0.5+0.1*(rbd.values[i]-0.5)));
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