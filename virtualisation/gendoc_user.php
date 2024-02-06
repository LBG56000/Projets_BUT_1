#!/usr/bin/php
<?php

$fileContents = file_get_contents('resumeCoursP1.md');

$lignes = explode("\n", $fileContents);

$titres1 = array();
$titres2Dieses = array();
$titres3Dieses = array();
$titres4Dieses = array();
$titres5Dieses = array();
$titres6Dieses = array();
$titres2Tirets = array();
$cote = array();
$autres = array();
$keywords = array();
$tables = array();

$insideBackticks = false;
$currentBackticksContent = '';
$insideTable = false;
$currentTableContent = '';
$nTablo = 0;

// Utiliser foreach pour récupérer les titres
foreach ($lignes as $ligne) {
    $trimmedLine = trim($ligne);

    // Vérifier si la ligne correspond à un titre
    if (preg_match('/^([(#-]+)\s*(.*)/', $ligne, $matches)) {
        $motif = $matches[1];
        $titre = $matches[2];

        // Stocker les titres dans les tableaux appropriés
        if (strlen($titre) > 0) {
            if ($motif == '#') {
                $titres1[] = $titre;
            } elseif ($motif == '##') {
                $titres2Dieses[] = $titre;
            } elseif ($motif == '-') {
                $titres2Tirets[] = $titre;
            } elseif ($motif == '###'){
		$titres3Dieses[] = $titre;
	    } elseif ($motif == '####'){
		$titres4Dieses[] = $titre;
	    } elseif ($motif == '#####'){
	    	$titres5Dieses[] = $titre;
	    } elseif ($motif == '######'){
	  	$titres6Dieses[] = $titre;
	    }
        }
    } elseif (trim($ligne) == '```') {
        // Détection des triples guillemets pour le début ou la fin
        if (!$insideBackticks) {
            $insideBackticks = true;
            $currentBackticksContent = '';
        } else {
            $insideBackticks = false;
            $cote[] = $currentBackticksContent;
            $currentBackticksContent = ''; // Réinitialiser le contenu
        }
    } else {
        // Catégoriser les lignes en fonction des mots-clés
        $lineCategorized = false;

        foreach ($keywords as $keyword) {
            if (strpos($trimmedLine, $keyword) !== false) {
                ${'autres_' . str_replace(' ', '', $keyword)}[] = $trimmedLine;
                $lineCategorized = true;
                break;
            }
        }

        // Si la ligne ne correspond à aucun mot-clé, la catégoriser dans "autres"
        if (!$lineCategorized && !$insideBackticks) {
            $autres[] = $trimmedLine;
        }

        // Si la ligne est à l'intérieur des triples guillemets, stocker dans $currentBackticksContent
        if ($insideBackticks) {
            $currentBackticksContent .= $ligne . "\n";
        }
 	if (preg_match('/^\|/', $trimmedLine)) {
            if (!$insideTable) {
                $insideTable = true;
                $currentTableContent = '';
            }
            $currentTableContent .= $trimmedLine . "\n";
        } elseif ($insideTable) {
            $insideTable = false;
            $tables[] = $currentTableContent;
            $nTablo++;
	}
    }
}

