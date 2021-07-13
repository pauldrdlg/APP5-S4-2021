package app6;

public class TestBench {


    public static void testLexical(String expression) {
        AnalLex lexical = new AnalLex(expression);
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            System.out.println(t.toString());
        }
    }

    public static void testAST(String expression) {
        DescenteRecursive dr = new DescenteRecursive();
        dr.initTerminalListFromExpression(expression);
        ElemAST RacineAST = dr.AnalSynt();

        System.out.println("Lecture de l'AST : " + RacineAST.EvalAST());
    }

    public static void testSyntaxique(String expression) {
        DescenteRecursive dr = new DescenteRecursive();
        dr.initTerminalListFromExpression(expression);
        ElemAST RacineAST = dr.AnalSynt();

        System.out.println("Évaluation de l'AST : " + RacineAST.EvalAST());
        System.out.println("Expression postfix de l'AST : " + RacineAST.EvalAST());
    }

    public static void main(String[] args) {
        // Test bench pour l'analyseur lexical
        System.out.println("==================================================");
        System.out.println("Banc de test pour l'analyseur lexical");
        System.out.println("==================================================");
        testLexical("1+1");

        // Test bench pour l'AST
        System.out.println("==================================================");
        System.out.println("Banc de test pour AST");
        System.out.println("==================================================");
        testAST("1+1+1");

        // Test bench pour l'analyseur syntaxique
        System.out.println("==================================================");
        System.out.println("Banc de test pour l'analyseur syntaxique");
        System.out.println("==================================================");
        testSyntaxique("1+(2+4*3)");
    }
}
