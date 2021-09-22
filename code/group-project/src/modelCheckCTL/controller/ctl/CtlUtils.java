package modelCheckCTL.controller.ctl;

import modelCheckCTL.model.kripke.State;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class CtlUtils {

    public static Boolean validateCtl(String ctlFormula) throws ParseException, IOException {
            ctlFormula = ctlFormula.trim();
            if (isAtom(ctlFormula) || isUnaryPathTempOp(ctlFormula)) {
                return true;
            } else {
                return false;
            }
        }

    public static Boolean isAtom(String ctlFormula) throws IOException {
        if (ctlFormula.length() == 1) {
            if (ctlFormula.matches("[p-z]")) {
                return true;
            } else {
                throw new IOException("Error in CTL Formula at character: " + ctlFormula + ". Atomic labels must be a letter from p to z.");
            }
        } else {
            return false;
        }
    }

    public static Boolean isUnaryPathTempOp(String ctlFormula) throws IOException, ParseException {
        String firstChar = ctlFormula.substring(0,1);
        String secondChar = ctlFormula.substring(1,2);
        if (firstChar.matches("[EA]")) {
            if (secondChar.matches("[XFG]")) {
                if (ctlFormula.length() > 2) {
                    String phi = ctlFormula.substring(2);
                    validateCtl(phi);
                    return true;
                } else {
                    throw new IOException("Error in CTL formula. Path/temporal operator \"" + ctlFormula + "\" found, but no atomic label (p-z) found after.");
                }
            } else {
                if (!secondChar.matches("\\[")) {
                    throw new IOException("Error in CTL formula. Path operator \"" + firstChar + "\" found, but the following \"" + secondChar + "\" character is not a valid temporal operator (X, F or G)");
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public static Boolean isBinaryPathTempOp(String ctlFormula) throws IOException {
        String firstChar = ctlFormula.substring(0,1);
        String secondChar = ctlFormula.substring(1,2);
        if (firstChar.matches("[EA]")) {
            if (secondChar.matches("\\[")) {

            }
        }
        return false;
    }

    public static Set statesWithLabel(Set states, Character label) {
           Set statesWithLabel = new HashSet();
           for (Object stateObj : states) {
               State state = (State) stateObj;
               // System.out.println(state);
               if (state.hasLabel(label)) {
                   statesWithLabel.add(state);
               }
           }
           return statesWithLabel;
       }


}
