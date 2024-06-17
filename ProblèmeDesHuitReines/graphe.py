from collections import deque  
class Echiquier():
    def __init__(self, n) -> None:
        """
        Constructeur de la classe Echiquier
        self.n : nombre de cases de côté de l'échiquier
        self.grille : une grille 2d de n*n elements représentant l'échiquier (0 si la case est libre, 1 sinon)
        self.layout : un tableau d'images de cases noires ou blanches qui permettent l'affichage d'un échiquier        
        """
        
        self.n = n
        self.grille = []
        for i in range(n):
            self.grille.append([0]*n)   # une grille de n*n 0. Le 0 signifie que la case est libre, le 1 qu'une reine est sur la case
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

graphe = {}
def afficherG(graphe):
    for key,value in graphe.items():
        print(key,value)
def creerConnection(point1 : Echiquier,point2 : Echiquier):
    if point1 not in graphe:
        graphe[point1] = []
    if point2 not in graphe:
        graphe[point2] = []
    graphe[point1].append(point2)

def trouver (echec: Echiquier,ligne):
    tableau_posible = []
    for loop in range(echec.n):
        if echec.casePossible(ligne,loop):
            newEchec = Echiquier(echec.n)
            newEchec.grille = echec.grille
            newEchec.grille[ligne][loop] = 1
            tableau_posible.append(newEchec)
    return tableau_posible
   
def initGraphe(plateau:Echiquier,graphe:dict):
    #Init des valeurs
    echequier = Echiquier(plateau.n)
    graphe[echequier] = trouver(echequier,0)
    tableau  = []
    for ligne in range(1,plateau.n):
        graphe[ligne] = trouver(plateau,ligne)
        # ajouter au graphe une nouvelle ligne avec la liste des autres echéquier dispo
        # trouver(echequier,ligne) ligne du dessous
    print(tableau)

def parcoursPlusLong(graphe):
    print(max(graphe))


def Dijkstra(graphe,depart):
    D = [float('inf') for i in range(depart)]
    D[depart] = 0
    P = []
    Q = deque((0,depart))
    while len(Q) != 0:
        dist_act,somm_act = deque(Q)
        for voisin,poids in graphe[somm_act].items():
            nouvelle = dist_act + poids
            if nouvelle > D[voisin]:
                D[voisin] = nouvelle
                P[voisin] = somm_act
                deque.push(Q ,(nouvelle,voisin))

    return (D,P)

echec = Echiquier(8)
initGraphe(echec,graphe)
# afficherG()
echec.afficher_grille()
# parcoursPlusLong(graphe)
# Dijkstra(echec,0)

