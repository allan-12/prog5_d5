package org.example.modele;

import java.util.Scanner;

public class InterfaceUtilisateur {
    private final MachineACafe machine;
    private final Scanner scanner;

    public InterfaceUtilisateur(MachineACafe machine) {
        this.machine = machine;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        while (machine.isActive()) {
            afficherMenu();
            int choix = lireEntier("Choisissez une option : ");
            if (choix == 5) {
                System.out.println("Arrêt de la machine.");
                break;
            }
            executerChoix(choix);
        }
        scanner.close();
    }

    private void afficherMenu() {
        System.out.println("\n=== Machine à Café ===");
        System.out.println("1. Effectuer un paiement");
        System.out.println("2. Sélectionner une boisson");
        System.out.println("3. Placer un gobelet");
        System.out.println("4. Afficher les ressources");
        System.out.println("5. Quitter");
    }

    private int lireEntier(String message) {
        System.out.print(message);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private String lireChaine(String message) {
        System.out.print(message);
        scanner.nextLine();
        return scanner.nextLine();
    }

    private void executerChoix(int choix) {
        try {
            switch (choix) {
                case 1:
                    double montant = lireEntier("Entrez le montant (€) : ");
                    String methode = lireChaine("Méthode de paiement (carte/mobile) : ");
                    machine.effectuerPaiement(montant, methode);
                    System.out.println("Paiement de " + montant + "€ accepté.");
                    break;
                case 2:
                    String boisson = lireChaine("Choisissez une boisson (espresso/latte) : ");
                    Boisson selectedBoisson = machine.selectionnerBoisson(boisson);
                    machine.distribuerBoisson(selectedBoisson);
                    System.out.println("Distribution de " + selectedBoisson.getNom() + " en cours...");
                    System.out.println(selectedBoisson.getNom() + " servi ! Profitez de votre boisson.");
                    break;
                case 3:
                    machine.placerGobelet();
                    System.out.println("Gobelet détecté.");
                    break;
                case 4:
                    System.out.println(machine.getRessources());
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } catch (MachineException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        MachineACafe machine = new MachineACafe();
        InterfaceUtilisateur ui = new InterfaceUtilisateur(machine);
        ui.demarrer();
    }
}
