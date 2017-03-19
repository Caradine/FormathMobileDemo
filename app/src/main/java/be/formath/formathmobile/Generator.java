package be.formath.formathmobile;

import be.formath.formathmobile.Model.*;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by v-dawagne on 01-09-16.
 */
public class Generator {
    public Operation generateOperation(String code) {
        Operation oper = new Operation();
        oper.setCode(code);
        Random rand = new Random();
        int nb_fact;
        int fact;
        float ffact;
        int fact_deux;
        float ffact_deux;
        int fact_trois;
        int dizaine;
        int dizaine_deux;
        int dizaine_trois;
        int unite;
        int unite_deux;
        int unite_trois;
        int cpt;
        int somme;
        int decime;
        int centime;
        int decime_deux;
        int centime_deux;
        int dividende_int;
        int diviseur_int;
        int quotient_int;
        boolean place;
        String label;

        switch (code) {
            case "A01":
                /*
                    Nombre de 1 chiffre + nombre de 1 chiffre.
                    Somme < 10
                */
                fact = rand.nextInt(10);
                fact_deux = rand.nextInt(10 - fact);
                oper.setRawResponse((float) (fact + fact_deux));
                oper.setLabel(fact + " + " + fact_deux + " =");
                break;
            case "A02":
                /*
                    Nombre de 2 chiffres + nombre de 1 chiffre.
                    Pas de report.
                    Somme < 20
                */
                fact = 10 + rand.nextInt(10);
                fact_deux = rand.nextInt(20 - fact);
                oper.setRawResponse((float) (fact + fact_deux));
                oper.setLabel(fact + " + " + fact_deux + " =");
                break;
            case "A03":
                /*
                    De 2 à 6 nombres à 1 chiffre.
                    Somme < 20
                */
                somme = 0;
                label = "";
                fact = rand.nextInt(10);
                somme += fact;
                label += fact;
                cpt = 1;
                while (cpt <= 6) {
                    fact = rand.nextInt(10);
                    if (somme + fact < 20) {
                        somme += fact;
                        label += " + " + fact;
                    }
                    cpt++;
                }
                oper.setRawResponse((float) somme);
                oper.setLabel(label + " =");
                break;
            case "A04":
                /*
                    Complémentaire à 10.
                */
                place = rand.nextBoolean();
                fact = rand.nextInt(10);
                oper.setRawResponse((float) (10 - fact));
                if (place) {
                    oper.setLabel("10 = ... + " + fact);
                } else {
                    oper.setLabel("10 = " + fact + " + ...");
                }
                break;
            case "A05":
                /*
                    Nombre de 2 chiffres + nombre de 1 chiffre.
                    Pas de report.
                    Somme < 100
                */
                dizaine = 1 + rand.nextInt(9);
                unite = rand.nextInt(10);
                unite_deux = rand.nextInt(10 - unite);
                oper.setRawResponse((float) (dizaine * 10) + unite + unite_deux);
                oper.setLabel(((dizaine * 10) + unite) + " + " + unite_deux + " =");
                break;
            case "A06":
                /*
                    Nombre de 2 chiffres + nombre de 2 chiffres.
                    Pas de report.
                    Somme < 100
                */
                dizaine = 1 + rand.nextInt(9);
                dizaine_deux = 1 + rand.nextInt(10 - dizaine);
                unite = rand.nextInt(10);
                unite_deux = rand.nextInt(10 - unite);
                oper.setRawResponse((float) (dizaine * 10) + unite + (dizaine_deux * 10) + unite_deux);
                oper.setLabel(((dizaine * 10) + unite) + " + " + ((dizaine_deux * 10) + unite_deux) + " =");
                break;
            case "A07":
                /*
                    De 3 à 7 Nombres à 1 chiffre.
                    Somme < 50
                */
                nb_fact = 2 + rand.nextInt(4);
                fact = rand.nextInt(10);
                somme = fact;
                label = fact + "";
                while (nb_fact > 0) {
                    fact = rand.nextInt(10);
                    if (somme + fact < 50) {
                        somme += fact;
                        label += " + " + fact;
                    }
                    nb_fact--;
                }
                oper.setLabel(label + " =");
                oper.setRawResponse((float) somme);
                break;
            case "A08":
                /*
                    Somme de 2 facteurs
                    Un facteur > 10 (ab)
                    Un autre facteur à un chiffre (c)
                    c > b et passage à la dizaine supérieure
                */
                dizaine = rand.nextInt(10);
                unite = rand.nextInt(10);
                unite_deux = unite + rand.nextInt(10 - unite);
                oper.setLabel(((dizaine * 10) + unite) + " + " + unite_deux + " =");
                oper.setRawResponse((float) (dizaine * 10) + unite + unite_deux);
                break;
            case "A09":
                /*
                    Nombre de 2 chiffres + nombre de 2 chiffres.
                    Report.
                    Somme < 100
                */
                dizaine = rand.nextInt(8);
                unite = 2 + rand.nextInt(8);
                dizaine_deux = 1 + rand.nextInt(8 - dizaine);
                unite_deux = (11 - unite) + rand.nextInt(10);
                oper.setLabel(((dizaine * 10) + unite) + " + " + ((dizaine_deux * 10) + unite_deux) + " =");
                oper.setRawResponse((float) (dizaine * 10) + unite + (dizaine_deux * 10) + unite_deux);
                break;
            case "A10":
                /*
                    Complémentaire à 100.
                */
                fact = rand.nextInt(100);
                oper.setLabel("100 = " + fact + " + ...");
                oper.setRawResponse((float) 100 - fact);
                break;
            case "A11":
                /*
                    Facteur 1 de 1 a 99 * 100
                    Facteur 2 de 1 a 99 * 10
                */
                fact = rand.nextInt(100) * 100;
                if (rand.nextBoolean())
                    fact_deux = rand.nextInt(10) * 10;
                else
                    fact_deux = rand.nextInt(10) * 100;
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
                break;
            case "A12":
                /*
                    Nombres de 2 chiffres.
                    Report.
                    Somme > 100
                */
                //TODO: Vérifier si uniquement le report des unités doit faire passer au dela de 100.
                dizaine = 1 + rand.nextInt(9);
                unite = 1 + rand.nextInt(9);
                dizaine_deux = (10 - dizaine) + rand.nextInt(dizaine);
                unite_deux = (10 - unite) + rand.nextInt(unite);
                oper.setLabel(((10 * dizaine) + unite) + " + " + ((10 * dizaine_deux) + unite_deux) + " =");
                oper.setRawResponse((float) (10 * dizaine) + unite + (10 * dizaine_deux) + unite_deux);
                break;
            case "A13":
                /*
                    Nombre entier + nombre à virgule (0,y)
                */
                fact = rand.nextInt(100);
                decime = 1 + rand.nextInt(9);
                oper.setLabel(fact + " + " + (((float) decime) / 10) + " =");
                oper.setRawResponse(fact + (((float) decime) / 10));
                break;
            case "A14":
                /*
                    Nombre entier + nombre à virgule (x,y)
                */
                fact = rand.nextInt(100);
                unite = rand.nextInt(10);
                decime = rand.nextInt(10);
                oper.setLabel(fact + " + " + (unite + ((float) decime) / 10) + " =");
                oper.setRawResponse(fact + unite + (((float) decime) / 10));
                break;
            case "A15":
                /*
                    Somme de 3 facteurs a 2 chiffres
                    Facteur 1 (ab) : 0 < a,b < 9
                    Facteur 2 (cd) : 0 < c,d < 10
                    Facteur 3 (ef) : a + e = 9
                                     b + f = 10
                */
                dizaine = 1 + rand.nextInt(8);
                unite = 1 + rand.nextInt(8);
                fact = (dizaine * 10) + unite;
                dizaine_deux = 1 + rand.nextInt(9);
                unite_deux = 1 + rand.nextInt(9);
                fact_deux = (dizaine_deux * 10) + unite_deux;
                dizaine_trois = 9 - dizaine;
                unite_trois = 10 - unite;
                fact_trois = (dizaine_trois * 10) + unite_trois;
                oper.setLabel(fact + " + " + fact_deux + " + " + fact_trois + " =");
                oper.setRawResponse((float) fact + fact_deux + fact_trois);
                break;
            case "A16":
                /*
                    Nombres ronds sans passage à la centaine.
                */
                dizaine = rand.nextInt(9);
                unite = rand.nextInt(9);
                fact = (100 * dizaine) + (10 * unite);
                unite_deux = rand.nextInt(10 - unite);
                fact_deux = (10 * unite_deux);
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
                break;
            case "A17":
                /*
                    Nombres rond avec passage à la centaine
                */
                dizaine = rand.nextInt(9);
                unite = rand.nextInt(9);
                fact = (100 * dizaine) + (10 * unite);
                if (unite == 0) {
                    unite_deux = 10;
                } else {
                    unite_deux = (10 - unite) + rand.nextInt(10 - (10 - unite));
                }
                fact_deux = (10 * unite_deux);
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
                break;
            case "A18":
                /*
                    Facteur 1 : Nombre à 3 chiffres.
                    Facteur 2 : Nombre à 2 chiffres terminer par 0.
                */
                fact = rand.nextInt(1000);
                fact_deux = rand.nextInt(10) * 10;
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
                break;
            case "A20":
                /*
                    Facteur 1 : Nombre à 3 chiffres < 900.
                    Facteur 2 : Nombre à 2 chiffres.
                */
                fact = rand.nextInt(900);
                fact_deux = rand.nextInt(100);
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
                break;
            case "A21":
                /*
                    Facteur 1 : Centaine < 7
                    Facteur 2 : Centaine < 10 - centaine 1
                */
                fact = rand.nextInt(700);
                fact_deux = rand.nextInt((10 - (fact % 100)) * 100);
                oper.setLabel(fact + " + " + fact_deux + " =");
                oper.setRawResponse((float) fact + fact_deux);
            case "A22":
                /*
                    2 facteurs de 2 chiffre séparé par une virgule
                    Somme des unités < 10 et somme des décime < 10
                */
                unite = rand.nextInt(10);
                unite_deux = 1 + rand.nextInt(9 - unite);
                decime = rand.nextInt(10);
                decime_deux = 1 + rand.nextInt(9 - decime);
                oper.setLabel(unite + "," + decime + " + " + unite_deux + "," + decime_deux + " =");
                oper.setRawResponse((float) unite + unite_deux + (decime + decime_deux) / 10);
                break;
            case "A23":
                /*

                */
                //TODO: Précisions demandée
            case "A24":
                /*
                    Somme de deux facteurs a 2 chiffres séparer par une vrigule
                    Somme des facteurs > 10
                */
                unite = rand.nextInt(10);
                unite_deux = 10 - unite + rand.nextInt(unite);
                decime = rand.nextInt(10);
                decime_deux = 10 - decime + rand.nextInt(decime);
                oper.setLabel(unite + "," + decime + " + " + unite_deux + "," + decime_deux + " =");
                oper.setRawResponse((float) unite + unite_deux + (decime + decime_deux) / 10);
                break;
            case "A25":
                /*
                    Complémentaire a 10 avec un chiffre décimal
                */
                unite = rand.nextInt(10);
                decime = 1 + rand.nextInt(9);
                ffact = (float) unite + (decime / 10);
                oper.setLabel("10 = " + ffact + " + ...");
                oper.setRawResponse(10 - ffact);
                break;
            case "A26":
                /*
                    Facteur 1 : Deux chiffres séparer par une virgule
                    Facteur 2 : Deux chiffre a près la virgule > 0
                */
                unite = rand.nextInt(10);
                decime = 1 + rand.nextInt(9);
                ffact = unite + (decime / 10);
                centime = 1 + rand.nextInt(99);
                oper.setLabel(ffact + " + 0," + centime);
                oper.setRawResponse(ffact + (decime / 100));
                break;
            case "A27":
                /*

                */
                //TODO: Précisions demandées
                break;
            case "A28":
                //TODO: Précisions demandées
                break;
            case "A29":
                /*
                    Somme de 2 facteurs à 3 chiffres.
                    virgule après premier chiffre.
                    Derniers chiffres > 0
                */
                //TODO: Vérifier si unités toujour = 0
                centime = 1 + rand.nextInt(9);
                decime = rand.nextInt(10);
                centime_deux = 1 + rand.nextInt(9);
                decime_deux = rand.nextInt(10);
                oper.setLabel("0," + ((10 * decime) + centime) + " + 0," + ((10 * decime_deux) + centime_deux) + " =");
                oper.setRawResponse(((float) decime / 10) + ((float) centime / 100) + ((float) decime_deux / 10) + ((float) centime_deux / 100));
                break;
            case "A30":
                /*

                */
                //TODO: Précisions demandées
                break;
            case "A31":
                //TODO: Précisions demandées
                break;

            case "D01":
                /*
                    Quotient de 2 nombres.
                    Dividende < 20
                    Diviseur à 1 chiffre
                    Quotient < 10
                */
                //TODO: Vérifier si on exclus bien 1 comme diviseur.
                //FIXME: A debug
                int[] dividendes = {18, 16, 15, 14, 12, 10, 9, 8, 6, 4, 2};
                int choix = rand.nextInt(dividendes.length);
                dividende_int = dividendes[choix];
                ArrayList<Integer> diviseurs = MathExtension.getListeDiviseurEntier(dividende_int);
                if (!diviseurs.isEmpty()) {
                    choix = rand.nextInt(diviseurs.size() - 1);
                } else {
                    choix = 0;
                }
                diviseur_int = diviseurs.get(choix);
                oper.setLabel(dividende_int + " / " + diviseur_int + " =");
                oper.setRawResponse((float) dividende_int / diviseur_int);
                break;
            case "D02":
                /*
                    Dividende < 100
                    Diviseur < 10
                    Quotient < 10
                */
                quotient_int = 1 + rand.nextInt(9);
                diviseur_int = 1 + rand.nextInt(9);
                dividende_int = quotient_int * diviseur_int;
                oper.setRawResponse((float) quotient_int);
                oper.setLabel(dividende_int + " / " + diviseur_int + " =");
                break;
            case "D03":
                /*
                    Dividende: 3 chiffres, centaine > 0, unite = 0
                    Diviseur = 10
                    Quotient entier
                */
                fact = (10 + rand.nextInt(90)) * 10;
                oper.setLabel(fact + " / 10 =");
                oper.setRawResponse((float) fact / 10);
                break;
            case "D04":
                /*
                    Diviseur 1 chiffre > 0
                    Dividende multiple de diviseur * 10 ou 100
                */
                diviseur_int = 1 + rand.nextInt(9);
                dividende_int = rand.nextInt(10) * diviseur_int * 10;
                if (rand.nextBoolean())
                    dividende_int *= 10;
                oper.setLabel(dividende_int + " / " + diviseur_int + " =");
                oper.setRawResponse((float) dividende_int / diviseur_int);
                break;
            case "D05":
                /*
                    Dividende de 2 chiffres (= 0, 2, 4, 6, 8)
                    Diviseur = 2

                */
                unite = 2 * rand.nextInt(5);
                dizaine = 2 * rand.nextInt(5);
                oper.setRawResponse((float) ((dizaine * 10) + unite) / 2);
                oper.setLabel(((dizaine * 10) + unite) + " / 2 =");
                break;
            case "D06":
                /*

                */
                break;
            case "D08":
                /*
                    Dividende à 3 chiffres avec
                    Diviseur = 10
                */
                if (rand.nextInt(10) % 2 == 0) {
                    dizaine = 10 + rand.nextInt(90);
                    decime = 1 + rand.nextInt(9);
                    oper.setLabel(dizaine + "," + decime + " / 10 =");
                    oper.setRawResponse((float) (dizaine + ((float) decime / 10)) / 10);
                } else {
                    dizaine = (1 + rand.nextInt(9)) * 10;
                    unite = 1 + rand.nextInt(9);
                    oper.setLabel((dizaine + unite) + " / 10 =");
                    oper.setRawResponse((float) (dizaine + unite) / 10);
                }
                break;

            case "M01":
                /*
                    Multiplication d'un nombre à 1 chiffre par 2.
                */
                fact = rand.nextInt(10);
                oper.setLabel(fact + " x 2 =");
                oper.setRawResponse((float) fact * 2);
                break;
            case "M02":
                /*
                    Produit de 2 nombres entier inférieurs à 10.
                    Produit < 20
                */
                //FIXME: Terminer ici
                fact = rand.nextInt(10);
                //TODO: Finir ici
                break;
            case "M03":
                /*
                    Produit de 2 nombres entier < 10.
                    Produit < 100
                */
                fact = rand.nextInt(10);
                fact_deux = rand.nextInt(10);
                oper.setLabel(fact + " x " + fact_deux + " =");
                oper.setRawResponse((float) fact * fact_deux);
                break;
            case "M29":
                /*
                    Produit de 2 nombre réel dont un = 500.
                    Second facteur : -ab-  dont a = 1, 2, 3, 4 ou 6
                                           et b = 0, 2, 4, 6, 8
                */
                //TODO: Vérifier présence de la virgule
                break;

            case "S01":
                fact = rand.nextInt(21);
                fact_deux = rand.nextInt(10);
                oper.setRawResponse((float) (fact - fact_deux));
                oper.setLabel(fact + " - " + fact_deux + " =");
                break;
            //case "S02" :
            //  fact = rand.nextInt(20);

        }
        return oper;
    }

