package modelCheckCTL.model;

import modelCheckCTL.model.ctlFormula.CtlFormula;
import modelCheckCTL.model.kripke.Kripke;
import modelCheckCTL.model.kripke.State;

public class ModelCheckInputs {
    private Kripke kripke;
    private CtlFormula ctlFormula;
    private State stateToCheck;

    public CtlFormula getCtlFormula() {
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

    public void setCtlFormula(CtlFormula ctlFormula) {
        this.ctlFormula = ctlFormula;
    }

    public void setKripke(Kripke kripke) {
        this.kripke = kripke;
    }

}
