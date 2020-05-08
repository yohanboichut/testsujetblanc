package modele;


import java.util.ArrayList;
import java.util.Collection;

public class Partie {

    public static final String JOUEUR = "joueur1";
    private Collection<Bateau> bateauxJoueur1;
    private Collection<Bateau> bateauxJoueur2;


    public Partie(Collection<Bateau> bateauxJoueur1, Collection<Bateau> bateauxJoueur2) {
        this.bateauxJoueur1 = new ArrayList<>(bateauxJoueur1);
        this.bateauxJoueur2 = new ArrayList<>(bateauxJoueur2);
    }

    public boolean partieTerminee() {
        return  (this.bateauxJoueur1.isEmpty() || this.bateauxJoueur2.isEmpty());
    }

    public boolean tir(String joueur, int x, int y) {

        Collection<Bateau> bateauxVises = joueur.equals(JOUEUR)?this.bateauxJoueur2:this.bateauxJoueur1;
        Collection<Bateau> bateauxASupprimer = new ArrayList<Bateau>();

        for (Bateau b : bateauxVises) {

            try {
                if(b.encaisserTir(x,y)) {
                    return true;
                }
            } catch (CouleException e) {
                bateauxASupprimer.add(b);
            }
        }

        if (bateauxASupprimer.isEmpty()) {
            return false;
        }
        else {
            bateauxVises.removeAll(bateauxASupprimer);
            return true;
        }


    }

}
