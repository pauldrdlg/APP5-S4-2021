package app6;

import static app6.AnalLex.ANSI_RED;
import static app6.AnalLex.ANSI_RESET;

public class TestBench {


    public static void testLexical(String expression) {
        System.out.println("Test lexical : " + expression);
        AnalLex lexical = new AnalLex(expression);
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            System.out.println(t.toString());
        }
        System.out.println("");
    }

    public static void testAST(String expression) {
        System.out.println("Test AST : " + expression);
        DescenteRecursive dr = new DescenteRecursive();
        dr.initTerminalListFromExpression(expression);
        ElemAST RacineAST = dr.AnalSynt();

        System.out.println("Lecture de l'AST : " + RacineAST.LectAST() + "\n");
    }

    public static void testSyntaxique(String expression) {
        System.out.println("Test syntaxique : " + expression);
        DescenteRecursive dr = new DescenteRecursive();
        dr.initTerminalListFromExpression(expression);
        ElemAST RacineAST = dr.AnalSynt();
        int evalAST = RacineAST.EvalAST();

        if (!RacineAST.isEval()) {
            System.out.println(ANSI_RED + "Impossible d'évaluer l'expression parce qu'au moins une des opérandes est une variable." + ANSI_RESET);
        }

        System.out.println("Évaluation de l'AST : " + evalAST);
        System.out.println("Expression postfix de l'AST : " + RacineAST.postfix() + "\n");
    }

    public static void main(String[] args) {
        // Test bench pour l'analyseur lexical
        System.out.println("==================================================");
        System.out.println("Banc de test pour l'analyseur lexical");
        System.out.println("==================================================");
        // Tests pour la validation
        /*testLexical("(U_x+V_y)*W_z/35");
        testLexical("(U_x+V_y)*W__z/35");*/
        // Tests de notre banc de tests
        /*testLexical("(1+-1)");
        testLexical("1+,83");
        testLexical("1*Abc_d");
        testLexical("345/Abc_def_g");
        testLexical("234+abc_d");
        testLexical("789+Abc_");
        testLexical("839+Abc__d");
        testLexical("Ad_r+Ad55");*/

        // Test bench pour l'AST
        System.out.println("==================================================");
        System.out.println("Banc de test pour AST");
        System.out.println("==================================================");
        // Tests pour la validation
        /*testAST("(U_x-V_y)*W_z/35");
        testAST("(55-47)*14/2");
        testAST("(U_x-)*W_z/35");*/
        // Tests de notre banc de tests
        /*testAST("6(7+8)/9)");
        testAST("67/Aa+Jj*77");
        testAST("45*(Gg-Hh)/333");*/

        // Test bench pour l'analyseur syntaxique
        System.out.println("==================================================");
        System.out.println("Banc de test pour l'analyseur syntaxique");
        System.out.println("==================================================");
        // Tests pour la validation
        /*testSyntaxique("(U_x-V_y)*W_z/35");
        testSyntaxique("(55-47)*14/2");
        testSyntaxique("(U_x-)*W_z/35");*/
        // Tests de notre banc de tests
        /*testSyntaxique("123*(G_a-Ff)/4");
        testSyntaxique("6258G_g");
        testSyntaxique("984/");
        testSyntaxique("1234+8)");
        testSyntaxique("Gh+(3+9");*/

    }
}
