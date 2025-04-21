package lemmingsEvolved;

/**
 * Class that represents a Lemming, containing its tribe as well as its power value.
 */
public class  Lemming {

    /*
     * The tribe of the Lemming.
     */
    private char tribe;

    /*
     * The power value of the Lemming.
     */
    private long powerValue;


    /**
     * Constructor of the Lemming class.
     * @param tribe - the tribe of the Lemming.
     * @param powerValue - the power Value of the Lemming.
     */
    public Lemming(char tribe, long powerValue){
        this.tribe = tribe;
        this.powerValue = powerValue;
    }


    /**
     * Returns the power value of the Lemming.
     * @return the power value.
     */
    public long getPowerValue() {
        return powerValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lemming lemming = (Lemming) o;
        return tribe == lemming.tribe;
    }

}
