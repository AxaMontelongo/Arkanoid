package Arkanoid;

import java.awt.*;
import java.util.Random;
public class Items  implements Pintable {
        Color color;
        final int ancho=20;
        final int alto=40;
        public int x,y;

        public Items(int x, int y){
            this.x=x;
            this.y=y;
            Random r=new Random();
            color=new Color(r.nextInt(128)
                    ,r.nextInt(024),r.nextInt(115));
        }


        @Override
        public void pintar(Graphics2D g) {
            g.fillRect(x,y,ancho,alto);
        }
    }

