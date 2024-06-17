import PySimpleGUI as sg
import math
import time

import classes as cl

sg.theme('darkBrown6') #couleur de l'arrière plan de la fenêtre (pour l'affichage)

def backtrackingGrille(e : cl.Echiquier, l, lReine1 = None):
    """algorithme de backtracking"""
    if(l == lReine1): #reine imposée
        return backtrackingGrille(e, l+1, lReine1)
    if (l >= e.n): #condition d'arrêt, si on est à la dernière ligne
        return True
    else:
        for c in range(e.n): 
            if(e.casePossible(l, c)): #cherche une où placer une dame sur la ligne l
                e.grille[l][c] = 1
                
                if(backtrackingGrille(e, l+1, lReine1)): #si on arrive à placer une case sur la ligne suivante
                    return True
                else:
                    e.grille[l][c] = 0
        return False #impossible de placer une reine sur la ligne


def backtrackingVisuel(window : sg.Window, e : cl.Echiquier, l, lReine1 = None):
    """même fonctionnement que le backatracking précédent, mais met également à jour l'affichage dans le fenêtre"""
    event, value = window.read(timeout=10)
    if(l == lReine1):
        return backtrackingVisuel(window,e, l+1, lReine1)
    if (l >= e.n):
        return True
    else:
        
        for c in range(e.n):
            if(e.casePossible(l, c)):
                e.ajouteReine(window, l, c) #ajoute reine à l'affichage de l'échiquier
                e.grille[l][c] = 1
                
                if(backtrackingVisuel(window, e, l+1, lReine1)):
                    return True
                else:
                    e.grille[l][c] = 0
                    e.supprimeReine(window, l, c)   #retire reine de l'affichage de l'échiquier
        return False

def resolutionAvecVisuel(n, l, c):
    """résoud le problème des n reines avec la première reine placée sur la case (l,c)
    affiche la solution et le temps d'exécution si le booléen correspondant vaut True"""
    
    e = cl.Echiquier(n)    #instance d'échiquier
    window = sg.Window("reines", e.layout)
    e.grille[l][c] = 1  #place la première dame sur la case imposée
    backtracking_done = False
    #--------------pas touche--------------
    while True:
        event, value = window.read(timeout=0)
        
        if event == sg.WIN_CLOSED:
            break
        #--------------------------------------
        
        if not backtracking_done:   #évite que le backtracking ne s'exécute encore et encore à chaque itération de la boucle servant à afficher la fenêtre
            e.ajouteReine(window, l, c)
            backtrackingVisuel(window, e, 0, l)
            backtracking_done = True

def resolutionTerminal(n, l, c, afficheResultat = True):
    """résoud le problème des n reines avec la première reine placée sur la case (l,c)
    affiche la solution et le temps d'exécution si le booléen correspondant vaut True"""
    e = cl.Echiquier(n)    #instance d'échiquier
    e.grille[l][c] = 1  #place la première dame sur la case imposée
    t1 = time.time()
    solutionTrouvee = backtrackingGrille(e, 0, l)
    if (afficheResultat):
        if(solutionTrouvee):
            e.afficher_grille()
            t2 = time.time()
            print("solution trouvée en",round(t2-t1, 10), "secondes")
        else:
            print("aucune solution n'a été trouvée")


