/**
 * Ce programme permet de créer un agenda avec des actions à faire 
 * elles seront rentrées par l'utlisateur
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**Structure pour représenter une date*/
typedef struct
{
    int jour;  /**Representant le jour*/
    int mois;  /**Representant le mois */
    int annee; /**Representant l'annee*/
} t_date;

/**Structure pour représenter une tâche à réaliser*/
typedef struct
{
    t_date date;    /**Avec la structure date */
    char nom[50];   /**Avec le nom d'une tache*/
    char desc[999]; /**Description*/
} t_tache;

/**Structure pour représenter un calendrier de tâches à faire*/
typedef struct
{
    t_tache taches[100]; /**La structure tâches*/
} t_calendrier;

int menu();
void afficherTaches(t_calendrier *cal, int *stack);
void trierTaches(t_calendrier *cal, int *stack);
void echangeTaches(int indexTache1, int indexTache2, t_calendrier *cal);
void addTache(t_calendrier *cal, int *stack);
t_tache saisirTache(t_calendrier *cal);
void supTache(t_calendrier *cal, int *stack);

/**
 * \brief programme principal permettant la saisie des tâches
 * \return 0 si le programme s'est deroulee sans accros
 * Programme principal permettant de rentrer une tache à faire, de supprimer ou d'afficher la tache
 */
int main()
{
    /**Initialisation du calendrier et du compteur de tâches*/
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

/**
 * \brief Fonction pour afficher le menu et obtenir le choix de l'utilisateur
 * \return le choix de l'utilisateur
 */

int menu()
{
    int reponse = 0;
    printf("Que voulez vous faire aujourd'hui ?\n");
    printf("1 : Afficher les taches\n");
    printf("2 : Ajouter une tache\n");
    printf("3 : Supprimer une tache\n");
    printf("4 : Sortie\n");
    scanf("%d", &reponse);

    return reponse;
}

/**
 * \brief Procedure pour afficher les tâches triees par date
 *
 * \param cal de type t_calendrier
 * \param stack la pile de tâches
 */
void afficherTaches(t_calendrier *cal, int *stack)
{
    int jour = 0;
    int mois = 0;
    int annee = 0;
    printf("\n");
    if (*stack == 0)
    {
        printf("Pas de taches :)\n");
    }
    else
    {
        /** Trie les tâches avant de les afficher*/
        trierTaches(cal, stack);
        for (int index = 1; index < *stack + 1; index++)
        {
            jour = cal->taches[index].date.jour;
            mois = cal->taches[index].date.mois;
            annee = cal->taches[index].date.annee;

            printf("[%d][%d/%d/%d] %s : %s\n", index, jour, mois, annee, cal->taches[index].nom, cal->taches[index].desc);
        }
    }
    printf("\n");
}

/**
 * \brief procedure pour trier les tâches par date
 * \param cal de type t_calendrier
 * \param stack de type entier
 * Procedure pour trier les tâches par date
 */

void trierTaches(t_calendrier *cal, int *stack)
{
    if (*stack > 1)
    {
        for (int x = 1; x < *stack; x++)
        {
            for (int y = 1; y < *stack; y++)
            {
                t_date dateTache1 = cal->taches[y].date;
                t_date dateTache2 = cal->taches[y + 1].date;
                /** Compare les dates numériquement*/
                if (dateTache1.annee > dateTache2.annee ||
                    (dateTache1.annee == dateTache2.annee && dateTache1.mois > dateTache2.mois) ||
                    (dateTache1.annee == dateTache2.annee && dateTache1.mois == dateTache2.mois && dateTache1.jour > dateTache2.jour))
                {
                    /** Échange les tâches si nécessaire*/
                    echangeTaches(y, y + 1, cal);
                }
            }
        }
    }
}

/**
 * \brief Cette procedure permet d'echanger les tâches pour les trier
 * \param indexTache1 l'index de la premiere tâche à échanger
 * \param indexTache2 l'index de la deuxième tâche à échanger
 * \param cal le calendrier final
 * Cette procedure permet de changer les tâches pour les trier
 */
void echangeTaches(int indexTache1, int indexTache2, t_calendrier *cal)
{
    t_tache tacheTemp;
    /**Echange*/
    tacheTemp = cal->taches[indexTache1];
    cal->taches[indexTache1] = cal->taches[indexTache2];
    cal->taches[indexTache2] = tacheTemp;
}

/**
 * \brief Procedure pour ajouter une tache au calendrier
 * \param cal le calendrier
 * \param stack la pile de tâche
 * Procedure pour ajouter une tâche au calendrier
 * et de la rajouter à la pile
 */
void addTache(t_calendrier *cal, int *stack)
{
    /**Saisie*/
    t_tache tacheAjoute = saisirTache(cal);
    *stack = *stack + 1;
    cal->taches[*stack] = tacheAjoute;
    int jour = tacheAjoute.date.jour;
    int mois = tacheAjoute.date.mois;
    int annee = tacheAjoute.date.annee;
    printf("Une nouvelle tache a ete ajoute ! ([%d/%d/%d] %s : %s )\n", jour, mois, annee, tacheAjoute.nom, tacheAjoute.desc);
}
/**
 * \brief Fonction permettant de saisir une tâche
 *
 * \param cal le calendrier
 * \return t_tache
 * Fonction permettant de saisir une tâche
 * en saissisant la date
 */

t_tache saisirTache(t_calendrier *cal)
{
    t_date date = {
        .jour = 0,
        .mois = 0,
        .annee = 0};
    /** Saisit la date de la tâche*/
    printf("Un jour ?\n");
    scanf("%d", &date.jour);
    printf("Un Mois ?\n");
    scanf("%d", &date.mois);
    printf("Une Annee ?\n");
    scanf("%d", &date.annee);

    t_tache nouvTache = {
        .date = date,
        .nom = "",
        .desc = ""};
    /** Saisit le nom et la description de la tâche*/
    printf("Un Nom ?\n");
    scanf("%s", &nouvTache.nom);
    printf("Une Description ?\n");
    scanf("%s", &nouvTache.desc);

    return nouvTache;
}

/**
 * \brief Procedure pour supprimer une tâche dans un calendrier
 * \param cal le calendrier ou ne devons supprimer la tâche
 * \param stack la pile de tâche ou nous devons supprimer la tâche
 * Procedure pour supprimer une tâche dans un calendrier
 */
void supTache(t_calendrier *cal, int *stack)
{
    int index = 0;
    printf("Quel taches souhaitez vous supprimer ?\n");
    scanf("%d", &index);
    for (int decale = index; decale < *stack; decale++)
    {
        echangeTaches(decale, decale + 1, cal);
    }
    printf("La tache du [%d/%d/%d] a ete retirer\n", cal->taches[index].date.jour, cal->taches[index].date.mois, cal->taches[index].date.annee);
    *stack = *stack - 1;
}
