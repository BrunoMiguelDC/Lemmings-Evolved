package lemmingsEvolved;

/**
 * Class that manages the game trials and the sequences required by the game trial.
 */
public class LemmingsEvolved {

    /*
     * Array with the game trials.
     */
    private GameTrial[] gameTrials;

    /*
     * Array with the Lemmings of the current first sequence.
     */
    private Lemming[] currentSeq1;

    /*
     * Array with the Lemmings of the current second sequence.
     */
    private Lemming[] currentSeq2;


    /**
     * Constructor of the LemmingsEvolved class that initializes all private variables.
     * @param gameTrials - number of game trials.
     */
    public LemmingsEvolved(int gameTrials){
        this.gameTrials = new GameTrial[gameTrials];
        currentSeq1 = null;
        currentSeq2 = null;
    }


    /**
     * Creates a Lemming array and attributes it to the currentSeqN with N == seqNum.
     * @param seqNum - the number of the sequence.
     * @param seqLength - the length of the sequence.
     */
    public void createSequence(short seqNum, int seqLength){
        Lemming[] seq = new Lemming[seqLength];
        if(seqNum == 1)
            currentSeq1 = seq;
        else
            currentSeq2 = seq;
    }

    /**
     * Adds a Lemming to the currentSeqN with N == seqNum.
     * @param seqNum - the number of the sequence.
     * @param lemmingPos - the position of the Lemming in the sequence.
     * @param tribe - the tribe of the Lemming.
     * @param powerValue - the power value of the Lemming.
     */
    public void addLemmingToSeq(short seqNum, int lemmingPos,char tribe, long powerValue){
        Lemming lemming = new Lemming(tribe, powerValue);
        if(seqNum == 1)
            currentSeq1[lemmingPos] = lemming;
        else
            currentSeq2[lemmingPos] = lemming;
    }

    /**
     * Creates a game trial with the current Lemming sequences.
     * @param trialNum - the number of the trial.
     */
    public void createGameTrial(int trialNum){
        gameTrials[trialNum] = new GameTrial(currentSeq1, currentSeq2);
    }

    /**
     * Returns the maximum possible points of the game trial with the number == trialNum.
     * @param trialNum - the number of the trial.
     * @return - the maximum possible points.
     */
    public long maxPossiblePointsFor(int trialNum){
      return gameTrials[trialNum].getMaxPossiblePoints();
    }

    /**
     * Return the minimum number of pairs needed to get the maximum possible points of the game trial with the number == trialNum.
     * @param trialNum - the number of the trial.
     * @return - minimum number of pairs.
     */
    public int minNumOfPairNeededFor(int trialNum){
        return  gameTrials[trialNum].getMinNumberOfPairs();
    }

}
