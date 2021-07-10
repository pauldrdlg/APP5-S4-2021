package app6;

/** @author Ahmed Khoumsi */

/**
 * Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

    // Attributs
    String chaine;
    int Etat;
    int index;
    int chaineLength;

    // Couleurs pour les erreurs en console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


	public boolean estUnChiffre(char caractere)
    {
        if (caractere == '0' || caractere == '1' || caractere == '2' || caractere == '3' || caractere == '4' ||
            caractere == '5' || caractere == '6' || caractere == '7' || caractere == '8' || caractere == '9')
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean estUneMajuscule(char caractere)
    {
        if (caractere == 'A' || caractere == 'B' || caractere == 'C' || caractere == 'D' || caractere == 'E' || caractere == 'F' ||
            caractere == 'G' || caractere == 'H' || caractere == 'I' || caractere == 'J' || caractere == 'K' || caractere == 'L' ||
            caractere == 'M' || caractere == 'N' || caractere == 'O' || caractere == 'P' || caractere == 'Q' || caractere == 'R' ||
            caractere == 'S' || caractere == 'T' || caractere == 'U' || caractere == 'V' || caractere == 'W' || caractere == 'X' ||
            caractere == 'Y' || caractere == 'Z')
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean estUneMinuscule(char caractere)
    {
        if (caractere == 'a' || caractere == 'b' || caractere == 'c' || caractere == 'd' || caractere == 'e' || caractere == 'f' ||
            caractere == 'g' || caractere == 'h' || caractere == 'i' || caractere == 'j' || caractere == 'k' || caractere == 'l' ||
            caractere == 'm' || caractere == 'n' || caractere == 'o' || caractere == 'p' || caractere == 'q' || caractere == 'r' ||
            caractere == 's' || caractere == 't' || caractere == 'u' || caractere == 'v' || caractere == 'w' || caractere == 'x' ||
            caractere == 'y' || caractere == 'z')
        {
            return true;
        }
        else
        {
            return false;
        }
    }


/**
 * Constructeur pour l'initialisation d'attribut(s)
 */
    public AnalLex(String chaine) {
        this.chaine = chaine;
        this.Etat = 0;
        this.index = 0;
        this.chaineLength = this.chaine.length();
    }


/**
 * resteTerminal() retourne :
 * false  si tous les terminaux de l'expression arithmetique ont ete retournes
 * true s'il reste encore au moins un terminal qui n'a pas ete retourne
 */
    public boolean resteTerminal( ) {
        return this.index < this.chaineLength;
    }

  
/**
 * prochainTerminal() retourne le prochain terminal
 * Cette methode est une implementation d'un AEF
 */  
    public Terminal prochainTerminal( ) {
        String tempChaine = "";
        this.Etat = 0;

        while(resteTerminal())
        {
            char caractere = this.chaine.charAt(index);
            index++;

            switch(this.Etat)
            {
                case 0:
                    if(caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/')
                    {
                        return new Terminal(String.valueOf(caractere), types.operateur);
                    }
                    else if (caractere == '(')
                    {
                        return new Terminal(String.valueOf(caractere), types.parentheseOuvrante);
                    }
                    else if (caractere == ')')
                    {
                        return new Terminal(String.valueOf(caractere), types.parentheseFermante);
                    }
                    else if (estUnChiffre(caractere))
                    {
                        this.Etat = 1;
                        tempChaine += caractere;
                    }
                    else if (estUneMajuscule(caractere))
                    {
                        this.Etat = 2;
                        tempChaine += caractere;
                    } else {
                        ErreurLex("ERREUR: '" + caractere + "'\n" +
                                              "1) Caractere inexistant\n" +
                                              "2) Pas de majuscule ou de chiffre au debut d'une operande");
                        return null;
                    }
                    break;
                case 1:
                    if (estUnChiffre(caractere))
                    {
                        tempChaine += caractere;
                    } else {
                        index--;
                        return new Terminal(tempChaine, types.operande);
                    }
                    break;
                case 2:
                    if (estUneMajuscule(caractere) || estUneMinuscule(caractere))
                    {
                        tempChaine += caractere;
                    }
                    else if (caractere == '_')
                    {
                        this.Etat = 3;
                        tempChaine += caractere;

                        if(this.index == this.chaineLength)
                        {
                            ErreurLex("ERREUR: '" + caractere + "'\n" +
                                                  "1) Underscore a la fin de l'operande qui est tout a droite de L'ER");
                            return null;
                        }
                    }
                    else
                    {
                        index--;
                        return new Terminal(tempChaine, types.operande);
                    }
                    break;
                case 3:
                    if (estUneMajuscule(caractere) || estUneMinuscule(caractere))
                    {
                        this.Etat = 2;
                        tempChaine += caractere;
                    }
                    else
                    {
                        ErreurLex("ERREUR: '" + caractere + "'\n" +
                                              "1) Caractere inexistant\n" +
                                              "2) Double Underscore l'un a la suite de l'autre\n" +
                                              "3) Underscore a la fin d'une operande");
                        return null;
                    }
                    break;
            }
        }

        return new Terminal(tempChaine.toString(), types.operande);
    }


/**
 * ErreurLex() envoie un message d'erreur lexicale
 */ 
    public void ErreurLex(String messageErreur) {
        System.out.println(ANSI_RED + messageErreur + ANSI_RESET);
        System.exit(0);
    }


  //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) {
        String toWrite = "";
        System.out.println("Debut d'analyse lexicale");
        if (args.length == 0){
            args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
        }
        Reader r = new Reader(args[0]);

        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        // Execution de l'analyseur lexical
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
        }				   //    d'analyse lexicale
        System.out.println(toWrite); 	// Ecriture de toWrite sur la console
        Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}