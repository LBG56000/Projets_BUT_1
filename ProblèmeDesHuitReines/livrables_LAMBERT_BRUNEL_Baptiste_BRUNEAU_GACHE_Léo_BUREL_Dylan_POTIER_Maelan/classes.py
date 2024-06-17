import PySimpleGUI as sg
import math
import time

sg.theme('darkBrown6') #couleur de l'arrière plan de la fenêtre (pour l'affichage)

def copieGrille(grille1):
    """crée une autre grille, y copie le contenu de grille1 et la renvoie"""
    grille2 = []
    for i in range(len(grille1)):
        grille2.append([0]*len(grille1)) #crée grille2 vide
        
    for l in range(len(grille1)):
        for c in range(len(grille1)):
            grille2[l][c] = grille1[l][c] #copie grille1
            
    return grille2

class Echiquier():
    def __init__(self, n, grille=None):
        """
        Constructeur de la classe Echiquier
        self.n : nombre de cases de côté de l'échiquier
        self.grille : une grille 2d de n*n elements représentant l'échiquier, vide si aucune grille n'est
                      passée en paramètre(0 si la case est libre, 1 sinon)
        self.layout : un tableau d'images de cases noires ou blanches qui permettent l'affichage d'un échiquier        
        """
        
        self.n = n
        
        self.grille = []
        if (grille != None):
            self.grille = copieGrille(grille)
        else:
            for i in range(n):
                self.grille.append([0]*n)   # une grille de n*n 0. Le 0 signifie que la case est libre, le 1 qu'une reine est sur la case

    
        
        # ------------------partie affichage------------------
        tab_images = []
        for l in range(self.n):
            tab_images.append([])
            for c in range(self.n):
                if(self.grille[l][c] == 1):
                    if((l+c)%2 == 0): #permet l'aternance entre une case blanche et une case noire
                        tab_images[l].append(sg.Image(filename="images/reine_case_blanche.png", key=(l, c)))
                    else:
                        tab_images[l].append(sg.Image(filename="images/reine_case_noire.png", key=(l, c)))
                else:
                    if((l+c)%2 == 0): #permet l'aternance entre une case blanche et une case noire
                        tab_images[l].append(sg.Image(filename="images/case_blanche.png", key=(l, c)))
                    else:
                        tab_images[l].append(sg.Image(filename="images/case_noire.png", key=(l, c)))
                            
        self.layout = [tab_images] # un tableau contenant tous les éléments qui seront affichés
        # -----------------------------------------------------
    
    def __repr__(self):
        res = ""
        for l in self.grille:
            res += str(l) + "\n"
        return res
    
    #------------ méthodes grille ------------
    
    def afficher_grille(self):
        """affiche la grille dans le terminal"""
        for i in range(self.n):
            print(self.grille[i])
        print(" ")
       
    def casePossible(self, l, c):
        """renvoie true si il est possible de placer une reine sur la case de coordonnées (l,c), false sinon"""
        if (1 in self.grille[l]):
            return False

        for ligne in self.grille:
            if(ligne[c] == 1):
                return False

        for i in range(self.n):
            for j in range(self.n):
                if(i+j == l+c or i-j == l-c):
                    if(self.grille[i][j] == 1):
                        return False
        return True
    
               
    #------------ méthodes affichage ------------
    
    def ajouteReine(self, window, ligne, colonne):
        """place une reine sur la case de coordonnées (ligne, colonne)"""
        if (ligne >= 0 and colonne >= 0 and ligne < self.n and colonne < self.n): #si coordonnées dans les bornes

            if((ligne+colonne)%2 == 0):
                window[(ligne, colonne)].update(filename='images/reine_case_blanche.png')
            else:
                window[(ligne, colonne)].update(filename='images/reine_case_noire.png')

    def supprimeReine(self, window, ligne, colonne):
        """supprime une reine sur la case de coordonnées (ligne, colonne)"""
        if (ligne >= 0 and colonne >= 0 and ligne < self.n and colonne < self.n): #si coordonnées dans les bornes
 
            if((ligne+colonne)%2 == 0):
                window[(ligne, colonne)].update(filename='images/case_blanche.png')
            else:
                window[(ligne, colonne)].update(filename='images/case_noire.png')
    #--------------------------------------------
   
 
