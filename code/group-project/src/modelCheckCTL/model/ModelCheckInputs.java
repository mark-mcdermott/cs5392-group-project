package modelCheckCTL.model;

import modelCheckCTL.model.ctlFormula.CtlFormula;
import modelCheckCTL.model.kripke.Kripke;
import modelCheckCTL.model.kripke.State;

public class ModelCheckInputs {
    private Kripke kripke;
    private String ctlFormula;
    private State stateToCheck;

    public ModelCheckInputs(Kripke kripke, String ctlFormula, State stateToCheck) {
        this.kripke = kripke;
        this.ctlFormula = ctlFormula;
        this.stateToCheck = stateToCheck;
    }

    public String getCtlFormula() {
        return ctlFormula;
    }

    public Kripke getKripke() {
        return kripke;
    }

    public State getStateToCheck() {
        return stateToCheck;
    }

    public void setStateToCheck(State stateToCheck) {
        this.stateToCheck = stateToCheck;
    }

    public void setCtlFormula(String ctlFormula) {
        this.ctlFormula = ctlFormula;
    }

    public void setKripke(Kripke kripke) {
        this.kripke = kripke;
    }

}
