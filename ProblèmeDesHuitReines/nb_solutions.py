import math
import time
import classes as cl


#------------------ utilisant un backtracking ------------------
def backtrackingGrille(e : cl.Echiquier, l, lReine1 = None, solutions_set=None):
    if(l == lReine1):
        return backtrackingGrille(e, l+1, lReine1, solutions_set)
    
    if (l >= e.n):
        return 1  # Une solution trouvée
    
    count = 0  # Initialiser le compteur de solutions
    
    for c in range(e.n):
        if(e.casePossible(l, c)):
            e.grille[l][c] = 1
            
            # Récursivement chercher les solutions
            count += backtrackingGrille(e, l+1, lReine1, solutions_set)
            
            e.grille[l][c] = 0
    
    # Vérifier si la solution n'a pas déjà été trouvée
    solution_hash = hash(str(e.grille))
    if solutions_set is not None and solution_hash not in solutions_set:
        solutions_set.add(solution_hash)
        return count  # Retourner le nombre total de solutions
    else:
        return 0  # Solution déjà trouvée, ne pas compter

def resolutionTerminal(n, l, c):
    e = cl.Echiquier(n)
    e.grille[l][c] = 1
    t1 = time.time()
    solutions_set = set()  # Ensemble pour stocker les solutions uniques
    solutions = backtrackingGrille(e, 0, l, solutions_set)
    t2 = time.time()
    print("solution trouvée en", round(t2-t1, 10), "secondes")
    print("Nombre de solutions possibles avec la première reine donnée:", solutions)

#-------------------- utilisant les graphes --------------------




def nbSolutionGraphe(arbre : cl.Arbre):
    return len(arbre.niveaux[arbre.profondeur-1])

def solutionReineDonnee(taille,l,c):
    arbre = cl.Arbre(taille)
    arbre.remplir()
    solution = []
    for sommet in arbre.niveaux[taille-1]:
        if sommet.echiquier.grille[l][c] == 1:
            solution.append(sommet.echiquier)
    return solution, len(solution)

# main()

# arbre = cl.Arbre(5)
# arbre.remplir()
# print(nbSolutionGraphe(arbre))
# print(solutionReineDonnee(8,5,6))


