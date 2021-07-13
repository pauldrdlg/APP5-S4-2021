package app6; /** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  private int value;


/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(int value) {  // avec arguments
    this.value = value;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
      return value;
  }

  public  String postfix() {
      return String.valueOf(value);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return String.valueOf(value);
  }

}
