package app6;

/** @author Ahmed Khoumsi */

enum types
{
    operateur,
    operande,
    parentheseOuvrante,
    parentheseFermante
}

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
    String chaine;
    types type;


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
    public Terminal(String caractere, types type) {   // arguments possibles
        this.chaine = caractere;
        this.type =type;
    }

    public String toString() {
        return type.toString() + " : " + chaine;
    }
}
