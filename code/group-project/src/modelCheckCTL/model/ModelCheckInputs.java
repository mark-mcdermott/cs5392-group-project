package modelCheckCTL.model;

import modelCheckCTL.model.kripke.Kripke;
import modelCheckCTL.model.kripke.State;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ModelCheckInputs {
    private Kripke kripke;
    private String ctlFormula;
    private State stateToCheck;
    private InputStream stream;

    public ModelCheckInputs(Kripke kripke, String ctlFormula, State stateToCheck) throws UnsupportedEncodingException {
        this.kripke = kripke;
        this.ctlFormula = ctlFormula;
        this.stateToCheck = stateToCheck;
        this.stream = new ByteArrayInputStream(ctlFormula.getBytes("UTF-8"));
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

    public InputStream getStream() {
        return stream;
    }

    // after every Parse(), you need to reset the formula stream if you want to Parse() again
    public void resetFormulaStream(String ctlFormula) throws UnsupportedEncodingException {
        this.stream = new ByteArrayInputStream(ctlFormula.getBytes("UTF-8"));
    }

}
