package org.example.modele;

import java.util.HashMap;
import java.util.Map;

public class MachineACafe {
    private static final int EAU_INITIALE = 1000;
    private static final int CAPSULES_INITIALES = 10;
    private static final double PROBABILITE_ECHEC_CONNEXION = 0.1;

    private static final String ERR_FONDS_INSUFFISANTS = "Erreur : Fonds insuffisants.";
    private static final String ERR_CONNEXION = "Erreur : Problème de connexion au paiement.";
    private static final String ERR_BOISSON_INDISPONIBLE = "Erreur : Boisson non disponible.";
    private static final String ERR_EAU_VIDE = "Erreur : Réservoir d'eau vide.";
    private static final String ERR_PAS_DE_CAPSULE = "Erreur : Aucune capsule de café détectée.";
    private static final String ERR_PANNE = "Erreur : Panne de courant ou erreur interne.";
    private static final String ERR_PAS_DE_GOBELET = "Erreur : Aucun gobelet détecté.";

    private double argentCollecte = 0.0;
    private int eau = EAU_INITIALE;
    private int capsulesCafe = CAPSULES_INITIALES;
    private boolean gobeletPresent = false;
    private boolean alimentationActive = true;

    private final Map<String, Boisson> catalogue;

    public MachineACafe() {
        catalogue = new HashMap<>();
        catalogue.put("espresso", new Boisson("Espresso", 1.5, 50, 1));
        catalogue.put("latte", new Boisson("Latte", 2.5, 100, 1));
    }

    public void effectuerPaiement(double montant, String methode) throws MachineException {
        if (montant <= 0) {
            throw new MachineException(ERR_FONDS_INSUFFISANTS);
        }
        if (Math.random() < PROBABILITE_ECHEC_CONNEXION) {
            throw new MachineException(ERR_CONNEXION);
        }
        argentCollecte += montant;
    }

    public Boisson selectionnerBoisson(String choix) throws MachineException {
        if (!alimentationActive) {
            throw new MachineException(ERR_PANNE);
        }
        Boisson boisson = catalogue.get(choix.toLowerCase());
        if (boisson == null) {
            throw new MachineException(ERR_BOISSON_INDISPONIBLE);
        }
        if (eau < boisson.getEauNecessaire()) {
            throw new MachineException(ERR_EAU_VIDE);
        }
        if (capsulesCafe < boisson.getCapsulesNecessaires()) {
            throw new MachineException(ERR_PAS_DE_CAPSULE);
        }
        if (argentCollecte < boisson.getPrix()) {
            throw new MachineException(ERR_FONDS_INSUFFISANTS + " pour " + boisson.getNom() + ".");
        }
        return boisson;
    }

    public void distribuerBoisson(Boisson boisson) throws MachineException {
        if (!gobeletPresent) {
            throw new MachineException(ERR_PAS_DE_GOBELET);
        }
        eau -= boisson.getEauNecessaire();
        capsulesCafe -= boisson.getCapsulesNecessaires();
        argentCollecte -= boisson.getPrix();
    }


    public void placerGobelet() {
        gobeletPresent = true;
    }

    public String getRessources() {
        return String.format("État des ressources :\n- Eau : %d ml\n- Capsules de café : %d\n- Argent collecté : %.2f€",
                eau, capsulesCafe, argentCollecte);
    }

    public boolean isActive() {
        return alimentationActive;
    }
}
