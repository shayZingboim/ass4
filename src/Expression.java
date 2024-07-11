//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.List;
import java.util.Map;

/**
 * Expression interface.
 */
public interface Expression {

    /**
     * @param assignment Map of variables and their values
     * @return Boolean
     * @throws Exception if the expression contains a variable which is not in the assignment
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * @return Boolean
     * @throws Exception if the expression contains a variable which is not in the assignment
     */
    Boolean evaluate() throws Exception;

    /**
     * @return List of the variables in the expression
     */
    List<String> getVariables();

    /**
     * @return String representation of the expression
     */
    String toString();

    /**
     * @param var        String variable
     * @param expression Expression to replace the variable with
     * @return Expression with the variable replaced with the provided expression
     */
    Expression assign(String var, Expression expression);

    /**
     * @return Expression tree resulting from converting all the operations to the logical Nand operation
     */
    Expression nandify();

    /**
     * @return Expression tree resulting from converting all the operations to the logical Nor operation
     */
    Expression norify();

    /**
     * @return Expression tree resulting from simplifying the current expression
     */
    Expression simplify();


}