    private String catLevelOperationCodeTab[][][] =
            {
                    { // Category 1
                            {"A01","A02","A03","A04"}, // Level 1
                            {"M01","S01","S02"}, // Level 2
                            {"A05","A06","A07","M02"}, // Level 3
                            {"A08","A09","A10","A16","S03"}, // Level 4
                            {"D01","M03","S04","S11"}, // Level 5
                            {"A11","A12","D02","S09","S12"} // Level 6
                    }
            };

    public ArrayList<Operation> generateOperationList(int category, int level) {
        ArrayList<Operation> listOper = new ArrayList<>();
        String listCode[] = catLevelOperationCodeTab[category-1][level-1];
        int nbCode = listCode.length;
        ArrayList<Integer> choosenIndexlist = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int index = rand.nextInt(nbCode);
            while(true) {
                if (contains(choosenIndexlist, index)) {
                    if(choosenIndexlist.size() == nbCode) {
                        choosenIndexlist = new ArrayList<>();
                        choosenIndexlist.add(new Integer(index));
                        listOper.add(generateOperation(listCode[index]));
                        break;
                    }
                    else {
                        index++;
                        if (index == nbCode) {
                            index = 0;
                        }
                    }
                }
                else {
                    choosenIndexlist.add(new Integer(index));
                    listOper.add(generateOperation(listCode[index]));
                    break;
                }
            }
        }
        return listOper;
    }

    private boolean contains(ArrayList<Integer> tab, int elem) {
        for(Integer i : tab) {
            int val = i.intValue();
            if (i == elem) {
                return true;
            }
        }
        return false;
    }
}
