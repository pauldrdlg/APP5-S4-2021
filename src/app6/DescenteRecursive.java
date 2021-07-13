package app6;

/** @author Ahmed Khoumsi */

import java.util.ArrayList;
import java.util.List;

import static app6.AnalLex.ANSI_RED;
import static app6.AnalLex.ANSI_RESET;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private List<Terminal> terminalList;
  int readIndex;

  int cptParenthese;



/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive() {
  terminalList = new ArrayList();
  readIndex = 0;
  cptParenthese = 0;
}

public void initTerminalList(String in) {
  Reader r = new Reader(in);
  AnalLex lexical = new AnalLex(r.toString());
  Terminal t = null;
  while(lexical.resteTerminal()){
    t = lexical.prochainTerminal();
    terminalList.add(t);
  }
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
  ElemAST n = E();

  return n;
}


// Methode pour chaque symbole non-terminal de la grammaire retenue
public ElemAST E() {
  ElemAST n1 = T();
  ElemAST nRetour = n1;
  Terminal t = null;
  try
  {
    t = terminalList.get(readIndex);
  }
  catch(Exception e)
  {
    return nRetour;
  }

  if (t.type == types.operateur) {
    if (t.chaine.equals("+") || t.chaine.equals("-")) {
      if (readIndex < terminalList.size() - 1) {
        readIndex++;
      }
      ElemAST n2 = E();
      nRetour = new NoeudAST(t.chaine, n1, n2);
    }
  }
  else if (t.type == types.parentheseFermante) {
    cptParenthese--;
    if (cptParenthese < 0) {
      ErreurSynt("Erreur Syntaxique : parenthèse fermante de trop au caractère " + readIndex);
    }
    cptParenthese++;
  }
  else if (t.type == types.parentheseOuvrante) {
      ErreurSynt("Erreur Syntaxique : il manque un opérateur au caractère " + readIndex);
  }
  else if (terminalList.get(readIndex - 1).type == types.parentheseFermante) {
      ErreurSynt("Erreur Syntaxique : il manque un opérateur au caractère " + readIndex);
  }
  return nRetour;
}

public ElemAST T() {
  ElemAST n1 = F();
  ElemAST nRetour = n1;
  Terminal t = null;
  try
  {
    t = terminalList.get(readIndex);
  }
  catch(Exception e)
  {
    return nRetour;
  }

  if (t.type == types.operateur) {
    if (t.chaine.equals("*") || t.chaine.equals("/")) {
      if (readIndex < terminalList.size() - 1) {
        readIndex++;
      }
      ElemAST n2 = T();
      nRetour = new NoeudAST(t.chaine, n1, n2);
    }
  }
  return nRetour;
}

public ElemAST F() {
  ElemAST n = null;
  Terminal t = terminalList.get(readIndex);

  switch (t.type) {
    case operande:
      n = new FeuilleAST(t.chaine);
      if (readIndex < terminalList.size() - 1) {
        readIndex++;
      }
      break;
    case parentheseOuvrante:
      if (readIndex < terminalList.size() - 1) {
        readIndex++;
      }
      cptParenthese++;
      n = E();
      terminal(types.parentheseFermante);
      cptParenthese--;
      break;
    default:
      ErreurSynt("Erreur Syntaxique: opérande ou paranthèse ouverte attendu au caractère " + readIndex);
      break;

  }
  return n;
}

void terminal(types attendu) {
  Terminal t = null;
  try
  {
    t = terminalList.get(readIndex);
  }
  catch(Exception e)
  {
    ErreurSynt("Erreur Syntaxique : il manque une paranthèse fermante");
  }

  if (t.type == attendu) {
      readIndex++;
  }
  else {
    ErreurSynt("Erreur Syntaxique : " + attendu.toString() + " manquante");
  }
}



/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    System.out.println(ANSI_RED + s + ANSI_RESET);
    System.exit(0);
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";
    String toWritePostfix = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive();
    dr.initTerminalList(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      if (!RacineAST.isEval()) {
        System.out.println(ANSI_RED + "Impossible d'évaluer l'automate parce qu'au moins une des opérandes est une variable." + ANSI_RESET);
      }
      System.out.println(toWriteEval);
      toWritePostfix += "Expression postfix trouve : " + RacineAST.postfix() + "\n";
      System.out.println(toWritePostfix);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval+toWritePostfix); // Ecriture de toWrite
                                                                                  // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

