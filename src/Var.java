//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class representing a variable.
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructor.
     *
     * @param name The name of the variable.
     */
    public Var(String name) {
        this.name = name;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        // If the assignment is null, throw an exception.
        if (assignment == null) {
            this.evaluate();
            // If the variable is in the assignment, return its value.
        } else if (assignment.containsKey(name)) {
            return assignment.get(name);
        }
        // If the variable is not in the assignment, throw an exception.
        throw new Exception("Variable not found in assignment");
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Variable not found in assignment");
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.add(name);
        return vars;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(name)) {
            return expression;
        }
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
