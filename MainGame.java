package Arkanoid;
import javax.swing.*;
import java.awt.*;


public class MainGame extends JFrame {
    JuegoPrinc princ;
    JFrame ventana;


    public MainGame(){
        princ=new JuegoPrinc();
        ventana=new JFrame("Arkanoid");
        ventana.add(princ, BorderLayout.CENTER);
        ventana.setSize(800,600);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread MainThread=new Thread(princ);
        MainThread.start();
    }
    public static void main(String[] args) {
        new MainGame();
    }
}