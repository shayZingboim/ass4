//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * The Or class is a class representing an or operation.
 */
public class Or extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) || getRight().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getLeft().nandify(), getLeft().nandify()),
                new Nand(getRight().nandify(), getRight().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getLeft().norify(), getRight().norify()),
                new Nor(getLeft().norify(), getRight().norify()));
    }

    @Override
    public String getSymbol() {
        return "|";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        //If one of the expressions is T, return T.
        if (leftSimplified.toString().equals("T") || rightSimplified.toString().equals("T")) {
            return new Val(true);
        }
        //If left is F, return right.
        if (leftSimplified.toString().equals("F")) {
            return rightSimplified;
        }
        //If right is F, return left.
        if (rightSimplified.toString().equals("F")) {
            return leftSimplified;
        }
        //If the expressions are equal, return one of them.
        if (leftSimplified.equals(rightSimplified)) {
            return leftSimplified;
        }
        // if the expression can't be simplified return the original expression
        return new Or(leftSimplified, rightSimplified);
    }

}
