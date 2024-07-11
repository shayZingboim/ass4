//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class representing a variable.
 */
public class Val implements Expression {
    private final boolean value;

    /**
     * Constructor.
     *
     * @param value The value of the variable.
     */
    public Val(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return value;
    }

    @Override
    public Boolean evaluate() {
        return value;
    }

    @Override
    public String toString() {
        if (value) {
            return "T";
        }
        return "F";
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }
    @Override
    public Expression simplify() {
        return this;
    }
}
