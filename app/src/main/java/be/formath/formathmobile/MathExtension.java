package be.formath.formathmobile;

import java.util.ArrayList;

/**
 * Created by v-dawagne on 01-09-16.
 */
public class MathExtension {
    public static ArrayList<Integer> getListeDiviseurEntier(int dividende) {
        ArrayList<Integer> diviseurs = new ArrayList<>();
        for(int i = 2; i < dividende; i++) {
            if (dividende % i == 0) {
                diviseurs.add(i);
            }
        }
        return diviseurs;
    }
}
