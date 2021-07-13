package app6; /** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  private String content;
  private ElemAST leftChild;
  private ElemAST rightChild;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(String content, ElemAST leftChild, ElemAST rightChild) { // avec arguments
    this.content = content;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
      int retour = 0;

     switch (content) {
         case "+":
             retour = leftChild.EvalAST() + rightChild.EvalAST();
             break;
         case "-":
             retour = leftChild.EvalAST() - rightChild.EvalAST();
             break;
         case "*":
             retour = leftChild.EvalAST() * rightChild.EvalAST();
             break;
         case "/":
             retour = leftChild.EvalAST() / rightChild.EvalAST();
             break;
         default:
             return 0;
     }

     return retour;
  }

  public String postfix() {
    return leftChild.postfix() + rightChild.postfix() + content;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return "(" + leftChild.LectAST() + content + rightChild.LectAST() + ")";
  }

}


