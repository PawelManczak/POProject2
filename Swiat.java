package com.company;

import com.company.Organizmy.Organizm;
import com.company.Organizmy.Rosliny.*;
import com.company.Organizmy.Zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Swiat {
    private int rozmiarX, rozmiarY;
    private int runda;
    private Organizm[][] mapa;
    JFrame frame;
    private String key;


    private void setVariables(int rozmiarX, int rozmiarY){
        this.rozmiarX = rozmiarX;
        this.rozmiarY = rozmiarY;
        mapa = new Organizm[rozmiarX][rozmiarY];
        this.runda = 1;
        this.key= "DOWN";
    }
    private void setFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,1,0,0));
        frame.setSize(1000,1000);
        Screen s = new Screen(this, mapa, rozmiarY, rozmiarX);
        frame.add(s);
        s.setVisible(true);

        frame.setVisible(true);
    }
    Swiat(int rozmiarX, int rozmiarY) {

        setVariables(rozmiarX, rozmiarY);
        setFrame();
    }

    public String getKey() {
        return key;
    }

    public int getRozmiarX() {
        return rozmiarX;
    }

    public int getRozmiarY() {
        return rozmiarY;
    }

    public Organizm[][] getMapa() {
        return mapa;
    }

    public void dodajOrganizm(Polozenie p, Organizm organizm) {
        mapa[p.x][p.y] = organizm;
    }
    public void usunOrganizm(Polozenie p){
        //System.out.println(mapa[p.x][p.y].getGatunek());
        //System.out.println(p.x + " " + p.y);
        mapa[p.x][p.y] = null;
        //rysujSwiat();
    }

    void rysujSwiat() {
        for (int y = 0; y < rozmiarY; y++) {
            for (int x = 0; x < rozmiarX; x++) {
                if (mapa[x][y] != null)
                    mapa[x][y].rysuj();
                else System.out.print("*");
            }
            System.out.println();
        }
    }

    void wykonajTure(String key) {
        this.key = key;

        int maxInicjatywa = 7;
        int maxWiek = runda;

        for (int i = maxInicjatywa; i >= 0; i--) {
            for (int j = maxWiek; j >= 0; j--) {
                for (int y = 0; y < rozmiarY; y++) {
                    for (int x = 0; x < rozmiarX; x++) {
                        if (mapa[x][y] != null
                                && mapa[x][y].getWiek() == j
                                && mapa[x][y].getInicjatywa() == i
                                && mapa[x][y].getRuch()) {

                            if(mapa[x][y] instanceof Czlowiek)
                                System.out.println("czlowiek here");
                            mapa[x][y].zmienRuch();
                            mapa[x][y].akcja(new Polozenie(x,y));

                        }
                    }
                }
            }
        }

        for (int y = 0; y < rozmiarY; y++) {
            for (int x = 0; x < rozmiarX; x++) {
                if (mapa[x][y] != null) {
                    mapa[x][y].zmienRuch();
                    mapa[x][y].inkrementujWiek();
                }
            }
        }
        runda++;
    }

    public void zapiszDoPliku()  {
        PrintWriter writer;
        try {
            writer = new PrintWriter("save.txt", "UTF-8");

            writer.println(rozmiarX);
            writer.println(rozmiarY);

            for (int y = 0; y < rozmiarY; y++) {
                for (int x = 0; x < rozmiarX; x++) {
                    if(mapa[x][y] instanceof Czlowiek){
                        Czlowiek czlowiek = (Czlowiek) mapa[x][y];
                        writer.println(mapa[x][y].getGatunek());
                        writer.println(czlowiek.getCzasMocy());
                        writer.println(mapa[x][y].getSila());
                        writer.println(mapa[x][y].getWiek());
                    }
                    else if (mapa[x][y] != null){
                        writer.println(mapa[x][y].getGatunek());
                        writer.println(mapa[x][y].getSila());
                        writer.println(mapa[x][y].getWiek());
                    }
                    else writer.println("n");
                }
                System.out.println();
            }
            writer.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public Swiat(){
        File myObj = new File("save.txt");
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            int sizeX = Integer.parseInt(myReader.nextLine());
            int sizeY = Integer.parseInt(myReader.nextLine());
            setVariables(sizeX, sizeY);

            for (int y = 0; y < rozmiarY; y++) {
                for (int x = 0; x < rozmiarX; x++) {
                    String gatunek = myReader.nextLine();
                    if(gatunek.equals("n"))
                        mapa[x][y] = null;
                    else if(gatunek.equals("czlowiek")){
                        Czlowiek czlowiek = new Czlowiek(this);
                        czlowiek.setCzasMocy(Integer.parseInt(myReader.nextLine()));
                        czlowiek.setSila(Integer.parseInt(myReader.nextLine()));
                        czlowiek.setWiek(Integer.parseInt(myReader.nextLine()));
                        mapa[x][y] = czlowiek;
                    }
                    else{
                        switch (gatunek) {
                            case "antylopa":
                                mapa[x][y] = new Antylopa(this);
                                break;
                            case "Barszcz sosnowskiego":
                                mapa[x][y] = new BarszczSosnowskiego(this);
                                break;
                            case "Guarana":
                                mapa[x][y] = new Guarana(this);
                                break;
                            case "Lis":
                                mapa[x][y] = new Lis(this);
                                break;
                            case "mlecz":
                                mapa[x][y] = new Mlecz(this);
                                break;
                            case "owca":
                                mapa[x][y] = new Owca(this);
                                break;
                            case "trawa":
                                mapa[x][y] = new Trawa(this);
                                break;
                            case "wilcze jagody":
                                mapa[x][y] = new WilczeJagody(this);
                                break;
                            case "wilk":
                                mapa[x][y] = new Wilk(this);
                                break;
                            case "zolw":
                                mapa[x][y] = new Zolw(this);
                                break;
                            default:
                                System.out.println(gatunek);
                                break;
                        }
                        mapa[x][y].setSila(Integer.parseInt(myReader.nextLine()));
                        mapa[x][y].setSila(Integer.parseInt(myReader.nextLine()));
                    }
                }

                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setFrame();
    }
}






