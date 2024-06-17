def tester_toutes_les_possibilites(echiquier, rangee, taille_grille, position_imposee):
    global count
    if rangee == taille_grille:
        afficher_echiquier(echiquier)
        count += 1
        return

    for colonne in range(taille_grille):
        if not conflit_sur_case(echiquier, rangee, colonne, taille_grille):
            echiquier[rangee][colonne] = 'Q'
            if rangee == position_imposee[0] and colonne == position_imposee[1]:
                tester_toutes_les_possibilites(echiquier, rangee + 1, taille_grille, position_imposee)
            elif rangee != position_imposee[0]:
                tester_toutes_les_possibilites(echiquier, rangee + 1, taille_grille, position_imposee)
            echiquier[rangee][colonne] = '.'

def demander_taille_grille():
    while True:
        try:
            taille_grille = int(input("Entrez la taille de la grille: "))
            if taille_grille <= 0:
                raise ValueError
            return taille_grille
        except ValueError:
            print("Taille invalide. Entrez une taille de grille valide (entier positif).")

def demander_position(message, taille_grille):
    while True:
        try:
            position = input(message).split()
            if len(position) != 2:
                raise ValueError
            ligne = int(position[0])
            colonne = int(position[1])
            if not (0 <= ligne < taille_grille and 0 <= colonne < taille_grille):
                raise ValueError
            return (ligne, colonne)
        except ValueError:
            print(f"Position invalide. Entrez une position valide (ligne colonne) entre 0 et {taille_grille-1}.")

def conflit_sur_case(echiquier, ligne, colonne, taille_grille):
    # Vérification de la colonne
    for i in range(ligne):
        if echiquier[i][colonne] == 'Q':
            return True

    # Vérification de la diagonale supérieure gauche
    i, j = ligne - 1, colonne - 1
    while i >= 0 and j >= 0:
        if echiquier[i][j] == 'Q':
            return True
        i -= 1
        j -= 1

    # Vérification de la diagonale supérieure droite
    i, j = ligne - 1, colonne + 1
    while i >= 0 and j < taille_grille:
        if echiquier[i][j] == 'Q':
            return True
        i -= 1
        j += 1

    return False

def afficher_echiquier(echiquier):
    for row in echiquier:
        print(" ".join(str(cell) for cell in row))
    print("\n")

def main():
    taille_grille = demander_taille_grille()
    position_reine = demander_position("Entrez la position de la reine (ligne colonne): ", taille_grille)

    echiquier = [['.' for _ in range(taille_grille)] for _ in range(taille_grille)]

    global count
    count = 0
    tester_toutes_les_possibilites(echiquier, 0, taille_grille, position_reine)
    print("Nombre de solutions trouvées avec une reine en position donnée:", count)

if __name__ == "__main__":
    main()
