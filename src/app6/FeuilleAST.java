package app6; /** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  private String value;
  private boolean eval;


/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(String value) {  // avec arguments
    this.value = value;
    this.eval = true;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
      try {
          return Integer.valueOf(value);
      }
      catch (Exception e) {
          this.eval = false;
          return 0;
      }
  }

  public  String postfix() {
      return value;
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return value;
  }

    public boolean isEval() {
        return eval;
    }

    public void setEval(boolean eval) {
        this.eval = eval;
    }
}
