package modele;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestPartieFonctionnel {



    @Test
    public void testFinDePartieJoueur1Win() {
        Bateau bateau = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Partie partie = new Partie(new ArrayList<>(),bateauJ2);
        Assert.assertTrue(partie.partieTerminee());
    }



    @Test
    public void testFinDePartieJoueur2Win() {
        Bateau bateau = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Partie partie = new Partie(bateauJ2,new ArrayList<>());
        Assert.assertTrue(partie.partieTerminee());
    }


    @Test
    public void testFinDePartiePartieNonTerminee() {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);



        Partie partie = new Partie(bateauJ1,bateauJ2);
        Assert.assertFalse(partie.partieTerminee());
    }


    @Test
    public void testTirJoueur1PasTouchePasCoule() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(false);

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ1,bateauJ2);
        Assert.assertFalse(partie.tir(Partie.JOUEUR,2,2));

    }

    @Test
    public void testTirJoueur2PasTouchePasCoule() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(false);

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ2,bateauJ1);
        Assert.assertFalse(partie.tir(Partie.JOUEUR+"2",2,2));
        Assert.assertFalse(partie.partieTerminee());
    }



    @Test
    public void testTirJoueur1ToucheMaisPasCoule() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(true);

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ1,bateauJ2);
        Assert.assertTrue(partie.tir(Partie.JOUEUR,2,2));
        Assert.assertFalse(partie.partieTerminee());
    }


    @Test
    public void testTirJoueur2ToucheMaisPasCoule() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andReturn(true);

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ2,bateauJ1);
        Assert.assertTrue(partie.tir(Partie.JOUEUR+"2",2,2));
        Assert.assertFalse(partie.partieTerminee());
    }

    @Test
    public void testTirJoueur1ToucheEtCoulePartieTerminee() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andThrow(new CouleException());

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ1,bateauJ2);
        Assert.assertTrue(partie.tir(Partie.JOUEUR,2,2));
        Assert.assertTrue(partie.partieTerminee());
    }


    @Test
    public void testTirJoueur2ToucheEtCoulePartieTerminee() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andThrow(new CouleException());

        EasyMock.replay(bateau,bateau1);

        Partie partie = new Partie(bateauJ2,bateauJ1);
        Assert.assertTrue(partie.tir(Partie.JOUEUR+"2",2,2));
        Assert.assertTrue(partie.partieTerminee());
    }


    @Test
    public void testTirJoueur1ToucheEtCoulePartieNonTerminee() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Bateau bateau2 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);
        bateauJ2.add(bateau2);


        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andThrow(new CouleException());
        EasyMock.expect(bateau2.encaisserTir(2,2)).andReturn(false);

        EasyMock.replay(bateau,bateau1,bateau2);

        Partie partie = new Partie(bateauJ1,bateauJ2);
        Assert.assertTrue(partie.tir(Partie.JOUEUR,2,2));
        Assert.assertFalse(partie.partieTerminee());
    }


    @Test
    public void testTirJoueur2ToucheEtCoulePartieNonTerminee() throws CouleException {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);

        Bateau bateau2 = EasyMock.createMock(Bateau.class);

        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);
        bateauJ2.add(bateau2);


        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);

        EasyMock.expect(bateau.encaisserTir(2,2)).andThrow(new CouleException());
        EasyMock.expect(bateau2.encaisserTir(2,2)).andReturn(false);

        EasyMock.replay(bateau,bateau1,bateau2);

        Partie partie = new Partie(bateauJ2,bateauJ1);
        Assert.assertTrue(partie.tir(Partie.JOUEUR+2,2,2));
        Assert.assertFalse(partie.partieTerminee());
    }


    @Test(expected = NullPointerException.class)
    public void testTirJoueurNull() {
        Bateau bateau = EasyMock.createMock(Bateau.class);
        Bateau bateau1 = EasyMock.createMock(Bateau.class);
        Collection<Bateau> bateauJ2 = new ArrayList<>();
        bateauJ2.add(bateau);

        Collection<Bateau> bateauJ1 = new ArrayList<>();
        bateauJ1.add(bateau1);
        Partie partie = new Partie(bateauJ1,bateauJ2);
        partie.tir(null,2,2);
    }




}
