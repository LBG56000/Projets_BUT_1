/*****
 * @author BRUNEAU-GACHE Léo 1D1
 * @brief programme sudoku
 * @date 26/11/2023
 * @version 10
 * Programme Sudoku fait par BRUNEAU-GACHE Léo 
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/***************************
 * DEFINITION DES CONSTANTES
****************************/
/**
 * @def on définit les numéros des lignes
 * @def on définit les numéros des colonnes
 * @def on definit la valeur maximum
 * @def on definit la valeur minimum
 * @def on definit les sépateurs de lignes
 * @def on définit les séparateurs de colonnes
 * @def on definit l'action de commencer 
 * @def action négative
*/

#define NB_LIGNE 9
#define NB_COL 9
const int VAL_MAX = 9;
const int VAL_MIN = 1;
const char SEP[35] = "+----------+----------+----------+";
const char COL[2] = "|";
const char PRET[2] = "Y";
const char PAS_PRET[2] = "N";

/*******************************************
 * DEFINITION DU TYPE t_Grille
 * @brief un tableau de NB_LIGNE et NB_COL*
 * @typedef t_Grille un type qui permet un tableau à deux dimensions
********************************************/

typedef int t_Grille[NB_LIGNE][NB_COL];

/******************************************************
 * DEFINITION DES PROTOTYPES DE PROCEDURE ET FONCTION
*******************************************************/

/**Procédure chargerGrille*/
void chargerGrille(t_Grille);
/**Procédure afficherGrille*/
void afficherGrille(t_Grille);
/**Procédure saisir*/
void saisir(int*);
/**Procedure afficheErreur*/
void afficheErreur(int*);
/**Fonction possible*/
int possible(t_Grille, int, int, int);
/**Fonction restePlace*/
int restePlace(t_Grille);
/**Fonction afficheRegles*/
int afficheRegles();


/************************
 * 
 * PROGRAMME PRINCIPAL*
 * 
*************************/

int main()
{
    /**declaration des variables des coordonnées de la case et de la valeur*/
    int numLigne;
    int numColonne;
    int valeur;
    /**definition du type t_Grille pour la grille de jeu*/
    t_Grille grille1;
    if(afficheRegles() == 1){
        chargerGrille(grille1);
        /**condition d'arret de continuer*/
        while (restePlace(grille1) == 1)
        {
            /**Utilisation de la procedure afficherGrille pour grille1*/
            afficherGrille(grille1);
            /**Rentrée des corrdonnées*/
            printf("Indice de la case\n");
            printf("Entrez le numero de Ligne\n");
            saisir(&numLigne);
            /**On enlève 1 car l'utilisateur tape un de plus par rapport à notre grille*/
            numLigne --;
            printf("Entrez le numero de la colonne\n");
            saisir(&numColonne);
            /**On enlève 1 car l'utilisateur tape un de plus*/
            numColonne --;
            /**On regarde si la case est libre*/
            if (grille1[numLigne][numColonne] != 0)
            {
                printf("IMPOSSIBLE, la case n'est pas libre.\n");
            }
            else
            {
                printf("Valeur à insérer\n");
                saisir(&valeur);
                if (possible(grille1, numLigne, numColonne, valeur) == 1)
                {
                    grille1[numLigne][numColonne] = valeur;
                }
            }
        }
        printf("Grille pleine,Bravo!!!!!!!!\n");
    }else{
        printf("Vous n'êtes pas pret revenez après");
    }
}

/****************************
 * 
 * PROCEDURES ET FONCTIONS*
 * 
******************************/

/**
 * @fn void chargerGrille(t_Grille g)
 * @brief Procédure qui charge la grille
 * @param g : la grille à charger
*/


void chargerGrille(t_Grille g)
{
    /**defintion d'une valeur pour rentrer le nom du fichier*/
    char nomFichier[30];
    /**association du nom logique et du nom externe*/
    FILE *f;
    printf("Nom du fichier ?\n");
    scanf("%s", nomFichier);
    f = fopen(nomFichier, "rb");
    /**on teste si on peut ouvrir le fichier**/
    if (f == NULL)
    {
        /**Affichage d'un message d'erreur*/
        printf("\n ERREUR sur le fichier %s\n", nomFichier);
    }
    else
    {
        fread(g, sizeof(int), NB_COL * NB_LIGNE, f);
    }
    fclose(f);
}


