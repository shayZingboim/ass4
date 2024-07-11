//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * Xor class.
 * Represents a logical XOR expression.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Xor(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) ^ getRight().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getLeft().nandify(), new Nand(getLeft().nandify(), getRight().nandify())),
                new Nand(getRight().nandify(), new Nand(getLeft().nandify(), getRight().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(new Nor(getLeft().norify(), getLeft().norify()),
                new Nor(getRight().norify(), getRight().norify())),
                new Nor(getLeft().norify(), getRight().norify()));
    }

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        // if the expressions are equal return false
        if (leftSimplified.equals(rightSimplified)) {
            return new Val(false);
        }
        // if the right expressions is true return the other opposite
        if (rightSimplified.toString().equals("T")) {
            return new Not(leftSimplified);
        }
        // if the left expressions is true return the other opposite
        if (leftSimplified.toString().equals("T")) {
            return new Not(rightSimplified);
        }
        // if the left expressions is false return the right expression
        if (leftSimplified.toString().equals("F")) {
            return rightSimplified;
        }
        // if the right expressions is false return the left expression
        if (rightSimplified.toString().equals("F")) {
            return leftSimplified;
        }
        // if the expression can't be simplified return the original expression
        return new Xor(leftSimplified, rightSimplified);
    }
}
