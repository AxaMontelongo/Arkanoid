package Arkanoid;

import java.awt.*;
import java.util.Random;

public class Bolita implements Pintable {
    Color color;
    final int ancho=20;
    final int alto=20;
    public int x,y;

    public Bolita(int x, int y){
        this.x=x;
        this.y=y;
        Random r=new Random();
        color=new Color(r.nextInt(255)
                ,r.nextInt(255),r.nextInt(255));
    }


    @Override
    public void pintar(Graphics2D g) {
        g.fillOval(x,y,ancho,alto);
    }
}