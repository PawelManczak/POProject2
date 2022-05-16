package com.company;

import com.company.Organizmy.Organizm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Organizm[][]mapa;
    private int rozmiarY, rozmiarX;
    private int ROZMIAR_KAFELKI = 50;
    private Swiat swiat;
    private JButton newTurnB, saveB, loadB;


    Screen(Swiat swiat, Organizm[][] mapa, int rozmiarY, int rozmiarX){
        this.mapa = mapa;
        this.rozmiarY = rozmiarY;
        this.rozmiarX = rozmiarX;
        this.swiat = swiat;

        newTurnB = new JButton("nowa tura");
        saveB = new JButton("zapisz");
        loadB = new JButton("wczytaj");

        this.add(newTurnB);
        this.add(saveB);
        this.add(loadB);

        saveB.addActionListener(e -> {
            swiat.zapiszDoPliku();
            saveB.setFocusable(false);
            repaint();
        });
        newTurnB.addActionListener(e -> {
            swiat.wykonajTure("n");
            newTurnB.setFocusable(false);
            repaint();
        });
        loadB.addActionListener(e -> {
            swiat.changeSwiat();

            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
            loadB.setFocusable(false);
            repaint();
        });

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addMouseListener(this);

    }

    public void paint(Graphics g){
        super.paint(g);

        for(int i = 0; i < rozmiarY; i++){
            for(int j = 0; j < rozmiarX; j++){
                if(mapa[j][i] != null)
                g.setColor(mapa[j][i].Color());
                else
                    g.setColor(Color.BLACK);

                g.fillRect((j+1)*ROZMIAR_KAFELKI, (i+1) * ROZMIAR_KAFELKI, ROZMIAR_KAFELKI,ROZMIAR_KAFELKI);
            }
        }

    }
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                swiat.wykonajTure("UP");
                break;
            case KeyEvent.VK_DOWN:
                swiat.wykonajTure("DOWN");
                break;
            case KeyEvent.VK_RIGHT:
                swiat.wykonajTure("RIGHT");
                break;
            case KeyEvent.VK_LEFT:
                swiat.wykonajTure("LEFT");
                break;
            case KeyEvent.VK_SHIFT:
                swiat.wykonajTure("MOC");
                System.out.println("czlowiek");
                break;

        }
      repaint();
    }

    public void mouseClicked(MouseEvent e) {
        //tylko na pustym
        //if (mapa[e.getX() / ROZMIAR_KAFELKI - 1][e.getY() / ROZMIAR_KAFELKI - 1] == null) {
            Lista lista = new Lista(swiat, new Polozenie(e.getX() / ROZMIAR_KAFELKI - 1, e.getY() / ROZMIAR_KAFELKI - 1));
       // }
    }
    public void mouseEntered(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(MouseEvent e){ }
    public void mouseExited(MouseEvent e){ }
    public void mouseReleased(MouseEvent e){ }


}
