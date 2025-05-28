package org.example;

import org.example.modele.InterfaceUtilisateur;
import org.example.modele.MachineACafe;

public class Main {
    public static void main(String[] args) {
        MachineACafe machine = new MachineACafe();
        InterfaceUtilisateur ui = new InterfaceUtilisateur(machine);
        ui.demarrer();
    }
}