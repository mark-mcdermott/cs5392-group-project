package modelCheckCTL.model.kripke;

import java.util.Set;

import static java.lang.String.valueOf;

public class State implements Comparable<State>{

    private Integer number;
    private Set labels;

    public State(Integer number) {
        this.number = number;
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

}
