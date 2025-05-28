package org.example.modele;

public class Boisson {
    private final String nom;
    private final double prix;
    private final int eauNecessaire;
    private final int capsulesNecessaires;

    public Boisson(String nom, double prix, int eauNecessaire, int capsulesNecessaires) {
        this.nom = nom;
        this.prix = prix;
        this.eauNecessaire = eauNecessaire;
        this.capsulesNecessaires = capsulesNecessaires;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getEauNecessaire() {
        return eauNecessaire;
    }

    public int getCapsulesNecessaires() {
        return capsulesNecessaires;
    }
}