/**
 * @fn void afficherGrille(t_Grille grille)
 * @brief Procédure qui affiche la grille
 * @param grille : la grille à charger
 * Affiche la grille et affiche les valeurs
*/

void afficherGrille(t_Grille grille)
{
    /*on initialise des variable de boucles pour parcourir les lignes et colonnes**/
    int boucleNb;
    int boucleC;
    int boucleL;
    /**espace entre le bord**/
    printf("     ");
    /*on affiche la ligne du haut **/
    for (boucleNb = 0; boucleNb < NB_LIGNE; boucleNb++){
        printf("%d  ", boucleNb + 1);
        /*on met un espace toutes les 3 valeurs avec le modulo**/
        if (((boucleNb + 1) % 3) == 0){
            printf("  ");
        }
    }
    printf("\n");
    printf("  ");
    /**la première ligne de séparateur */
    printf("%s\n", SEP);
    for (boucleC = 0; boucleC < NB_COL; boucleC++)    
    {
        printf("%d", boucleC + 1);
        printf(" %s", COL);
        for (boucleL = 0; boucleL < NB_LIGNE; boucleL++)
        {
            /**On regarde si la valeur vaut 0 si c'est le cas c'est un .*/
            if (grille[boucleC][boucleL] == 0)
            {
                printf("  .");
            /**Dans le cas contraire on affiche la valeur*/
            }else{
                printf("  %d", grille[boucleC][boucleL]);
            }
            /**On regarde aussi avec le modulo pour afficher les colonnes*/
            /*Boucle + 1 car on veut commencer à 1*/
            if ((boucleL + 1) % 3 == 0)
            {
                printf(" %s", COL);
            }
        }
        printf("\n");
        /**Puis une autre ligne de separateur*/
        if ((boucleC + 1) % 3 == 0)
        {
            printf("  %s\n", SEP);
        }
    }
}

/**
 * @fn void saisir(int *valeur)
 * @brief Procédure qui saisie la valeur et vérifie sa conformité
 * @param valeur : la valeur qui est à saisir 
 * Vérifie la conformité de la valeur (entière,inférieur ou égale à 9 et supérieur  à 1) et la renvoie au programme
*/

void saisir(int *valeur)
{
    /**La valeur sera placé la en retour*/
    char val[50];
    /**Affectation de la valeur*/
    printf("Entrez la valeur\n");
    scanf("%s", val);
    /**Tant qu'elle n'est pas entière ou inférieur à 1 ou supérieur à 9 on affiche un message d'erreur*/
    while ((sscanf(val, "%d", valeur) == 0) || ((*valeur < VAL_MIN) || (*valeur > VAL_MAX)))
    {
        /**Le message d'erreur classique*/
        /**Puis la spécification du message pour que l'utilisateur comprend*/
        printf("Valeur non valide\n");
        if (sscanf(val, "%d", valeur) == 0)
        {
            printf("Valeur pas entière\n");
        }
        else
        {
            /**Utilisation d'une procédure permettant la reduction du code*/
            afficheErreur(valeur);
        }
        scanf("%s", val);
    }
}