// Variables pour stocker les premiers titres de chaque type
$premierTitre1 = reset($titres1);
$Titre1Dieses2 = (isset($titres2Dieses[0])) ? $titres2Dieses[0] : '';
$Titre2Dieses2 = (isset($titres2Dieses[1])) ? $titres2Dieses[1] : '';
$Titre3Dieses2 = (isset($titres2Dieses[2])) ? $titres2Dieses[2] : '';
$Titre1Dieses3 = (isset($titres3Dieses[0])) ? $titres3Dieses[0] : '';
$Titre2Dieses3 = (isset($titres3Dieses[1])) ? $titres3Dieses[1] : '';
$Titre3Dieses3 = (isset($titres3Dieses[2])) ? $titres3Dieses[2] : '';
$Titre4Dieses3 = (isset($titres3Dieses[3])) ? $titres3Dieses[3] : '';
$Titre5Dieses3 = (isset($titres3Dieses[4])) ? $titres3Dieses[4] : '';
$Titre6Dieses3 = (isset($titres3Dieses[5])) ? $titres3Dieses[5] : '';
$Titre1Dieses4 = (isset($titres4Dieses[0])) ? $titres4Dieses[0] : '';
$Titre2Dieses4 = (isset($titres4Dieses[1])) ? $titres4Dieses[1] : '';
$Titre3Dieses4 = (isset($titres4Dieses[2])) ? $titres4Dieses[2] : '';
$Titre4Dieses4 = (isset($titres4Dieses[3])) ? $titres4Dieses[3] : '';
$Titre1Dieses5 = (isset($titres5Dieses[0])) ? $titres5Dieses[0] : '';
$Titre2Dieses5 = (isset($titres5Dieses[1])) ? $titres5Dieses[1] : '';
$Titre3Dieses5 = (isset($titres5Dieses[2])) ? $titres5Dieses[2] : '';
$Titre4Dieses5 = (isset($titres5Dieses[3])) ? $titres5Dieses[3] : '';
$Titre5Dieses5 = (isset($titres5Dieses[4])) ? $titres5Dieses[4] : '';
$Titre6Dieses5 = (isset($titres5Dieses[5])) ? $titres5Dieses[5] : '';
$Titre7Dieses5 = (isset($titres5Dieses[6])) ? $titres5Dieses[6] : '';
$Titre8Dieses5 = (isset($titres5Dieses[7])) ? $titres5Dieses[7] : '';
$Titre9Dieses5 = (isset($titres5Dieses[8])) ? $titres5Dieses[8] : '';
$Titre10Dieses5 = (isset($titres5Dieses[9])) ? $titres5Dieses[9] : '';
$Titre11Dieses5 = (isset($titres5Dieses[10])) ? $titres5Dieses[10] : '';
$Titre12Dieses5 = (isset($titres5Dieses[11])) ? $titres5Dieses[11] : '';
$Titre13Dieses5 = (isset($titres5Dieses[12])) ? $titres5Dieses[12] : '';
$Titre14Dieses5 = (isset($titres5Dieses[13])) ? $titres5Dieses[13] : '';
$Titre15Dieses5 = (isset($titres5Dieses[14])) ? $titres5Dieses[14] : '';
$Titre16Dieses5 = (isset($titres5Dieses[15])) ? $titres5Dieses[15] : '';
$Titre1Dieses6 = (isset($titres6Dieses[0])) ? $titres6Dieses[0] : '';
$Tirets1 = (isset($titres2Tirets[0])) ? $titres2Tirets[0] : '';
$Tirets2 = (isset($titres2Tirets[1])) ? $titres2Tirets[1] : '';
$Tirets3 = (isset($titres2Tirets[2])) ? $titres2Tirets[2] : '';
$Tirets4 = (isset($titres2Tirets[3])) ? $titres2Tirets[3] : '';
$Tirets5 = (isset($titres2Tirets[4])) ? $titres2Tirets[4] : '';
$Tirets6 = (isset($titres2Tirets[5])) ? $titres2Tirets[5] : '';
$Tirets7 = (isset($titres2Tirets[6])) ? $titres2Tirets[6] : '';
$Tirets8 = (isset($titres2Tirets[7])) ? $titres2Tirets[7] : '';
$Tirets9 = (isset($titres2Tirets[8])) ? $titres2Tirets[8] : '';
$Tirets10 = (isset($titres2Tirets[9])) ? $titres2Tirets[9] : '';
$cote1 = (isset($cote[0])) ? $cote[0] : '';
$cote2 = (isset($cote[1])) ? $cote[1] : '';
$cote3 = (isset($cote[2])) ? $cote[2] : '';
$cote4 = (isset($cote[3])) ? $cote[3] : '';


