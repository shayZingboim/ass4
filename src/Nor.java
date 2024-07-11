//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * Nor class.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Nor(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getLeft().evaluate(assignment) || getRight().evaluate(assignment));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(getLeft().nandify(), getLeft().nandify()),
                new Nand(getRight().nandify(), getRight().nandify())),
                new Nand(new Nand(getLeft().nandify(), getLeft().nandify()),
                        new Nand(getRight().nandify(), getRight().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(getLeft().norify(), getRight().norify());
    }

    @Override
    public String getSymbol() {
        return "V";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        // if the expressions are equal, return the opposite of one of them
        if (leftSimplified.equals(rightSimplified)) {
            return new Not(leftSimplified);
        }
        // if one of the expressions is T, return F
        if (leftSimplified.toString().equals("T") || rightSimplified.toString().equals("T")) {
            return new Val(false);
        }
        // if the left expressions is F, return the opposite of the right expression
        if (leftSimplified.toString().equals("F")) {
            return new Not(rightSimplified);
        }
        // if the right expressions is F, return the opposite of the left expression
        if (rightSimplified.toString().equals("F")) {
            return new Not(leftSimplified);
        }
        // if the expression can't be simplified return the original expression
        return new Nor(leftSimplified, rightSimplified);
    }
}
