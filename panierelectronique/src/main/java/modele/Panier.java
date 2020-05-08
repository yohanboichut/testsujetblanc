package modele;

import java.util.Collection;
import java.util.Objects;

public class Panier {


    private FabriqueResume fabriqueResume;
    private Collection<Article> panier;

    private int idPanier;
    private String nom;


    public FabriqueResume getFabriqueResume() {
        return fabriqueResume;
    }

    public void setFabriqueResume(FabriqueResume fabriqueResume) {
        this.fabriqueResume = fabriqueResume;
    }

    public Collection<Article> getPanier() {
        return panier;
    }

    public void setPanier(Collection<Article> panier) {
        this.panier = panier;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double coutTotal(){
        double resultat=0;
        for (Article article : this.getPanier()) {
            resultat += article.getPrix();
        }
        return resultat;
    }

    public Resume generationResume() throws InformationManquanteException {
        if (Objects.isNull(this.getNom()) || this.getNom().equals("")){
            throw new InformationManquanteException();
        }
        else
            return this.getFabriqueResume().creer(this.getIdPanier(),this.getNom(),this.coutTotal());
    }


}
