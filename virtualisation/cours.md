# Graphes

Il existe deux type de graphes les graphes orientés et non orientés

## Graphes non-orientés

Soit V et E deux ensembles finis

```
V = {v1 ...vn}
```

```
E ⊂{{vi,vj }|vi,vj ∈V }
```

On appelle graphe <b>non-orienté</b> noté G, couple (V,E).
<span style="color: #FF0000">Les éléments de V sont appelés les sommets du graphes, ceux de E les arrêtes. </span>

### Ordre, taille et voisins

### Ordre et taille
<span style="color: #FF0000">
Le cardinal de V est <b>l'ordre du graphe</b>. <br>
Le cardinal de E est <b> la taille du graohe</b>.
</span>

#### Voisins

Soit v<sub>i</sub>v<sub>j</sub> ∈E  est une arrête d'un graphe non orienté (V,E). <span style = "color:#FF0000"> Les sommets v1 et v2 sont alors dits adjacents (voisins). On notera ```ν(vi)``` l'ensemble des voisins du sommet v<sub>i</sub>. </span>

On dit que les arrêtes v<sub>i</sub>v<sub>j</sub> est <b>incidente</b> aux sommets v<sub>i</sub>et on notera ``` ω(vi)``` l'ensemble des arrêtes de E incidentes à v<sub>i</sub>.

### Degré, formule des poignées de mains 

#### Degré 

Le degré ou la valence d'un sommet v<sub>i</sub> noté *deg(v)* est <span style = "color : #FF0000">le nombre d'arrêtes incidente à ce sommet ou une __boucle compte double__.</span>

On a le résultat suivant lemme ou formule des poignées de mains

```Σ d(v) = 2|E|``` avec ```v∈V``` et |E|le cardinal de E.

## Graphes orientés

Soit V et E deux ensembles finis

```
V = {v1 ...vn}
```
```
E ⊂ V * V = {{vi,vj }|vi,vj ∈V }
```

On appelle __graphe orienté__, noté G le couple (V,E). 
Comme pour les graphes non-orient ́es, les  ́el ́ements de V
sont appel ́es les sommets du graphes (verticies), ceux de E
__les arcs__. Les arcs sont donc des couples (vi,vj ) que l’on
notera simplement v<sub>i</sub>v<sub>j</sub>.

<span style = "color : #FF0000"> Contrairement aux arrêtes, l'ordre des sommets de l'arc est important <b>un arc est un couple </b>.</span>

### Sucesseurs et prédécesseurs

Nous gardons les mêmes définition que dans le cas d'un graphe __non-oreinté__. 

Si a = (v<sub>i</sub>v<sub>j</sub>) est un arc d'un graphe orienté, alors v<sub>i</sub> est __l'origine__ de a v<sub>j</sub> __sa destination__. 
L'arc a est alors dit __sortant__ de v<sub>i</sub> ou __incident__ à v<sub>j</sub> vers l'extérieur. <br> 
<br>
De même *a* est dit __entrant__ dans v<sub>j</sub> ou __incident__ à v<sub>j</sub> vers __l'intérieur__. <br>
<br></br>
Deux arcs sont __adjacents__ s'ils ont au moins une extréminté en commun. 
<br>

On notera ```ω+(vi)``` l'ensemble des arcs sortants de v<sub>i</sub> et ```ω-(vi)``` l'ensemble des arcs entrant de v<sub>i</sub>. 

L'ensemble des arc incidents à un sommet *v* sera noté ``` ω(v)``` et vérifie

```
ω(v) =  ω+(v) ∪ ω−(v)
```
<span style = "color : #FF0000">
Soit *G = (V,E)* un __graphe orienté__ et
soit __v ∈V un sommet de G__. <br>
Un sommet v<sub>i</sub> ∈ V est un __successeur__ de v si __(v,v<sub>i</sub>) ∈ E ((v,vi) ∈ω+(v))__ <br>
De même, v<sub>j</sub> in V est un __ prédécesseur__ de v si __(v<sub>j</sub> ,v) ∈ E ((vj ,v) ∈ω−(v))__.
</span>

### Demi-degrès, formule des poignées de mains

Soit G = (V,E) un graphe orienté. Le
demi-degré entrant d’un sommet v ∈ V , noté deg<sup>+</sup>(v) __est le nombre d’arcs sortant de v (c’est donc le cardinal de ω<sup>+</sup>(v))__. <br>
Le demi-degré sortant de v, noté deg<sup>−</sup>(v), est le
nombre d’arcs entrant dans ce sommet (c’est donc le cardi-
nal de ω<sup>−</sup>(v))

Ainsi: 

<span style = "color : #FF0000">
deg<sup>+</sup>(v) = |ω<sup>+</sup>(v)| et deg<sup>-</sup>(v) = |ω<sup>-</sup>(v)|
</span>

On a alors la formule suivante:

<span style = "color : #FF0000">
Σ<sub>v∈V</sub> d<sup>+</sup>(v) = Σ<sub>v∈V</sub>  d<sup>-</sup>(v)
</span>


## Multigraphes

On peut utiliser les definitions précédentes en introduidant le notion de __multi-graphe__.

Un multigraphe, est un graphe (orienté
ou non) doté d’une ou plusieures arêtes (ou arcs dans le
cas orienté), ou de boucles.








