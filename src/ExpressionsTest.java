//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.HashMap;
import java.util.Map;

/**
 * ExpressionsTest class.
 */
public class ExpressionsTest {
    /**
     * Main method.
     *
     * @param args Arguments.
     * @throws Exception If an error occurs.
     */
    public static void main(String[] args) throws Exception {
        // 1. Create an expression with at least four variables.
        Expression expression = new Xnor(new Nor(new And(new Val(true), new Var("z")),
                new Var("x")),
                new Not(new Nand(new Var("y"),
                        new Var("z"))));

        // 2. Print the expression.
        System.out.println(expression);

        // 3. Print the value of the expression with an assignment to every variable.
        Map<String, Boolean> assignment = new HashMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        System.out.println(expression.evaluate(assignment));

        // 4. Print the Nandified version of the expression.
        System.out.println(expression.nandify());

        // 5. Print the Norified version of the expression.
        System.out.println(expression.norify());

        // 6. Print the simplified version of the expression.
        System.out.println(expression.simplify());
    }
}