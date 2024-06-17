import time

#Fonction brute force testant toutes les possibilités lignes par lignes et colonnes par colonnes avec la position d'une reine imposée

def brute_force(echiquier, rangee, grille, position_imposee):
    
    global nb
    
    # Si on a placé toutes les reines sur l'échiquier, on affiche l'échiquier
    if rangee == grille:
        #afficher(echiquier)
        nb += 1  # On incrémente le compteur de solutions trouvées
        return

    # Pour chaque colonne dans la rangée actuelle
    for colonne in range(grille):
        
        # Si aucune reine n'est en conflit avec la case actuelle
        if not conflit_reines(echiquier, rangee, colonne, grille):
            
            # Placer une reine dans cette case
            echiquier[rangee][colonne] = 1
            
            # Si la case est celle où la reine doit être placée, passer à la rangée suivante
            if rangee == position_imposee[0] and colonne == position_imposee[1]:
                brute_force(echiquier, rangee + 1, grille, position_imposee)
                
            # Sinon, passer à la rangée suivante
            elif rangee != position_imposee[0]:
                brute_force(echiquier, rangee + 1, grille, position_imposee)
                
            # Retirer la reine de cette case pour tester d'autres possibilités
            echiquier[rangee][colonne] = 0

#-----------------------------------------------------------------------------------------------------------#
            
# Fonction permettant de choisir la taille de la grille

def taille_grille():
    
    while True:
        
        try:
            grille = int(input("Entrez la taille de la grille : "))  # Demander la taille de la grille à l'utilisateur
            if grille <= 0:
                raise ValueError
            
            return grille
        
        except ValueError:
            print("Taille invalide. Entrez une taille de grille valide (entier positif).")

#-----------------------------------------------------------------------------------------------------------#

# Fonction demandant la position de la première reine

def premiere_reine(message, grille):
    
    while True:
        
        try:
            
            ligne = int(input(f"Entrez la colonne {message} (entre 1 et {grille}): "))-1
            colonne = int(input(f"Entrez la ligne {message} (entre 1 et {grille}): "))-1
            
            if not (0 <= ligne < grille and 0 <= colonne < grille):
                raise ValueError
            
            return (ligne, colonne)
        
        except ValueError:
            print(f"Position invalide. Entrez des valeurs entières valides entre 0 et {grille-1}.")

#-----------------------------------------------------------------------------------------------------------#
            
# Fonction pour vérifier s'il y a un conflit sur la case spécifiée

def conflit_reines(echiquier, ligne, colonne, grille):
    
    # Vérification de la colonne
    for i in range(ligne):
        
        if  echiquier[i][colonne] == 1:
            return True

    # Vérification de la diagonale supérieure gauche
    i, j = ligne - 1, colonne - 1
    
    while i >= 0 and j >= 0:
        
        if echiquier[i][j] == 1:
            return True
        i -= 1
        j -= 1

    # Vérification de la diagonale supérieure droite
    i, j = ligne - 1, colonne + 1
    
    while i >= 0 and j < grille:
        
        if echiquier[i][j] == 1:
            return True
        i -= 1
        j += 1

    return False

#-----------------------------------------------------------------------------------------------------------#

# Fonction pour afficher l'échiquier

def afficher(echiquier):
    
    print("\n".join("[{}]".format(", ".join(map(str, row))) for row in echiquier) + "\n")

#-----------------------------------------------------------------------------------------------------------#

# Fonction principale du programme

def main():
    
    grille = taille_grille()  # Demander la taille de la grille
    position_reine = premiere_reine("Entrez la position de la reine (ligne colonne) : ", grille)  # Demander la position de la première reine

    # Initialisation de l'échiquier
    echiquier = [['0' for _ in range(grille)] for _ in range(grille)]

    global nb
    
    nb = 0  # Initialiser le compteur de solutions trouvées
    t1 = time.time()
    brute_force(echiquier, 0, grille, position_reine)  # Tester toutes les possibilités
    t2 = time.time()
    print("\nNombre de solutions trouvées avec une reine en position donnée:", nb)  # Afficher le nombre de solutions trouvées
    print("solution trouvée en",round(t2-t1, 10), "secondes\n")

#-----------------------------------------------------------------------------------------------------------#
    
# Execution du script


