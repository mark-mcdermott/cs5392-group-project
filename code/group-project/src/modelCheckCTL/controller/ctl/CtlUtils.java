package modelCheckCTL.controller.ctl;

import modelCheckCTL.controller.ctl.ctlParser.Token;
import modelCheckCTL.model.formulaObj.FormulaObj;
import modelCheckCTL.model.kripke.State;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static modelCheckCTL.util.Util.contains;

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

        public static Set statesWithLabel(Set states, Token t) {
           Set statesWithLabel = new HashSet();
           String labelStr = t.toString();
           Character label = labelStr.charAt(0);
           for (Object stateObj : states) {
               State state = (State) stateObj;
               // System.out.println(state);
               if (state.hasLabel(label)) {
                   // System.out.println(state);
                   statesWithLabel.add(state);
               }
           }
           return statesWithLabel;
    }

    public static FormulaObj statesWithLabelFormulaObj(Set states, Token t) {
        String labelStr = t.toString();
        Character label = labelStr.charAt(0);
        Set statesWithLabel = statesWithLabel(states,label);
        return new FormulaObj(statesWithLabel);
    }

    public static Set intersection(Set a, Set b) {
        Set intersection = new HashSet();
        for (Object stateObj : a) {
            State state = (State) stateObj;
            if (contains(b,state)) {
                intersection.add(state);
            }
        }
        return intersection;
    }

    public static Set union(Set a, Set b) {
        Set union = new HashSet();
        for (Object stateObj : a) {
            union.add((State) stateObj);
        }
        for (Object stateObj : b) {
            State state = (State) stateObj;
            if (!contains(union,state)) {
                union.add(state);
            }
        }
        return union;
    }

}
