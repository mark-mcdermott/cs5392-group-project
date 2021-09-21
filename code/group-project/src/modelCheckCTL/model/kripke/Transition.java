package modelCheckCTL.model.kripke;

public class Transition implements Comparable<Transition>{
    private Integer number;
    private State from;
    private State to;

    public Transition(Integer number, State from, State to) {
        this.number = number;
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "t" + number;
    }

    public String toStringDetailed() {
        return "t" + number + " : " + from + " - " + to;
    }

    @Override
    public int compareTo(Transition transition) {
        return this.number.compareTo(transition.number);
    }

}
