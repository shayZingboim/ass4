//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.List;

/**
 * An abstract class for unary expressions.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public Boolean evaluate() throws Exception {
        return this.evaluate(null);
    }

    @Override
    public abstract Boolean evaluate(java.util.Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract List<String> getVariables();

    @Override
    public abstract String toString();

    /**
     * @return the symbol of the expression
     */
    public abstract String getSymbol();

}
