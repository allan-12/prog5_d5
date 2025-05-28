# prog5_d5


# Système de Machine à Café

## Présentation

Le système de machine à café est une application Java qui reproduit le comportement d’une machine à café automatique. Il permet de gérer les interactions utilisateur liées au paiement, au choix du café et à la distribution, tout en prenant en charge les erreurs comme un paiement insuffisant, un manque d’ingrédients, une panne électrique ou un réservoir d’eau vide. Conçu avec une architecture modulaire et extensible, il respecte les principes de la programmation orientée objet pour assurer une maintenance aisée et une évolutivité future.

## Architecture du Système

Le système est structuré autour d’une architecture modulaire, favorisant une séparation nette des responsabilités. Il s’appuie sur les principes de conception orientée objet, tels que l’encapsulation, la responsabilité unique et l’injection de dépendances. L’architecture est composée de plusieurs modules, chacun dédié à une fonction spécifique de la machine à café.

### Modules

1. *Module de Gestion des Paiements*

   - *Rôle* : Supervise le traitement des paiements pour les commandes de café.
   - *Fonctionnalités* :
     - Accepte différents modes de paiement (pièces, paiements électroniques, etc.).
     - Vérifie si le montant payé couvre le coût du café sélectionné.
     - Transmet l’état du paiement au module de choix du café.
   - *Gestion des erreurs* :
     - Affiche un message si le paiement est insuffisant.

2. *Module de Choix du Café*

   - *Rôle* : Gère la sélection des cafés et contrôle la disponibilité des ressources nécessaires.
   - *Fonctionnalités* :
     - Propose une liste de cafés disponibles (par exemple : espresso, cappuccino, latte).
     - Vérifie la disponibilité des ingrédients (grains de café, lait, sucre, etc.).
     - Contrôle le niveau d’eau dans le réservoir.
     - S’assure que la machine est alimentée en électricité.
   - *Gestion des erreurs* :
     - Informe l’utilisateur en cas de manque d’ingrédients, d’eau ou de panne électrique.
   - *Dépendances* : Module de Gestion des Stocks, Module de Réservoir d’Eau, Module d’Alimentation Électrique.

3. *Module de Gestion des Stocks*

   - *Rôle* : Surveille et gère les stocks d’ingrédients nécessaires à la préparation des cafés.
   - *Fonctionnalités* :
     - Suit les quantités disponibles d’ingrédients (café, lait, sucre, etc.).
     - Met à jour les stocks après chaque préparation de café.
     - Fournit des informations sur la disponibilité des ingrédients au module de choix.
   - *Gestion des erreurs* :
     - Signale lorsque les stocks d’ingrédients sont épuisés.

4. *Module de Réservoir d’Eau*

   - *Rôle* : Contrôle l’approvisionnement en eau pour la préparation des cafés.
   - *Fonctionnalités* :
     - Surveille le niveau d’eau dans le réservoir.
     - Met à jour le niveau d’eau après chaque utilisation.
     - Indique la disponibilité de l’eau au module de choix.
   - *Gestion des erreurs* :
     - Avertit en cas de niveau d’eau insuffisant.


5. *Module d’Alimentation Électrique*

   - *Rôle* : Vérifie l’état de l’alimentation électrique de la machine.
   - *Fonctionnalités* :
     - Contrôle si la machine est sous tension avant de lancer une opération.
     - Fournit l’état de l’alimentation au module de choix.
   - *Gestion des erreurs* :
     - Indique si la machine est hors service en raison d’une panne électrique.

6. *Module de Préparation du Café*

   - *Rôle* : Gère la préparation et la distribution du café sélectionné.
   - *Fonctionnalités* :
     - Lance l’opération getCoffee pour préparer et distribuer le café.
     - Collabore avec les modules de stocks et de réservoir d’eau pour consommer les ressources nécessaires.
     - Confirme à l’utilisateur que le café a été préparé avec succès.
   - *Gestion des erreurs* :
     - S’appuie sur le module de choix pour vérifier que toutes les conditions préalables (paiement, ingrédients, eau, courant) sont remplies.
   - *Dépendances* : Module de Choix du Café, Module de Gestion des Stocks, Module de Réservoir d’Eau.

7. *Module d’Interface Utilisateur*

   - *Rôle* : Fournit une interface pour les interactions avec l’utilisateur.
   - *Fonctionnalités* :
     - Affiche les options de café, les demandes de paiement et les messages d’erreur.
     - Recueille les choix de l’utilisateur (sélection de café, paiement).
     - Transfère les données aux modules de paiement, de choix et de préparation.
   - *Dépendances* : Module de Gestion des Paiements, Module de Choix du Café, Module de Préparation du Café.

### Flux de Fonctionnement

1. *Lancement* : L’utilisateur interagit avec l’interface pour choisir un café.
2. *Paiement* : Le module de gestion des paiements vérifie si le montant fourni est suffisant.
   - En cas de paiement insuffisant, un message invite l’utilisateur à compléter le paiement.
3. *Choix du Café* : Le module de choix vérifie :
   - La disponibilité des ingrédients via le module de gestion des stocks.
   - Le niveau d’eau via le module de réservoir.
   - L’état de l’alimentation électrique via le module correspondant.
   - Si une vérification échoue, un message d’erreur est affiché à l’utilisateur.
4. *Préparation* : Si toutes les conditions sont réunies, le module de préparation produit le café et met à jour les stocks et le réservoir d’eau.
5. *Confirmation* : L’interface utilisateur informe l’utilisateur du succès de l’opération ou des erreurs rencontrées.

### Considérations de Conception

- *Modularité* : Chaque module est indépendant et communique via des interfaces bien définies, facilitant les mises à jour.
- *Extensibilité* : De nouveaux types de café ou moyens de paiement peuvent être ajoutés sans modifier les modules existants.
- *Gestion des erreurs* : Une gestion complète des erreurs garantit des retours clairs à l’utilisateur.
- *Maintenabilité* : La séparation des responsabilités simplifie le débogage et la maintenance.
- *Évolutivité* : Le système peut intégrer des fonctionnalités supplémentaires, comme des alertes de maintenance ou une surveillance à distance.

### Hypothèses

- Un seul utilisateur interagit avec la machine à la fois.
- L’eau est considérée comme une ressource distincte des autres ingrédients pour une gestion d’erreurs claire.
- La machine nécessite une alimentation électrique pour fonctionner.

### Améliorations Futures

- Ajout de moyens de paiement variés (cartes bancaires, paiements mobiles).
- Mise en place d’un mode maintenance pour recharger les ingrédients et l’eau.
- Intégration de journaux pour suivre les erreurs et l’utilisation.
- Support multilingue pour l’interface utilisateur.

## Mise en Route

Pour développer ce système en Java :
1. Définir des interfaces pour chaque module afin de garantir un couplage faible.
2. Créer des classes concrètes pour chaque module, implémentant les interfaces correspondantes.
3. Utiliser l’injection de dépendances pour connecter les modules.
4. Implémenter une classe contrôleur principale pour orchestrer le flux opérationnel.
5. Tester chaque module indépendamment, puis effectuer des tests d’intégration pour vérifier le fonctionnement global.

Ce README offre une vue d’ensemble de l’architecture du système de machine à café. Les détails d’implémentation dépendront des besoins spécifiques, comme le type d’interface utilisateur (console, graphique) ou l’intégration matérielle.~~


https://github.com/Safidy-Michael/prog-5-d5-Algo

