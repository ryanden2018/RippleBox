import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

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


    for(int i=0; i < N; i++) {
      for(int j=0; j < N; j++) {
        
        ((Graphics2D)g).setColor(
          new Color(
            (float)Math.sqrt(Math.min((float)0.99f, Math.max(0.01f,0.25f*(float)rbd.values[N*i+j]))),
            (float)Math.sqrt(Math.min((float)0.99f, Math.max(0.01f,0.25f*(float)rbd.values[N*i+j]))),
            (float)Math.sqrt(Math.min((float)0.99f, Math.max(0.01f,0.25f*(float)rbd.values[N*i+j])))
          )
        );

        int[] xVals = { 2*j, 2*(j+1), 2*(j+1), 2*j };
        int[] yVals = { 2*i, 2*i, 2*(i+1), 2*(i+1) };

        g.fillPolygon(xVals,yVals,4);
      }
    }
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