/**
 * @fn int possible(t_Grille grille, int numLigne, int numCol, int val)
 * @brief Fonction qui renvoie si la valeur peut être placée à cet endroit
 * @param grille:la grille dans son ensemble 
 * @param numLigne: le numéro de la ligne
 * @param numCol: le numéro de la colonne
 * @param val : la valeur qui est à tester 
 * Vérifie si la valeur peut être placée à cet endroit et renvoie une valeur booléenne
*/
int possible(t_Grille grille, int numLigne, int numCol, int val)
{
    /*Initialisation des variables de boucles pour les colonnes et lignes*/
    int boucleC;
    int boucleL;
    /**Initialisation de la valeur boolénne de retour*/
    int correct;
    /**Initialisation des variables de boucles pour les blocs*/
    int blocCol;
    int blocLigne;
    int boucleBlocLig;
    int boucleBlocCol;
    correct = 1;
    boucleC = 0;
    /**On regarde tant que la valeur est correcte*/
    while(boucleC < NB_COL && correct == 1){
        boucleL = 0;
        /**On regarde tant que la valeur est correcte*/
        while (boucleL < NB_LIGNE && correct ==1)
        {
            /**Affichage du premier message d'erreur si la valeur est présente sur la ligne*/
            if (grille[numLigne][boucleC] == val)
            {
                printf("La valeur %d est presente sur la ligne %d\n",val,numLigne+1);
                correct = 0;
            }
            /**Affichage du deuxième message d'erreur si la valeur est présente sur la colonne*/
            if (grille[boucleL][numCol] == val)
            {
                printf("La valeur %d est presente sur la colonne %d\n",val,numCol+1);
                correct = 0;
            }
            boucleL++; 
        }
        boucleC ++;
    }
    /**Puis pour les blocs*/
    /**Mise en place d'une relation pour récupérer les coordonnées de la première valeur du bloc*/ 
    //Nous avons été plusieurs à la trouver pour récupérer les coordonnées des blocs
    blocLigne = (numLigne / 3)*3;
    blocCol = (numCol / 3)*3;
    boucleBlocLig = 0;
    boucleBlocCol =0;
    /**On parcourt les colonnes entre 0 et 3*/
    while( boucleBlocLig < 3 && correct == 1){
        /**On parcourt les lignes entre 0 et 3*/
        while( boucleBlocCol < 3 && correct == 1){
            /**Affichage du troisième message d'erreur si la valeur est présente dans le bloc*/
            if (grille[blocLigne+boucleBlocLig][blocCol+boucleBlocCol] == val){
                printf("La valeur %d est presente dans le bloc\n",val);
                correct = 0;
            }
        boucleBlocCol++;
        }
    /**On réinitialise la valeur pour passer à la colonne suivante*/
    boucleBlocCol = 0;
    boucleBlocLig++;    
    }
    return correct;
}

/**
 * @fn int restePlace(t_Grille grille)
 * @brief Fonction qui est utilisée pour savoir si il reste de la place
 * @param grille : la grille dans son ensemble
 * Vérifie dans la grille si il reste de la place donc plus aucune valeurs égale à 0
*/

int restePlace(t_Grille grille)
{
    /**Initialisation de la variable de retour*/
    int restePlace;
    /**Initialisation des variables de boucle lignes et colonnes*/
    int boucleL;
    int boucleC;
    restePlace = 0;
    boucleC = 0;
    /**On parcourt la grille par les colonnes*/
    while (boucleC < NB_COL && restePlace == 0)
    {
        boucleL = 0;
        /**On parcourt la grille par les lignes*/
        while (boucleL < NB_LIGNE && restePlace == 0)
        {
            /*On regardede si il reste des 0*/
            if (grille[boucleC][boucleL] == 0)
            {
                restePlace = 1;
            }
            boucleL++;
        }
        boucleC++;
    }
    return restePlace;
}

/**
 * @fn void afficheErreur(int *val)
 * @brief Fonction qui est utilisée pour décharger de la procédure possible
 * @param val :la valeur 
 * teste des conditions et affiche les erreurs associées 
*/


void afficheErreur(int *val)
{
    if (*val < 1)
    {
        printf("Valeur inférieur à 1\n");
        *val = 0;
    }
    if (*val > NB_LIGNE)
    {
        printf("Valeur supérieur à 9\n");
        *val = 0;
    }
}

/**
 * @fn int afficheRegles()
 * @brief Affiche au début de la partie les principales règles
 * Cette procédure permet d'afficher les principales règles mais aussi de savoir si le/la joueur.euse est pret.e
*/

int afficheRegles(){
    /*Initialisation de la variable de retour*/
    int estPret;
    /*La réponse*/
    char reponse[2];
    printf("Vous allez commencer une partie de sudoku\n");
    printf("Un jeu mis en forme par BRUNEAU-GACHE Leo\n");
    printf("Un petit rappel des regles ? tous les chiffres de 1 à 9 doivent etre present sur toutes les lignes colonnes et bloc de la grille\n");
    printf("Est vous pret.e ? Tapez Y pour Oui et N pour Non Y/N\n");
    scanf("%s",reponse);
    /*On regarde si la réponse est bien égale aux constantes*/
    if(strcmp(reponse,PRET)==0){
        estPret = 1;
    }else{
        estPret = 0;
    }
    return estPret;
    
}


