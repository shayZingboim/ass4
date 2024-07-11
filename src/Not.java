//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * The Not class is a logical expression representing the negation of a given expression.
 */
public class Not extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression The expression to negate.
     */
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !getExpression().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(getExpression().nandify(), getExpression().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getExpression().norify(), getExpression().norify());
    }

    @Override
    public String getSymbol() {
        return "~";
    }

    @Override
    public Expression simplify() {
        Expression simplified = getExpression().simplify();
        // if the expression is true, return false
        if (simplified.toString().equals("T")) {
            return new Val(false);
        }
        // if the expression is false, return true
        if (simplified.toString().equals("F")) {
            return new Val(true);
        }
        // if the expression can't be simplified return the original expression
        return new Not(simplified);
    }
}
