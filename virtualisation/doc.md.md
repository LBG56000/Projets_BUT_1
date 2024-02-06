# Documentation utilisateur 

## Sur cette page nous allons nous interesser à notre chaine de production lors de ce rendu 

### Nous verrons donc

- La partie conceptualisation 
- La partie codage

## La conceptualisation 

Pour mener à terme ce projet nous avons lors des premières semaines conceptualiser les livrables attentus
- Une documentation utilisateur 
- Trois programmes en langage C 
- Une documentation technique des programmes

Pour rendre les différents livrables dans les temps nous avons donc réparti les tâches à faire sous forme de tableau comme celui que vous voyez

|Personne|En charge de|
|-|-|
|Léo|Documenation technique|
|Baptiste|Documenation utilisateur|
|Titouan|Production des programmes en C|
|Enzo|Documenation utilisateur|

Ce tableau nous a permis de savoir vos attendus et de pouvoir respecter les délais.

### Les programmes en langage C

#### Le morpion

Le programme nous reproduit le jeu de morpion sur une grille 3 par 3. Il nous permet de jouer contre l'ordianteur. 
```
int main() {
    t_grille grilleMorpion;
    
    initGrille(grilleMorpion);
    afficher(grilleMorpion);

    while (verifierGagnant(grilleMorpion) == 0) {
        jeuJoueur(grilleMorpion);
        jeuOrdi(grilleMorpion);
        afficher(grilleMorpion);

    }
    switch (verifierGagnant(grilleMorpion)) {
        case 1:
            printf("Le Joueur gagne !\n");
            break;
        case 2:
            printf("L'ordinateur gagne !\n");
            break;
        case 3:
            printf("EGALITE !\n");
            break;
    }
    return 0;
}
```
Programme principal du jeu permettant de jouer avec la mise en relation des différentes procédures et fonctions.

#### L'agenda

Ce programme permet de récréer un agenda avec la possibilité d'ajouter des tâches à faire, affciher les taches ou de supprimer une tache.

```
int main()
{
    t_calendrier calend; /**Le calendrier commun à toutes les procedures et fonctions*/
    int stack = 0;       /**La pile de tâche à faire*/

    /** Affichage du menu et gestion des choix de l'utilisateur*/
    int section = menu();
    while (section != 4)
    {

        switch (section)
        {
        case 1:
            afficherTaches(&calend, &stack);
            break;
        case 2:
            addTache(&calend, &stack);
            break;
        case 3:
            supTache(&calend, &stack);
            break;
        }

        section = menu();
    }
    printf("Bonne journee !");
    return 0;
}
```
Programme principal permettent de faire la mise en relation pour créer un agenda personalisé. 

#### Algorithmes de tri

Ce programme vise à tester différentes façons de trier. 
Nous pouvons ainsi voir différentes façcon de trier une seule et unique chaine. 

```
int main() {
    int section = menu();/**Obtenir le choix de l'utilisateur pour l'algorithme de tri*/ 
    chaine chaineATrie;/**La chaine a trier commune à toute les procedures et fonctions*/
    initChaine(chaineATrie);/**Initialiser un tableau avec des valeurs aléatoires*/
    
    /**Afficher le tableau initial*/ 
    if (section < 4) {
        printf("\nChaine de depart :\n");
        afficherChaine(chaineATrie);
        printf("\nEtapes :\n");

        /**Effectuer l'algorithme de tri sélectionné*/
        switch (section) {
            case 1:
                trieSelection(chaineATrie);
                break;
            case 2:
                trieDichotomie(chaineATrie, 0, N - 1);
                break;
            case 3:
                trieInsertion(chaineATrie);
                break;
        }

        /**Afficher le tableau trié final*/ 
        printf("\nChaine de fin :\n");
        afficherChaine(chaineATrie);
    }
    
    printf("Bonne journee !\n");
    return 0;
}
```
Porgramme principal permettant de trier une chaine. 

### Documentation technique / utilisateur

- la documentation technique vise à détailler comment les programmes fonctionnent et surtout comment ils sont structurés
- la documenation utilisteur vise à expliquer comment faire fonctionner les proagrammes. 

## Le codage

- Utilisation de PHP
- Utilisation de Docker 
- Un programme Bash


Notre organisation:

|Personne|Taches à faire|
|-|-|
|Léo| Bash/aide|
|Baptiste|PHP documentation utilisateur|
|Enzo|Bash|
|Titouan|PHP documentation technique|

### Le PHP 

Le programme PHP sert à récupérer du texte contenu dans les programme en C pour en faire une page HTML avec les différentes commentaires mais aussi les prototypes des fonctions et procédures. 

Le PHP nous permet de créer une page HTML avec les mêmes balises que dans les programmes en C ou encore les balises Mardown. 

### Docker 

L'utilisation de Docker noous permet de prendre des images et de pouvoir récupérer un document PDF à partir d'un fichier HTML. 

Différentes images nous ont servies pour permettre de créer les différents livrables.

### Le programme Bash 

Le programmes vise à mettre en relation les différnts programmes (génération de documentation,docker,...) pour permettre de créer les archives finales contenant les informations nécessaires. 






#### Voila notre chaine de production pour permettre de faire les livrables que vous avez demandés, pour toutes questions nous restons à votre écoute

###### Rédigé par Léo avec la participation de Baptiste, Titouan et Enzo 