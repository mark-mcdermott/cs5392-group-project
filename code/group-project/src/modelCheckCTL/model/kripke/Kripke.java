package modelCheckCTL.model.kripke;

import java.util.Set;

import static java.lang.String.valueOf;
import static modelCheckCTL.util.Util.*;

public class Kripke {
    private Set states;
    private Set transitions;

    public Set getStates() {
        return states;
    }

    public void setStates(Set states) {
        this.states = states;
    }

    public Set getTransitions() {
        return transitions;
    }

    public void setTransitions(Set transitions) {
        this.transitions = transitions;
    }

    public String toString() {
        String output = "";
        output = output + getStatesStr(states) + '\n';
        output = output + getTransitionsStr(transitions);
        output = output + getLabelsStr(states);
        return output;
    }

}
