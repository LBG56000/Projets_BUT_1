
/**
 * Programme permettant de jouer au morpion tout seul contre l'ordinateur
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

#define VIDE '.'/**Une case vide*/
#define ORDI 'X'/**Pion Ordinateur*/
#define JOUEUR 'O'/**Pion Joueur */
#define TAILLE 3/**Taille de la grille*/

typedef char t_grille[TAILLE][TAILLE];/**Nouveau type t_grille*/

void initGrille(t_grille grille);
void afficher(t_grille grille);
void saisirNombre(int *nombre);
void jeuJoueur(t_grille grille);
void jeuOrdi(t_grille grille);
int genererNombre(int min, int max);
int verifierGagnant(t_grille grille);
bool estComplete(t_grille grille);
/**
 * @brief progragmme principal permmettant de jouer au morpion
 *
 * @return 0 si le prgramme s'est déroulé sans accros 
 */
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
/**
 * @brief Procedure permettant d'initialiser la grille avec '.'
 * 
 * @param grille la grille de jeu à initialiser 
 */
void initGrille(t_grille grille) {
    for (int indexLigne  = 0; indexLigne < TAILLE; indexLigne++) {
        for (int indexCol = 0; indexCol < TAILLE; indexCol++) {
            grille[indexLigne][indexCol] = VIDE;
        }
    }
}
/**
 * @brief Procedure permettant d'afficher la grille de jeu
 * 
 * @param grille la grille de jeu  
 */
void afficher(t_grille grille) {
    printf("Grille du Morpion :\n\n");
    printf("  ");
    for (int indexCol = 0; indexCol < TAILLE; indexCol++) {
            printf("  %d ", indexCol+1);
    }
    printf("\n");
    for (int indexLigne  = 0; indexLigne < TAILLE; indexLigne++) {
        printf("  -------------\n");
        printf("%d |",indexLigne+1);
        for (int indexCol = 0; indexCol < TAILLE; indexCol++) {
            printf(" %c |", grille[indexLigne][indexCol]);
        }
        printf("\n");
    }
    printf("  -------------\n");
    printf("\n");
}
/**
 * @brief Procedure permettant de saisir un chiffre
 * 
 * @param nombre le chiffre en question, ligne, colonne 
 */
void saisirNombre(int *nombre) {
    int valeur = 0;
    scanf("%d",&valeur);
    *nombre = valeur;
}
/**
 * @brief Procedure pour mettre la case selectionner dans la grille
 * 
 * @param grille la grille de jeu
 */
void jeuJoueur(t_grille grille) {
    int ligne, colonne;

    printf("Joueur, veuillez entrer les coordonnées (ligne et colonne) pour placer un X :\n");

    printf("Ligne (1-%d) : ", TAILLE);
    saisirNombre(&ligne);
    ligne--;
    printf("Colonne (1-%d) : ", TAILLE);
    saisirNombre(&colonne);
    colonne--;
    if (ligne >= 0 && ligne < TAILLE && colonne >= 0 && colonne < TAILLE && grille[ligne][colonne] == VIDE) {
        // Place un X à la position choisie
        grille[ligne][colonne] = JOUEUR;
    } else {
        printf("Coordonnées invalides ou Case déja remplie. Veuillez choisir à nouveau.\n");
        jeuJoueur(grille);
    }
}
/**
 * @brief Procedure permettant de faire jouer l'ordi
 * 
 * @param grille la grille de jeu 
 */
void jeuOrdi(t_grille grille) {
    int ligne, colonne;
    do {
        ligne = genererNombre(0, TAILLE - 1);
        colonne = genererNombre(0, TAILLE - 1);
    } while (grille[ligne][colonne] != VIDE && estComplete(grille) == false);
    if (estComplete(grille) == false) {
        grille[ligne][colonne] = ORDI;
    }
}
/**
 * @brief Fonction pour génerer un nombre
 * 
 * @param min avec un minimun 
 * @param max avec un maximun
 * @return un entier correspondant à la valeur trouvée
 */
int genererNombre(int min, int max) {
    srand(time(NULL));
    return rand() % (max - min + 1) + min;
}
/**
 * @brief Fonction qui verifie qui a gagné 
 * 
 * @param grille la grille de jeu 
 * @return un entier pour savoir qui a gagné 
 */
int verifierGagnant(t_grille grille) {
    int resultat = 3; 
    
    if(estComplete(grille) == false) {
        resultat = 0;
    }

    for (int i = 0; i < TAILLE; ++i) {
        if ((grille[i][0] == grille[i][1] && grille[i][1] == grille[i][2] && grille[i][0] != ' ') ||
            (grille[0][i] == grille[1][i] && grille[1][i] == grille[2][i] && grille[0][i] != ' ')) {
            if (grille[i][i] == JOUEUR) {
                resultat = 1; 
            } else if (grille[i][i] ==  ORDI) {
                resultat = 2; 
            }
        }
    }

    if ((grille[0][0] == grille[1][1] && grille[1][1] == grille[2][2] && grille[0][0] != ' ') ||
        (grille[0][2] == grille[1][1] && grille[1][1] == grille[2][0] && grille[0][2] != ' ')) {
        
        if (grille[1][1] == JOUEUR) {
            resultat = 1; 
        } else if (grille[1][1] == ORDI) {
            resultat = 2; 
        }
    }

    
    return resultat; 
}
/**
 * @brief Fonction pour savoir si la grille est complete 
 * 
 * @param grille la grille de jeu 
 * @return un booléen pour savoir si la grille est complete
 */
bool estComplete(t_grille grille) {
    bool complete = true;

    int indexLigne  = 0;
    int indexCol  = 0;

    while (complete == true && indexLigne < TAILLE) {
        indexCol = 0;
        while (complete == true && indexCol< TAILLE) {
            if ( grille[indexLigne][indexCol] == VIDE) {
                complete = false;
            }
            indexCol++;
        }
        indexLigne++;
    }

    return complete;
}
