package modelCheckCTL.util;

import modelCheckCTL.model.kripke.State;
import modelCheckCTL.model.kripke.Transition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Util {

    public static Boolean isStateName(String str) {
        if (str.length() < 2) {
            return false;
        } else if (str.charAt(0) != 's') {
            return false;
        } else {
            // checks if char(s) after the "s" are numbers
            String charsAfterS = str.substring(1);
            if (!charsAfterS.matches("\\d+")) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static String getStatesStr(Set states) {
        List statesList = new ArrayList<State>(states);
        Collections.sort(statesList);

        String statesStr = "";
        for (Object stateObj : statesList) {
            State state = (State) stateObj;
            statesStr = statesStr + state.toString() + ", ";
        }
        statesStr = statesStr.trim();
        statesStr = statesStr.substring(0,statesStr.length() - 1); // remove trailing comma
        statesStr = statesStr + ";";
        return statesStr;
    }

    public static void printStates(Set states) {
        String statesStr = getStatesStr(states);
        System.out.println(statesStr);
    }

    public static String getTransitionsStr(Set transitions) {
        List transitionsList = new ArrayList<Transition>(transitions);
        Collections.sort(transitionsList);

        String transitionsStr = "";
        for (Object transitionObj : transitionsList) {
            Transition transition = (Transition) transitionObj;
            transitionsStr = transitionsStr + transition.toStringDetailed() + ",\n";
        }
        transitionsStr = transitionsStr.substring(0,transitionsStr.length() - 2); // remove trailing comma and newline
        transitionsStr = transitionsStr + ";\n";
        return transitionsStr;
    }

        public static String getLabelsStr(Set states) {
            List statesList = new ArrayList<State>(states);
            Collections.sort(statesList);

            String labelsStr = "";
            for (Object stateObj : statesList) {
                State state = (State) stateObj;
                labelsStr = labelsStr + state.toStringDetailed();
            }
            labelsStr = labelsStr.substring(0,labelsStr.length() - 2); // remove trailing comma and newline
            labelsStr = labelsStr + ";\n";
            return labelsStr;
        }

    public static void printTransitions(Set transitions) {
        String transitionsStr = getTransitionsStr(transitions);
        System.out.println(transitionsStr);
    }

    public static State getState(Integer stateNum, Set states) {
        for (Object stateObj : states) {
            State state = (State) stateObj;
            if (state.getNumber() == stateNum) {
                return state;
            }
        }
        System.err.println("state not found in states");
        return null;
    }

    public static Boolean contains(Set states, State state) {
        Integer stateNum = state.getNumber();
        for (Object stateObj : states) {
            State thisState = (State) stateObj;
            if (thisState.getNumber() == stateNum) {
                return true;
            }
        }
        return false;
    }

}
