package modelCheckCTL.model.kripke;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.valueOf;

public class State implements Comparable<State>{

    private Integer number;
    private Set labels;
    private Set transitions;

    public State(Integer number) {
        this.transitions = new HashSet();
        this.number = number;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public Boolean hasTransitionTo(State targetState) {
        Integer transitionStateNum = targetState.getNumber();
        for (Object transitionObj : this.transitions) {
            Transition thisTransition = (Transition) transitionObj;
            Integer thisTransitionNum = thisTransition.getNumber();
            if (thisTransitionNum == transitionStateNum) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "s" + valueOf(number);
    }

    public String toStringDetailed() {
        String labelsStr = getLabelsString();
        return "s" + number + " : " + labelsStr;
    }

    private String getLabelsString() {
        String labelsStr = "";
        for (Object labelObj : labels ) {
            Character labelChar = (Character) labelObj;
            labelsStr = labelsStr + labelChar + " ";
        }
        labelsStr = labelsStr.trim();
        labelsStr = labelsStr + ",\n";
        return labelsStr;
    }

    public Boolean hasLabel(Character labelToCheck) {
        Boolean hasLabel = false;
        for (Object labelObj : labels ) {
            Character label = (Character) labelObj;
            if (label == labelToCheck) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(State state) {
        return this.number.compareTo(state.number);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set getLabels() {
        return labels;
    }

    public void setLabels(Set labels) {
        this.labels = labels;
    }

    public Set getTransitions() {
        return transitions;
    }
}
