package modele;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestPartieStructurel {


    @Test
    public void testFinDePartie() {

        Partie partie = new Partie(new ArrayList<Bateau>(),new ArrayList<Bateau>());

        boolean resultat = partie.partieTerminee();
        Assert.assertTrue(resultat);
    }


    @Test
    public void testTirerJ1() {
        Partie partie = new Partie(new ArrayList<>(),new ArrayList<>());

        boolean resultat = partie.tir(Partie.JOUEUR,2,2);
        Assert.assertFalse(resultat);
    }

    @Test
    public void testTirerJ2() {
        Partie partie = new Partie(new ArrayList<>(),new ArrayList<>());

        boolean resultat = partie.tir(Partie.JOUEUR+"2",2,2);
        Assert.assertFalse(resultat);
    }

    @Test
    public void testTirerJoueur1ToucheMaisPasCoule() throws CouleException {

        Collection<Bateau> joueur2= new ArrayList<>();
        Bateau bateau = EasyMock.createMock(Bateau.class);
        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(true);
        joueur2.add(bateau);

        EasyMock.replay(bateau);

        Partie partie = new Partie(new ArrayList<>(),joueur2);
        boolean resultat = partie.tir(Partie.JOUEUR,2,2);
        Assert.assertTrue(resultat);
        Assert.assertTrue(joueur2.size()==1);

    }


    @Test
    public void testTirerJoueur1ToucheEtCoule() throws CouleException {

        Collection<Bateau> joueur2= new ArrayList<>();
        Bateau bateau = EasyMock.createMock(Bateau.class);
        EasyMock.expect(bateau.encaisserTir(2,2)).andThrow(new CouleException());
        joueur2.add(bateau);

        EasyMock.replay(bateau);

        Partie partie = new Partie(new ArrayList<>(),joueur2);
        boolean resultat = partie.tir(Partie.JOUEUR,2,2);
        Assert.assertTrue(resultat);
        Assert.assertTrue(joueur2.size()==0);

    }

    @Test
    public void testTirerJoueur1NeTouchePas() throws CouleException {

        Collection<Bateau> joueur2= new ArrayList<>();
        Bateau bateau = EasyMock.createMock(Bateau.class);
        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(false);
        joueur2.add(bateau);

        EasyMock.replay(bateau);

        Partie partie = new Partie(new ArrayList<>(),joueur2);
        boolean resultat = partie.tir(Partie.JOUEUR,2,2);
        Assert.assertFalse(resultat);
        Assert.assertTrue(joueur2.size()==1);

    }







}
