package modelCheckCTL.model;

import modelCheckCTL.model.kripke.Kripke;

public class Model {

    private String kripkeFile;
    private String ctlFormula;
    private String stateToCheck;
    private Kripke kripke;

    public String getKripkeFile() {
        return kripkeFile;
    }

    public void setKripkeFile(String kripkeFile) {
        this.kripkeFile = kripkeFile;
    }

    public String getCtlFormula() {
        return ctlFormula;
    }

    public void setCtlFormula(String ctlFormula) {
        this.ctlFormula = ctlFormula;
    }

    public String getStateToCheck() {
        return stateToCheck;
    }

    public void setStateToCheck(String stateToCheck) {
        this.stateToCheck = stateToCheck;
    }

    public Kripke getKripke() {
        return kripke;
    }

    public void setKripke(Kripke kripke) {
        this.kripke = kripke;
    }

}
