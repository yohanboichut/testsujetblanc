package modele;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestPanier {



    @Test
    public void testCoutTotalPanierVide(){

        Panier panier = EasyMock.createMockBuilder(Panier.class).addMockedMethod("getPanier").createMock();

        Collection<Article> articles = new ArrayList<>();

        EasyMock.expect(panier.getPanier()).andReturn(articles);

        EasyMock.replay(panier);

        double total = panier.coutTotal();

        Assert.assertTrue(total==0);

    }


    @Test
    public void testCoutTotalPanierNonVide(){

        Panier panier = EasyMock.createMockBuilder(Panier.class).addMockedMethod("getPanier").createMock();

        Collection<Article> articles = new ArrayList<>();

        Article article1 = EasyMock.createMock(Article.class);
        Article article2 = EasyMock.createMock(Article.class);

        articles.add(article1);
        articles.add(article2);

        EasyMock.expect(article1.getPrix()).andReturn(4d);
        EasyMock.expect(article2.getPrix()).andReturn(6d);
        EasyMock.expect(panier.getPanier()).andReturn(articles);

        EasyMock.replay(panier);
        EasyMock.replay(article1,article2);

        double total = panier.coutTotal();

        Assert.assertTrue(total==10);
    }


    @Test(expected = NullPointerException.class)
    public void testCoutToltalNullPointer(){
        Panier panier = EasyMock.createMockBuilder(Panier.class).addMockedMethod("getPanier").createMock();

        EasyMock.expect(panier.getPanier()).andReturn(null);

        EasyMock.replay(panier);

        double total = panier.coutTotal();


    }


    @Test(expected = InformationManquanteException.class)
    public void testGenerationResumeInformationManquante() throws InformationManquanteException {
        Panier panier = EasyMock.createMockBuilder(Panier.class).addMockedMethod("getNom").createMock();
        EasyMock.expect(panier.getNom()).andReturn(null);
        EasyMock.replay(panier);
        panier.generationResume();
    }

    @Test
    public void testGenerationResumeOK() throws InformationManquanteException {
        Panier panier = EasyMock.createMockBuilder(Panier.class).addMockedMethod("getNom").addMockedMethod("getFabriqueResume")
                .addMockedMethod("getIdPanier").addMockedMethod("coutTotal").createMock();

        FabriqueResume fabriqueResume = EasyMock.createMock(FabriqueResume.class);
        Resume resume = EasyMock.createMock(Resume.class);
        EasyMock.expect(fabriqueResume.creer(12,"monPanier",150)).andReturn(resume);
        EasyMock.expect(panier.getNom()).andReturn("monPanier").times(3);
        EasyMock.expect(panier.getIdPanier()).andReturn(12);
        EasyMock.expect(panier.coutTotal()).andReturn(150d);
        EasyMock.expect(panier.getFabriqueResume()).andReturn(fabriqueResume);

        EasyMock.replay(panier,fabriqueResume,resume);

        Resume resultat = panier.generationResume();
        EasyMock.verify(panier,fabriqueResume,resume);
        Assert.assertTrue(resultat == resume);
    }




}
