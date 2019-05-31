import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class RippleBoxGraphics extends JComponent implements MouseMotionListener {
  int WIDTH = 750;
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

        // double midptX = ( (gwd.masses[N*i+j].posX+gwd.masses[N*(i+1)+(j+1)].posX)/2 );
        // double midptY = ( (gwd.masses[N*i+j].posY+gwd.masses[N*(i+1)+(j+1)].posY)/2 );

        // double[] xVals = { (gwd.masses[N*i+j].posX), (gwd.masses[N*i+(j+1)].posX), midptX };
        // double[] yVals = { (gwd.masses[N*i+j].posY), (gwd.masses[N*i+(j+1)].posY), midptY };
        // double[] xVals2 =  { (gwd.masses[N*i+j].posX), (gwd.masses[N*(i+1)+j].posX), midptX };
        // double[] yVals2 = { (gwd.masses[N*i+j].posY),  (gwd.masses[N*(i+1)+j].posY), midptY };
        // double[] xVals3 = { (gwd.masses[N*(i+1)+(j+1)].posX), (gwd.masses[N*(i+1)+j].posX), midptX };
        // double[] yVals3 = { (gwd.masses[N*(i+1)+(j+1)].posY), (gwd.masses[N*(i+1)+j].posY), midptY };
        // double[] xVals4 =  {  (gwd.masses[N*(i+1)+(j+1)].posX),  (gwd.masses[N*i+(j+1)].posX), midptX };
        // double[] yVals4 =  {  (gwd.masses[N*(i+1)+(j+1)].posY),  (gwd.masses[N*i+(j+1)].posY), midptY };

        // float val = (makeColor(xVals,yVals)+makeColor(xVals2,yVals2)+makeColor(xVals3,yVals3)+makeColor(xVals4,yVals4))/((float)4.0);
        // ((Graphics2D)g).setColor( new Color(val,val,val) );

        // g.fillPolygon(castToInt(xVals),castToInt(yVals),3);
        // g.fillPolygon(castToInt(xVals2),castToInt(yVals2),3);
        // g.fillPolygon(castToInt(xVals3),castToInt(yVals3),3);
        // g.fillPolygon(castToInt(xVals4),castToInt(yVals4),3);
      }
    }
  }

  // int[] castToInt(double[] vals) {
  //   int[] result = {(int) vals[0], (int) vals[1], (int) vals[2]};
  //   return result;
  // }

  // float makeColor(double[] xVals,double[] yVals) {
  //   float val = Math.min((float)0.99, (float)Math.pow(triangleArea(xVals,yVals)*(float)(3.0/Math.pow(WIDTH/(N-1),2)),4));
  //   return val;
  // }


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