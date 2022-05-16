package com.company;

import com.company.Organizmy.Rosliny.BarszczSosnowskiego;
import com.company.Organizmy.Rosliny.Guarana;
import com.company.Organizmy.Rosliny.Mlecz;
import com.company.Organizmy.Rosliny.Trawa;
import com.company.Organizmy.Zwierzeta.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command;
        //String command = scan.nextLine();
        Swiat swiat;

        //command = scan.nextLine();
        //if(command.equals("1")) {

            swiat = new Swiat(15, 5);

            Wilk wilk = new Wilk(swiat);
            Wilk wilk2 = new Wilk(swiat);
            Owca owca = new Owca(swiat);
            Lis lis = new Lis(swiat);
            Czlowiek czlowiek = new Czlowiek(swiat);
            Zolw zolw = new Zolw(swiat);
            Trawa trawa = new Trawa(swiat);
            Mlecz mlecz = new Mlecz(swiat);
            Guarana guarana = new Guarana(swiat);
            BarszczSosnowskiego barszczSosnowskiego = new BarszczSosnowskiego(swiat);


            swiat.dodajOrganizm(new Polozenie(1, 2), wilk);
            swiat.dodajOrganizm(new Polozenie(2, 2), wilk2);
            swiat.dodajOrganizm(new Polozenie(2, 3), owca);
            swiat.dodajOrganizm(new Polozenie(4, 4), lis);
            swiat.dodajOrganizm(new Polozenie(4, 2), czlowiek);
            swiat.dodajOrganizm(new Polozenie(0, 0), zolw);
            swiat.dodajOrganizm(new Polozenie(4, 3), trawa);
            swiat.dodajOrganizm(new Polozenie(6, 2), mlecz);
            swiat.dodajOrganizm(new Polozenie(7, 4), guarana);
            swiat.dodajOrganizm(new Polozenie(10, 4), barszczSosnowskiego);
        /*}
        else {
         swiat = new Swiat();
        }*/


        while(true){
            command = scan.nextLine();
            swiat.rysujSwiat();
            swiat.wykonajTure(null);
        }

    }
}
