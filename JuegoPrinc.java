package Arkanoid;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class JuegoPrinc extends JPanel implements Runnable, MouseMotionListener {

    ArrayList<Ladrillo> ladrillos;
    Barra barra;
    Bolita bolita;
    Bolita bolita2;
    Items item;
    Graphics2D g;
    public int Dy;
    public int DyItem;
    public int Dx;
    public int DxBolita;
    boolean jugando=true;
    int contadorniveles=4;
    int auxTiempo=8;
    int auxTiempo2=0;
    int tiempo=4;
    int vidas=3;
    int auxiliarVidas;
    int puntuaje;
    int nuevo;
    int contadorItem;
    int auxContadorItem;
    String nombre;
    boolean auxJugando;
    String niveles[]=new String[tiempo];

    public  JuegoPrinc(){
       niveles[0] ="Fácil";
       niveles[1] ="Medio";
       niveles[2] ="Dificil";
       niveles[3] ="Leyenda";
        nombre="";
        contadorItem=0;
        auxJugando=true;
        Dy=-1;
        DyItem=-1;
        Dx=1;
        DxBolita=1;
        puntuaje=15;
        auxiliarVidas=vidas;
        barra=new Barra(400,540);
        bolita=new Bolita(400,510);
        bolita2=new Bolita(400,-40);
        item=new Items(400,-40);
        //Fila de ladrillos
        ladrillos=new ArrayList<>();
        for(int j=0;j<contadorniveles;j++){
            for(int i=0;i<800/50;i++){
                ladrillos.add(new Ladrillo(1+50*i,j*20,
                        50,20));
                contadorItem++;
            }
        }
        auxContadorItem=contadorItem/2;
        addMouseMotionListener(this);

    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g=(Graphics2D) g;

        try {
            for (Ladrillo l : ladrillos) {
                l.pintar(this.g);
            }
        }catch (Exception e){

        }

        barra.pintar(this.g);
        bolita.pintar(this.g);
        bolita2.pintar(this.g);
        item.pintar(this.g);
    }


    @Override
    public void run() {

            while (jugando) {
                try {
                    //Nombre del jugador, //Dificultad
                    if(auxJugando==true) {
                        nombre = JOptionPane.showInputDialog("Ingresa tu Nombre: ");
                        String resp = (String) JOptionPane.showInputDialog(null, "Seleccione La dificultad", "Dificultad", JOptionPane.DEFAULT_OPTION, null, niveles, niveles[0]);
                        System.out.println(resp);
                        if (resp == "Fácil") {
                            tiempo = 5;
                        } else if (resp == "Medio") {
                            tiempo = 3;
                        } else if (resp == "Dificil") {
                            tiempo = 2;
                        } else if (resp == "Leyenda") {
                            tiempo = 1;
                        } else
                            JOptionPane.showMessageDialog(null, "No has seleccionado ningun nivel");
                        auxJugando=false;
                        auxTiempo2=tiempo;
                        JOptionPane.showMessageDialog(null, "Bienvenido " + nombre+"\nDiuficultad: "+resp);
                    }


                    //Movimiento de la bolita
                    Thread.sleep(tiempo);
                    bolita.x += Dx;
                    if ((bolita.y += Dy) > 0)
                        bolita.y += Dy;

                    //Salida del Item
                    if(contadorItem<=auxContadorItem ) {
                        item.y -= DyItem;
                    }

                    //Colision del Item con la barra, activacion del Item
                    if (item.x >= barra.x &&
                            item.x < barra.x + barra.ancho
                            && item.y == barra.y) {
                        tiempo=auxTiempo;
                        System.out.println("Haz obtenido un Item");
                    }
                    //Tiempo del item en juego
                    if(auxContadorItem-10==contadorItem){
                        tiempo=auxTiempo2;
                        contadorItem=auxContadorItem*2;
                    }

                    /*colision con las orillas*/
                    if (bolita.x >= getWidth() || bolita.x <= 0) {
                        Dx *= -1;

                    }
                    if (bolita.y <= 0) {
                        Dy *= -1;
                    }
                    /*colision con la barra*/
                    if (bolita.x >= barra.x &&
                            bolita.x < barra.x + barra.ancho
                            && bolita.y == barra.y) {
                        Dy *= -1;
                    }
                    //Vidas
                    if (bolita.y > barra.y) {
                        vidas--;
                        bolita.x=400;
                        bolita.y=510;
                        Thread.sleep(3000);
                    }
                    /*colision con los bloques*/
                    Iterator<Ladrillo> it = ladrillos.iterator();
                    while (it.hasNext()) {
                        Ladrillo ladrillo = it.next();
                        if (bolita.x > ladrillo.getX() &&
                                bolita.x < ladrillo.getX() + ladrillo.getAncho()
                                && bolita.y == ladrillo.getY()) {
                            it.remove();//elimino el ladrillo
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            Dy *= -1;
                            puntuaje+=10;
                            contadorItem--;
                        }

                    }

                    //Juego ganada
                    if(contadorniveles==30){
                        JOptionPane.showMessageDialog(null,"¡¡¡Felicidades Has Ganado El Juego!!!\n"+"Puntuaje: "+puntuaje);

                    }
                    //Juego perdido
                    if(vidas==0){
                        JOptionPane.showMessageDialog(null,"¡¡¡HAS PERDIDO JAJAJA!!!!!\n"+"Puntuaje: "+puntuaje);

                    }

                    //Nivel Nuevo
                    if(ladrillos.isEmpty()) {
                        contadorniveles++;
                        //for para volver a llenar el array list
                            //CAmbiar el valor de j para agregar nuevos niveles
                            for(int j=0;j<contadorniveles;j++){
                                for(int i=0;i<800/50;i++){
                                    ladrillos.add(new Ladrillo(1+50*i,j*20,
                                            50,20));
                                }
                            }
                            bolita.x=400;
                            bolita.y=510;
                        Thread.sleep(3000);

                            }
                        //Juego nuevo
                    if (vidas==0||contadorniveles ==30){
                        nuevo = JOptionPane.showConfirmDialog(null, "¿Quieres volver a Jugar?");
                        if (nuevo == 0) {
                            vidas=auxiliarVidas;
                            auxJugando=true;
                            puntuaje=15;
                            contadorniveles=4;
                            //Pintar los bloques
                            for(int j=0;j<contadorniveles;j++){
                                for(int i=0;i<800/50;i++){
                                    ladrillos.add(new Ladrillo(1+50*i,j*20,
                                            50,20));
                                }
                            }
                        }else{
                            jugando=false;
                            JOptionPane.showMessageDialog(null,"¡¡ADIOS!!"+nombre);
                        }


                        }

                   repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Termina el while

    }
    @Override
    public void mouseDragged(MouseEvent e) {    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getX()<590){
            barra.x=e.getX();
            repaint();
        }
    }
}