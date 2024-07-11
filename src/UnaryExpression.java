//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.List;

/**
 * An abstract class for unary expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression e;

    /**
     * Constructor.
     *
     * @param expression The expression.
     */
    public UnaryExpression(Expression expression) {
        this.e = expression;
    }

    /**
     * @return the expression
     */
    public Expression getExpression() {
        return e;
    }

    @Override
    public abstract Boolean evaluate(java.util.Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public List<String> getVariables() {
        return e.getVariables();
    }

    @Override
    public String toString() {
        return this.getSymbol() + "(" + e.toString() + ")";
    }

    /**
     * @return the symbol of the expression
     */
    public abstract String getSymbol();
}
