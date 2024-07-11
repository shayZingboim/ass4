//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.ArrayList;
import java.util.List;

/**
 * BinaryExpression class.
 * Represents a binary expression.
 * A binary expression is an expression that has a left expression, a right expression and a symbol.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructor.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Get the left expression.
     *
     * @return the left expression
     */
    protected Expression getLeft() {
        return left;
    }

    /**
     * Get the right expression.
     *
     * @return the right expression
     */
    protected Expression getRight() {
        return right;
    }

    @Override
    public abstract Boolean evaluate(java.util.Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public List<String> getVariables() {
        // Get the variables from the left and right expressions, recursively.
        List<String> variables = new ArrayList<>();
        variables.addAll(left.getVariables());
        variables.addAll(right.getVariables());
        return variables;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + this.getSymbol() + " " + right.toString() + ")";
    }
}
