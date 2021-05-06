package Arkanoid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Barra implements Pintable{

    int x,y;
    final int ancho=200;
    final int alto=29;

    public Barra(int x, int y){
        this.x=x;
        this.y=y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void pintar(Graphics2D g){
        g.setColor(new Color(0,0,255));
        g.fillRect(x,y,ancho,alto);
    }



}