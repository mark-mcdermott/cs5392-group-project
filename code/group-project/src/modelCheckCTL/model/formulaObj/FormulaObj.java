package modelCheckCTL.model.formulaObj;

import modelCheckCTL.model.kripke.Kripke;

import java.util.Set;

public class FormulaObj {
    private String formula;
    private Set states;

    public FormulaObj() { }

    public FormulaObj(String formula) {
        this.formula = formula;
    }

    public FormulaObj(Set states) {
        this.states = states;
    }

    public FormulaObj(String formula, Set states) {
        this.formula = formula;
        this.states = states;
    }

    public String getFormula() {
        return formula;
    }

    public Set getStates() {
        return states;
    }

}
