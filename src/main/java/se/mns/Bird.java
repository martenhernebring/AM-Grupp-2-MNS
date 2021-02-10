import java.awt.Color;

import javax.swing.*;
public class Bird extends Rectangle{

    public void paintShape(Graphics g) {
        super.paintShape(g);
        this.setBackgrond(Color.DARK_GRAY);

        Grafics2D g2D = (Grafics2D) g;
        int[]xPoints = {50,100,150,200,250,300,350};
        int[]yPoints = {350,250,250,200,150,250,350};
        int nPoints = xPoints.length;
        int[] x = {100,200,300};
        int [] y = {100, 200, 300};

//* Drawing a Polygram*/
        g2D.setColor(Color.ORANGE);
        g2D.drawPolyline(xPoints, yPoints, nPoints);
        g2D.set.Stroke(new BasicStoke(20));
        g2D.drawLine(200, 200, 400, 400);


        g2D.setColor(Color.GREEN);
        g2D.drawPolygon(x, y,3);
        g2D.fillPolygon(x,y,3);
    }
}