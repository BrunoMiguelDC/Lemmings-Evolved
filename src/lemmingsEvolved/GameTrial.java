package lemmingsEvolved;

import java.util.Arrays;

/**
 * Class that represents a game trial, computes the maximum points in the trial and the
 * minimum number of pairs needed to achieve those points.
 */
public class GameTrial {

    /*
     * Matrix with the maximum points possible for the sequence of Lemmings of range 0 to seq1.length
     * and the sequence of Lemmings of range 0 to seq2.length.
     */
    private long[][] points;

    /*
     * Matrix with minimum number of pairs needed to achieve the corresponding points in the points s matrix.
     */
    private int[][] minPairs;

    /*
     * First sequence of Lemmings.
     */
    private Lemming[] seq1;

    /*
     * Second sequence of Lemmings.
     */
    private Lemming[] seq2;


    /**
     * Constructor of the GameTrial Class that initializes all private variables.
     * @param seq1 - first sequence of Lemmings.
     * @param seq2 - second sequence of Lemmings.
     */
    public GameTrial(Lemming[] seq1, Lemming[] seq2){
        this.seq1 = seq1;
        this.seq2 = seq2;
        points = new long[seq1.length+1][seq2.length+1];
        minPairs = new int[seq1.length+1][seq2.length+1];
        this.fillPointsAndMinPairs();
    }

    /**
     * Returns the maximum possible points in this game trial.
     * @return - maximum possible points in this game trial.
     */
    public long getMaxPossiblePoints(){
        return  points[seq1.length][seq2.length];
    }

    /**
     * Returns the minimum number of pairs of Lemmings needed to achieve the
     * maximum possible points in this game trial.
     * @return - minimum number of pairs.
     */
    public int getMinNumberOfPairs(){
        return minPairs[seq1.length][seq2.length];
    }

    /**
     * Returns the power value of the Lemming pair, i.e, the sum of their respective
     * power values if they are of the same tribe, 0 otherwise.
     * @param l1 - first Lemming.
     * @param l2 - second Lemming.
     * @return - the sum of their respective power values if they are of the same tribe, 0 otherwise.
     */
    private long lemmingScore(Lemming l1, Lemming l2){
        if(l1.equals(l2)){
            return l1.getPowerValue() + l2.getPowerValue();
        }
        return 0;
    }

    /**
     * Fills the position (i, j) in points and minPairs matrices depending on the diagonal (i-1, j-1), up (i-1, j)
     * and left(i, j-1) values of points and minPairs matrices relative to the position (i, j).
     * @param i - position i.
     * @param j - position j.
     */
    private void fillPosition(int i, int j ){
        long diagonal = points[i-1][j-1] + this.lemmingScore(seq1[i-1], seq2[j-1]);
        long up = points[i-1][j];
        long left = points[i][j-1];

        long point;
        int pair;
        if(diagonal >= up && diagonal >= left){                          // Cases:
            point = diagonal;                                            // diagonal > up && diagonal > left
            if(diagonal == up){                                          // diagonal == up && diagonal > left
                pair = Math.min(minPairs[i-1][j-1]+1,minPairs[i-1][j]);  // diagonal > up && diagonal == left
            } else if(diagonal == left){                                 // diagonal == up && diagonal == left
                pair = Math.min(minPairs[i-1][j-1]+1,minPairs[i][j-1]);
            } else {
                pair = minPairs[i-1][j-1]+1;
            }
        } else if(up >= diagonal && up >= left){                         // Cases:
            point = up;                                                  // up > diagonal && up > left
            if(up == left) {                                             // up > diagonal && up == left
                pair = Math.min(minPairs[i-1][j], minPairs[i][j-1]);
            } else {
                pair = minPairs[i-1][j];
            }
        } else {                                                         // Cases:
            point = left;                                                // left > diagonal && left > up
            pair = minPairs[i][j-1];
        }
        points[i][j] = point;
        minPairs[i][j] = pair;
    }

    /**
     * Fills the points and minPairs matrices.
     */
    private void fillPointsAndMinPairs() {
        // Filling line 0 with 0
        Arrays.fill(points[0], 0);
        Arrays.fill(minPairs[0], 0);

        // Filling column 0 with 0
        for (long[] row : points) {
            row[0] = 0;
        }
        for (int[] row : minPairs) {
            row[0] = 0;
        }

        // Filling the rest
        for (int i = 1; i <= seq1.length; i++) {
            for (int j = 1; j <= seq2.length; j++) {
                this.fillPosition(i, j);
            }
        }
    }

}
