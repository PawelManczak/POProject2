package com.company;

import com.company.Organizmy.Rosliny.*;
import com.company.Organizmy.Zwierzeta.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Lista extends JFrame {
    private JList<String> jList;
    private JFrame frame;
    Lista(Swiat swiat, Polozenie polozenie){
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("wilk");
        model.addElement("owca");
        model.addElement("lis");
        model.addElement("zolw");
        model.addElement("antylopa");

        
        model.addElement("trawa");
        model.addElement("Guarana");
        model.addElement("mlecz");
        model.addElement("wilcze jagody");
        model.addElement("barszcz sosnowskiego");

        jList = new JList<>(model);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                switch(jList.getSelectedValue()){
                    case "wilk":
                        swiat.dodajOrganizm(polozenie, new Wilk(swiat));
                        break;
                    case "owca":
                        swiat.dodajOrganizm(polozenie, new Owca(swiat));
                        break;
                    case "lis":
                        swiat.dodajOrganizm(polozenie, new Lis(swiat));
                        break;
                    case "zolw":
                        swiat.dodajOrganizm(polozenie, new Zolw(swiat));
                        break;
                    case "antylopa":
                        swiat.dodajOrganizm(polozenie, new Antylopa(swiat));
                        break;
                    case "trawa":
                        swiat.dodajOrganizm(polozenie, new Trawa(swiat));
                        break;
                    case "Guarana":
                        swiat.dodajOrganizm(polozenie, new Guarana(swiat));
                        break;
                    case "mlecz":
                        swiat.dodajOrganizm(polozenie, new Mlecz(swiat));
                        break;
                    case "wilcze jagody":
                        swiat.dodajOrganizm(polozenie, new WilczeJagody(swiat));
                        break;
                    case "barszcz sosnowskiego":
                        swiat.dodajOrganizm(polozenie, new BarszczSosnowskiego(swiat));
                        break;

                }

                swiat.frame.repaint();
                SwingUtilities.getWindowAncestor(jList).setVisible(false);
            }

        });
        add(jList);
        setTitle("Wybierz organizm, ktory chcesz dodac");
        jList.setSize(200,400);
        setSize(200,400);
        setVisible(true);

    }
}