class Sommet():
    """
    représentation d'un sommet d'un graphe (arbre plus précisément) d'Echiquiers contenant un Echiquier avec des
    reines placées ou non
    ses enfant sont les différents échiquiers possibles après avoir ajouté une 
    dame si c'est possible à l'Echiquier actuel
    """
    def __init__(self, e : Echiquier):
        """
        constructeur de la classe Sommet
        self.echiquier : instance d'Echiquier contenue par le sommet
        self.enfants : tableau contenant tous les Sommets contenant des Echiquiers où il 
        est possible d'ajouter une reine à self.echiquier
        """
        self.echiquier = e
        self.enfants = []

    def __repr__(self):
        return "sommet"
    
    def trouverEnfants(self, ligne):
        """ajoute tous les enfants possibles dans self.enfants"""
        for colonne in range(self.echiquier.n):
            
            if(self.echiquier.casePossible(ligne, colonne)):
                nouvel_echiquier = Echiquier(self.echiquier.n, self.echiquier.grille)
                nouvel_echiquier.grille[ligne][colonne] = 1
                self.enfants.append(Sommet(nouvel_echiquier))


class Arbre():
    """
    représentation d'un graphe de type arbre
    chaque sommet est une instance de la classe Sommet et représente un état de l'échiquier, et ses enfant 
    sont les différents échiquiers possibles après avoir ajouté une dame si c'est possible
    la racine est un échiquier vide
    les feuilles sont des échiquiers remplis, donc les solutions au problème des n reines
    """
    
    def __init__(self, profondeur):
        """
        constructeur de la classe Arbre
        self.profondeurMax : profondeur que peut avoir l'arbre (correspond au nombre de reines à placer dans l'échiquier)
        self.niveaux : tableau de tableaux, chaque tableau correspond à un niveau de profondeur de l'arbre et contient tous
        les sommets présents à ce niveau
        """
        self.profondeur = profondeur
        self.niveaux = [] # tableau de tableaux contenant tous les sommets pour chaque niveau de l'arbre        
        for i in range(self.profondeur):
            self.niveaux.append([])
        
        #remplissage du premier niveau de l'arbre avec tous les échiquiers possibles ayant une dame sur la première ligne
        for i in range(self.profondeur):
            s = Sommet(Echiquier(self.profondeur))
            s.echiquier.grille[0][i] = 1
            self.niveaux[0].append(s)
    
    def remplir(self, display=False):
        """remplis l'arbre des sommets correspondants"""
        for indice_niv in range(self.profondeur):
            if(display):
                #affichage du nombre de sommets par niveau de l'arbre
                print("-----------------------")
                print(f"niveau {indice_niv} : {len(self.niveaux[indice_niv])} sommets")
                
            # pour tous les sommets de chaque niveau, trouve ses enfants et les ajoute au niveau suivant de l'arbre
            for sommet in self.niveaux[indice_niv]:
                
                if (indice_niv+1 < self.profondeur):
                    sommet.trouverEnfants(indice_niv+1)
                    
                    for enfant in sommet.enfants:
                        self.niveaux[indice_niv+1].append(enfant)


grille = [[1,0,0,0,0,0,0,0],
          [0,0,1,0,0,0,0,0],
          [0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0]]

def testTrouveAdj():
    e = Echiquier(8, grille)
    s = Sommet(e)
    s.trouverEnfants(2)

    for sommet in s.enfants:
        print(sommet.echiquier)
    print(str(len(s.enfants)) + " enfants")

def testRemplir():
    e = Echiquier(8)
    a = Arbre(e.n)
    a.remplir(display=True)

def nbSolutionGraphe(arbre : Arbre):
    return len(arbre[arbre.profondeur])
