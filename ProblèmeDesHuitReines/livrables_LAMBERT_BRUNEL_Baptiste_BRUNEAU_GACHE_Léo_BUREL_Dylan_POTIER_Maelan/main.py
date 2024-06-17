import time
import nb_solutions as nb_s
import backtracking as ba
import Brute_force as br

def choix_infos_echiquier():
    n = int(input("quelle sera la taille de l'échiquier (nombre de cases de côté)\n"))
    ligne = int(input(f"entrez la ligne où placer la première reine (entre 1 et {n})\n"))-1
    colonne = int(input(f"entrez la colonne où placer la première reine (entre 1 et {n})\n"))-1
    
    return n,ligne,colonne


def menu_backtracking():
    print("\nmenu backtracking")
    print("----------------------------")
    choix_valide = False
    while not choix_valide:
        print("veuillez choisir une action à effectuer")
        print("1 : voir la résolution du problème des n reines étape par étape pour une reine imposée")
        print("2 : voir une solution et connaître le temps d'exécution pour une reine imposée")
        print("3 : voir le nombre de solutions possibles et le temps d'exécution")
        print("4 : revenir au menu principal")
        
        c = int(input())
        
        if(c >= 1 or c <= 2):
            choix_valide = True
        else:
            print("choix invalide")
    
    if c != 4:
        n,ligne,colonne = choix_infos_echiquier()
    
    if c == 1:
        ba.resolutionAvecVisuel(n, ligne, colonne)
    elif c == 2:
        ba.resolutionTerminal(n, ligne, colonne)
    elif c == 3:
        nb_s.resolutionTerminal(n,ligne, colonne)
    
    if c != 4:
        menu_backtracking()
        

def menu_graphe():
    print("\nmenu graphes")
    print("----------------------------")
    choix_valide = False
    while not choix_valide:
        print("veuillez choisir une action à effectuer")
        print("1 : connaître le nombre de solutions et le temps d'exécution")
        print("2 : connaître le nombre de solutions et le temps d'exécution pour une reine imposée")
        print("3 : revenir au menu principal")
        
        c = int(input())
        
        if(c >= 1 and c <=3):
            choix_valide = True
        else:
            print("choix invalide")

    if c != 3:
        n = int(input("quelle sera la taille de l'échiquier (nombre de cases de côté)\n"))
        t1 = time.time()
        arbre = nb_s.cl.Arbre(n)
        arbre.remplir()
        t2 = time.time()
        tmp_creation_arbre = t2-t1
        
        if (c == 1):
            t1 = time.time()
            nb_solutions = nb_s.nbSolutionGraphe(arbre)
            t2 = time.time()
            tmp_recherche = t2-t1
            print("\nle nombre de solution est :", nb_solutions)
            print("arbre crée en", round(tmp_creation_arbre, 10), "secondes")
            print("résultat trouvé en", round(tmp_recherche), "secondes")
        else:
            ligne = int(input(f"entrez la ligne (entre 1 et {n})\n"))-1
            colonne = int(input(f"entrez la colonne (entre 1 et {n})\n"))-1
            
            t1 = time.time()
            nb_solutions = nb_s.solutionReineDonnee(n,ligne,colonne)[1]
            t2 = time.time()
            tmp_recherche = t2-t1
            print("le nombre de solution est :", nb_solutions)
            print("arbre crée en", round(tmp_creation_arbre, 10), "secondes")
            print("résultat trouvé en", round(tmp_recherche, 10), "secondes")
                    
        menu_graphe()

def menu_bruteforce():
    print("\nmenu bruteforce")
    print("----------------------------")
    choix_valide = False
    while not choix_valide:
        print("veuillez choisir une action à effectuer")
        print("1 : connaître le nombre de solutions et le temps d'exécution")
        print("2 : revenir au menu principal")
        
        c = int(input())
        
        if(c >= 1 and c <=2):
            choix_valide = True
        else:
            print("choix invalide")
    
    if c == 1:
        br.main()
    if c != 2:
        menu_bruteforce()
        

def choix_principal():
    choix_valide = False
    while not choix_valide:
        print("\nque voulez-vous utiliser ?")
        print("1 : le backtracking")
        print("2 : le graphe")
        print("3 : le bruteforce")
        print("----------------------------")
        print("entrez 0 pour quitter")
        
        c = int(input())
        if (c >= 0 and c<= 3):
            choix_valide = True
        else:
            print("choix invalide\n")
        
        return c

def menu_principal():
    c = -1
    while c != 0:
        c = choix_principal()
        
        if c == 1:
            menu_backtracking()
        elif c == 2:
            menu_graphe()
        elif c == 3:
            menu_bruteforce()
        else:
            print("sortie du programme")
        
menu_principal()