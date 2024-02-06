<?php
    // cherche dans le fichier les infos importantes a l aide de regex 
    function generateDoc($nomFichier) {
        $source = file_get_contents($nomFichier);
        $pattern = '/\/\*\*(.*?)\*\//s';
        // commentaire du programme
        $commentaireProg ='';
        if (preg_match($pattern, $source, $matches)) {
            $commentaire = trim($matches[0]);
            $commentaireFormate = str_replace('*', '', $commentaire);
            $commentaireFormate = str_replace('/', '', $commentaireFormate );
            $commentaireProg = $commentaireFormate;
        }
        
        preg_match_all('/#include\s+<([^>]+)>/', $source, $matches);
        $includes = isset($matches[1]) ? $matches[1] : [];

        // trouve les includes dans le fichier source.
        $listeIncludes = '<ul>';
        foreach ($includes as $include) {
            $listeIncludes .= '<li>' . htmlentities($include) . '</li>';
        }
        
        $listeIncludes .= '</ul>';
        
        // trouves les typedefs de structure
        // isset var != null

        preg_match_all('/\/\*\*(.*?)\*\/\s*typedef\s+struct/', $source, $matches);
        $commentaires = isset($matches[1]) ? $matches[1] : [];

        preg_match_all('/typedef\s+struct\s*{([^}]+)\}\s*([^;\/]+)/', $source, $matches);
        $variables = isset($matches[1]) ? $matches[1] : [];

        preg_match_all('/typedef\s+struct\s*{[^}]+}\s*([^;]+)/', $source, $matches);
        $noms = isset($matches[1]) ? $matches[1] : [];

        $tableauStruct = '<table border="1">
                        <tr>
                            <th>Nom</th>
                            <th>Commentaire</th>
                            <th>Variable</th>
                        </tr>';

        $nombreStructures = count($noms);
        
        // htmlentities converti les é en caractere html :)
        for ($i = 0; $i < $nombreStructures; $i++) {
            $nom = htmlentities($noms[$i]);
            $commentaire = isset($commentaires[$i]) ? htmlentities($commentaires[$i]) : '';
            $variable = htmlentities(preg_replace('/\/\*\*.*?\*\//', '', $variables[$i]));

            $tableauStruct .= '<tr>
                            <td>' . $nom . '</td>
                            <td>' . htmlentities($commentaire) . '</td>
                            <td>' . $variable . '</td>
                        </tr>';
        }

        $tableauStruct .= '</table>';

        preg_match_all('/typedef\s+((?!struct)[^\s;]+)\s+([^;\/]+)\s*;.*?\/\*\*(.*?)\*\//s', $source, $matches);
        $commentaires = isset($matches[3]) ? $matches[3] : [];
        $types = isset($matches[1]) ? $matches[1] : [];
        $noms = isset($matches[2]) ? $matches[2] : [];

        $tableauTypedefs = '<table border="1">
                        <tr>
                            <th>Nom</th>
                            <th>Type</th>
                            <th>Commentaire</th>
                        </tr>';

        $nombreTypedefs = count($noms);

        for ($i = 0; $i < $nombreTypedefs; $i++) {
            $nom = $noms[$i];
            $type = $types[$i];
            $commentaire = isset($commentaires[$i]) ? $commentaires[$i] : '';

            $tableauTypedefs .= '<tr>
                            <td>' . $nom . '</td>
                            <td>' . $type . '</td>
                            <td>' . htmlentities($commentaire) . '</td>
                        </tr>';
        }

        $tableauTypedefs .= '</table>';


        preg_match_all('/#define\s+([^\s]+)\s+([^\/]+)/', $source, $matchesDefines);
        preg_match_all('/const\s+([^\s]+)\s+([^=]+)/', $source, $matchesConstants);

        $defines = isset($matchesDefines[1]) ? $matchesDefines[1] : [];
        $values = isset($matchesDefines[2]) ? $matchesDefines[2] : [];
        $constants = isset($matchesConstants[2]) ? $matchesConstants[2] : [];
        $types = isset($matchesConstants[1]) ? $matchesConstants[1] : []; 

        $tableauConst = '<table border="1">
                        <tr>
                            <th>Nom</th>
                            <th>Type</th>
                            <th>Valeur</th>
                            <th>Constante</th>
                        </tr>';

        $nombreConstantes = count($defines);
        for ($i = 0; $i < $nombreConstantes; $i++) {
            $nom = $defines[$i];
            $type = isset($types[$i]) ? $types[$i] : '';
            $valeur = isset($values[$i]) ? $values[$i] : '';

            $tableauConst .= '<tr>
                                <td>' . $nom . '</td>
                                <td>'  . '</td>
                                <td>' . $valeur . '</td>
                                <td>' . "define" . '</td>
                            </tr>';
        }

        $nombreConstantes = count($constants);
        preg_match_all('/const\s+\w+\s+(\w+)\s*=\s*([-+]?.+?)(?=\s*;)/', $source, $matchesConstants);
        $values = isset($matchesConstants[2]) ?  $matchesConstants[2] : [];
        for ($i = 0; $i < $nombreConstantes; $i++) {
            $nom = $constants[$i];
            $type = isset($types[$i]) ? $types[$i] : ''; 
            $valeur = isset($values[$i]) ? $values[$i] : '';

            $tableauConst .= '<tr>
                                <td>' . $nom . '</td>
                                <td>' . $type . '</td>
                                <td>' . $valeur . '</td>
                                <td>' . "const" . '</td>
                            </tr>';
        }

        $tableauConst .= '</table>';

        preg_match_all('/\/\*\*\s*\n(.*?)\*\/\s*([^\n]*)/s', $source, $matches);

        $comments = $matches[1] ?? [];
        $code = $matches[2];
        
        $tableauFonctions = "<table border='1'>
                        <tr>
                            <th>Function</th>
                            <th>Commentaire</th>
                            <th>".htmlentities("Paramètres")."</th>
                            <th>Retourne</th>
                        </tr>";
        // pour une reson inconnue la premiere ligne est tjr vide
        $skipFirstComment = true;
        
        foreach ($comments as $index => $comment) {
            if ($skipFirstComment) {
                $skipFirstComment = false;
                continue;
            }
            // scan des commentaires des fontions
            preg_match('/[\\@]?brief\s*(.*?)\n/', $comment, $briefMatch);
            $brief = isset($briefMatch[1]) ? trim($briefMatch[1]) : '';
        
            preg_match_all('/[\\@]?param\s*([^\n]*)\n/', $comment, $paramMatches);
            $params = isset($paramMatches[1]) ? array_map('trim', $paramMatches[1]) : [];
        
            preg_match('/[\\@]?return\s*([^\n]*)\n/', $comment, $returnMatch);
            $return = isset($returnMatch[1]) ? trim($returnMatch[1]) : '';
        
            preg_match('/\b(\w+)\s+(\w+)\s*\([^)]*\)\s*/', $code[$index], $functionMatch);
            $returnType = isset($functionMatch[1]) ? $functionMatch[1] : '';
            $functionName = isset($functionMatch[2]) ? $functionMatch[2] : '';

            $tableauFonctions .= "<tr>
                            <td>$returnType $functionName</td>
                            <td>".htmlentities($brief)."</td>
                            <td>" . htmlentities(implode(', ', $params)) . "</td>
                            <td>".htmlentities($return)."</td>
                        </tr>";
        }
        
        $tableauFonctions .= "</table>";

        return [
            'commentaireProg' => $commentaireProg,
            'listeIncludes' => $listeIncludes,
            'tableau' => $tableauStruct,
            'tableauTypedefs' => $tableauTypedefs,
            'tableauFonctions' => $tableauFonctions,
            'tableauConst' => $tableauConst,
        ];
    }
    // genere le code html pour un programme
    function generateHTML($fichier, $commentaireProg, $listeIncludes, $tableauStruct, $tableauTypedefs, $tableauConst, $tableauFonctions) {
        $html = '';
    
        $html .= '<section>';
        $html .= '<h2>Programme : ' . $fichier. '</h2>';
        $html .= '<p>' . $commentaireProg . '</p>';
        $html .= '</section>';
    
        $html .= '<section>';
        $html .= '<h2>Imports</h2>';
        $html .= strpos($listeIncludes, "li") !== false ? $listeIncludes : "Il n'y en a pas";
        $html .= '</section>';
    
        $html .= '<section>';
        $html .= '<h2>Typedefs</h2>';
        $html .= '<h3>Structures</h3>';
        $html .= '<p>' . (strpos($tableauStruct, "td") == true ? $tableauStruct : "Il n'y en a pas.") . '</p>';
        $html .= '<h3>Autres</h3>';
        $html .= '<p>' . (strpos($tableauTypedefs, "td") == true ? $tableauTypedefs : "Il n'y en a pas.") . '</p>';
        $html .= '</section>';
    
        $html .= '<section>';
        $html .= '<h2>Constantes</h2>';
        $html .= strpos($tableauConst, "td") == true ? $tableauConst : "Il n'y en a pas.";
        $html .= '</section>';
    
        $html .= '<section>';
        $html .= '<h2>Fonctions</h2>';
        $html .= !empty($tableauFonctions) ? $tableauFonctions : "Il n'y en a pas";
        $html .= '</section>';
    
        return $html;
    }
    // lis le fichier de config
    function lireConfig() {
        
        $config = "config";
        if (!file_exists($config)) {
            return "Fichier de configuration non trouvé";
        }
        $configContent = file_get_contents($config);
        $configLines = explode("\n", $configContent);
        
        $client = '';
        $produit = '';
        $version = '';
        foreach ($configLines as $line) {
            if (empty($line)) {
                continue;
            }
            // separe la CLIENT et Les Génies du Ponant
            list($key, $value) = explode('=', $line, 2);

            $key = trim($key);
            $value = trim($value);
            // recupere les bonnes clés
            switch ($key) {
                case 'CLIENT':
                    $client = $value;
                    break;
                case 'PRODUIT':
                    $produit = $value;
                    break;
                case 'VERSION':
                    $version = $value;
                    break;
                default:
                    break;
            }
        }
        $client = htmlentities($client);
        $produit = htmlentities($produit);
        $version = htmlentities($version);

        return "$client, $produit (v$version)";
    }
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Documentation technique des programmes</title>
</head>
<body>

    <h1>Documentation technique des programmes</h1>
    <p><?php echo lireConfig() ?></p>
    <?php 
    $dossierCourant = __DIR__;
    $fichiers = scandir($dossierCourant);
    $fichiersC = [];
    $firstTime = false;
    foreach ($fichiers as $fichier) {
         if (pathinfo($fichier, PATHINFO_EXTENSION) === 'c') {
            $resultats = generateDoc($fichier);
            if ($resultats !== false) {
                $commentaireProg = $resultats['commentaireProg'];
                $listeIncludes = $resultats['listeIncludes'];
                $tableau = $resultats['tableau'];
                $tableauTypedefs = $resultats['tableauTypedefs'];
                $tableauFonctions = $resultats['tableauFonctions'];
                $tableauConst = $resultats['tableauConst'];
                if ($firstTime == false) {
                    $firstTime = true;
                }else {
                    echo "<hr>"; 
                }
                $htmlSections = generateHTML($fichier, $commentaireProg, $listeIncludes, $tableau, $tableauTypedefs, $tableauConst, $tableauFonctions);
                echo $htmlSections; 
            } else {
                echo 'Le fichier specifie n est pas trouve.';
            }
        }
    }
    ?>
    <style>
        body {
            font-family: 'Arial', sans-serif;
        }
        hr {
            border: 0; 
            height: 2px; 
            background-color: #000;
            margin: 20px 0; 
            font-weight: bold;
        }
        h1 {
            color: green;
        }
        td:empty{
            background-color: gray;
        }
    </style>

</body>
</html>