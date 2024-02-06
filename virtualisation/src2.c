/**
 * Ce programme vise à choisir un mode de tri.
 * Puis selon le choix de l'utilisateur on trie la chaine
 * Il permet de voir différent type de tri
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 10 /**On definit une taille maximale pour la chaine*/
typedef int chaine[N]; /**La chaine de nombres à trier*/

int menu();
void initChaine(chaine c);
void afficherChaine(chaine c);
void trieSelection(chaine c);
void trieDichotomie(chaine c, int debut, int fin);
int partition(chaine c, int debut, int fin);
void trieInsertion(chaine c);
void echange(int *indexA, int *indexB);

/**
 * \brief programme principal permettant de trier une chaine
 * \return renvoie 0 si le programme s'est déroulé sans accros
*/
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
/**
 * \brief Il s'agit de la d'une fonction qui à pour but d'obtenir le choix de l'utilisateur pour l'algorithme de tri
 * 
 * \return reponse correspond au choix de l'utilisateur
 * Cette fonction nous permet de connaitre le choix de l'utilisateur sur plusieurs algorithme
 */
int menu() {
    int reponse = 0;
    printf("Quel algorythme ?\n");
    printf("1 : Trie par Selection\n");
    printf("2 : Trie par Dichotomie\n");
    printf("3 : Trie par Insertion\n");
    printf("4 : Sortie\n");
    scanf("%d",&reponse);
    
    return reponse;
}
/**
 * \brief Procédure permettant d'initialiser la chaine avec des valeurs aléatoires
 * 
 * \param c un parametre de type chaine  correspondant à la chaine à trier 
 * Procédure permetattant d'initialiser la chaine avec des valeur aléatoire 
 */
void initChaine(chaine c) {
    srand(time(NULL)); 
    for (int i = 0; i < N; i++) {
        c[i] = rand() % 100; 
    }
}

/**
 * \brief Cette procédure permet d'afficher la chaine passée en paramètre
 * 
 * \param c la chaine à afficher 
 * Cette procédure permet d'afficher la chaine passée en paramètre
 */

void afficherChaine(chaine c) {
    printf("[");
    for (int i = 0; i < N-1; i++) {
        printf("%d-", c[i]);
    }
    printf("%d]\n", c[N-1]);
}

/**
 * \brief Cette procedure permet de trier la chaine avec le tris par selection en selectionnant une valeur minimun et en la comparant avec les autres
 * 
 * \param c la chaine à trier 
 * Cette procedure permet de trier la chaine avec le tris par selection 
 * En selectionnant une valeur minimun et en la comparant avec les autres
 */

void trieSelection(chaine c) {
    int i, j, minIndex, temp;

    for (i = 0; i < N-1; i++) {
        minIndex = i;
        for (j = i + 1; j < N; j++) {
            if (c[j] < c[minIndex]) {
                minIndex = j;
            }
        }

        echange(&c[i], &c[minIndex]);
        afficherChaine(c);
    }
}

/**
 * \brief Cette procédure permet d'échanger deux valeurs contenu dans le tableau grace aux indices
 * 
 * \param indexA première valeur à échanger 
 * \param indexB deuxième valeur à échanger
 * Cette procédure permet d'échanger deux valeurs contenu dans le tableau
 */
void echange(int *indexA, int *indexB) {
    int temp = *indexA;
    *indexA = *indexB;
    *indexB = temp;
}

/**
 * \brief Cette procedure permet de réaliser le tris dichotomique
 * 
 * \param c la chaine en question 
 * \param debut l'indice de debut chaine
 * \param fin l'indice de fin de la chaine
 * Cette procedure permet de réaliser le tris dichotomique
 */

void trieDichotomie(chaine c, int debut, int fin) {
    if (debut < fin) {
        int pivotIndex = partition(c, debut, fin);
        trieDichotomie(c, debut, pivotIndex - 1);
        trieDichotomie(c, pivotIndex + 1, fin);
        afficherChaine(c);
    }
}

/**
 * \brief Fonction pour partitionner le tableau pour le tri dichotomique
 * 
 * \param c la chaine 
 * \param debut l'indice de debut 
 * \param fin l'indice de fin 
 * \return i + 1
 */

int partition(chaine c, int debut, int fin) {
    int pivot = c[fin];
    int i = debut - 1;

    for (int j = debut; j <= fin - 1; j++) {
        if (c[j] < pivot) {
            i++;
            echange(&c[i], &c[j]);
        }
    }

    echange(&c[i + 1], &c[fin]);
    return i + 1;
}

/**
 * \brief Procédure pemettant le tris par insertion
 * 
 * \param c la chaine à trier 
 * Procédure pemettant le tris par insertion 
 */

void trieInsertion(chaine c) {
    int i, j, temp;

    for (i = 1; i < N; i++) {
        temp = c[i];
        j = i - 1;

        while (j >= 0 && c[j] > temp) {
            c[j + 1] = c[j];
            j--;
        }

        c[j + 1] = temp;
        afficherChaine(c);
    }
}
