package app6; /** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  private String content;
  private ElemAST leftChild;
  private ElemAST rightChild;
  private boolean eval;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(String content, ElemAST leftChild, ElemAST rightChild) { // avec arguments
    this.content = content;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    this.eval = true;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
      int retour = 0;

      int left = leftChild.EvalAST();
      int right = rightChild.EvalAST();

      if (!leftChild.isEval() || !rightChild.isEval()) {
          this.eval = false;
          return 0;
      }

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
    return leftChild.postfix() + " " + rightChild.postfix() + " " + content;
  }

    @Override
    public boolean isEval() {
        return eval;
    }


    /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return "(" + leftChild.LectAST() + content + rightChild.LectAST() + ")";
  }

}