function convertTable($table) {
    $tableHTML = '<table>';
    $rows = explode("\n", trim($table));

    foreach ($rows as $row) {
        $cols = explode('|', trim($row, '|'));
        $tableHTML .= '<tr>';

        foreach ($cols as $col) {
            $tableHTML .= '<td>' . trim($col) . '</td>';
        }

        $tableHTML .= '</tr>';
    }

    $tableHTML .= '</table>';
    return $tableHTML;
}



?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Doc</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1><?php echo $premierTitre1; ?></h1>
    </header>

    <section>
	<h2><?php echo $Titre1Dieses2; ?></h2>
        <article>
	<h3><?php echo $Titre1Dieses3; ?></h3>
        <ul><li><p><?php echo $Tirets1; ?></p></li>
        <li><p><?php echo $Tirets2; ?></p></li>	</ul>
	</article>
    </section>

    <section>
	<h2><?php echo $Titre2Dieses2; ?></h2>
	<article>
	<p><?php echo $Titre1Dieses5; ?></p>
        <ul><li><p><?php echo $Tirets3; ?></p></li>
        <li><p><?php echo $Tirets4; ?></p></li>
        <li><p><?php echo $Tirets5; ?></p></li>
	</ul>
	<p><?php echo $Titre2Dieses5; ?></p>
	<p><?php echo convertTable($tables[0]); ?></p>
	<p><?php echo $Titre3Dieses5; ?> </p>
	<h3><?php echo $Titre2Dieses3; ?></h3>
	<h4><?php echo $Titre1Dieses4; ?> </h4>
	<p><?php echo $Titre4Dieses5; ?> </p>
	<pre> <?php echo htmlentities($cote1); ?> </pre>
	<p><?php echo $Titre5Dieses5; ?> </p>
	<h4><?php echo $Titre2Dieses4; ?> </h4>
	<p><?php echo $Titre6Dieses5; ?> </p>
	<pre> <?php echo htmlentities($cote2); ?> </pre>
	<p><?php echo $Titre7Dieses5; ?> </p>
	<h4><?php echo $Titre3Dieses4; ?> </h4>
	<p><?php echo $Titre8Dieses5; ?> </p>
	<p><?php echo $Titre9Dieses5; ?> </p>
	<pre> <?php echo htmlentities($cote3); ?> </pre>
	<p><?php echo $Titre10Dieses5; ?> </p>
	<h3><?php echo $Titre3Dieses3; ?> </h3>
        <ul><li><p><?php echo $Tirets6; ?></p></li>
        <li><p><?php echo $Tirets7; ?></p></li>
	</ul>
	</article>
    </section>

    <section>
	<h2><?php echo $Titre3Dieses2; ?> </h2>
	<article>
        <ul><li><p><?php echo $Tirets8; ?></p></li>
        <li><p><?php echo $Tirets9; ?></p></li>
        <li><p><?php echo $Tirets10; ?></p></li>
	</ul>
	<p><?php echo $Titre11Dieses5; ?> </p>
	<p><?php echo convertTable($tables[1]); ?></p>
	<h3><?php echo $Titre4Dieses3; ?> </h3>
	<p><?php echo $Titre12Dieses5; ?> </p>
	<p><?php echo $Titre13Dieses5; ?> </p>
	<h3><?php echo $Titre5Dieses3; ?> </h3>
	<p><?php echo $Titre14Dieses5; ?> </p>
	<p><?php echo $Titre15Dieses5; ?> </p>
	<h3><?php echo $Titre6Dieses3; ?> </h3>
	<p><?php echo $Titre16Dieses5; ?> </p>
	</article>
    </section>

    <footer>
	<h4><?php echo $Titre4Dieses4; ?> </h4>
	<p><?php echo $Titre1Dieses6; ?> </p>
    </footer>
</body>
</html>
