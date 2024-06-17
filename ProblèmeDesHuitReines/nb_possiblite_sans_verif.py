import math

# Taille de la grille
n = 8

# Nombre total de reines à placer (après avoir imposé une reine)
k = n-1

# Calcul du nombre total de possibilités
total_possibilities = math.factorial((n ** 2) - 1) // (math.factorial((n ** 2) - n - 1) * math.factorial(k))

print(f"Nombre total de possibilités pour le problème des 7 reines sur une grille {n}x{n} avec une reine imposée en (0, 0) :", total_possibilities)