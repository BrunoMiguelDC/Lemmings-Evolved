import lemmingsEvolved.LemmingsEvolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int gameTrials = Integer.parseInt(in.readLine());
        LemmingsEvolved lemmingsEvolved = new LemmingsEvolved(gameTrials);

        // Reading game trials
        for(int trialNum = 0; trialNum < gameTrials; trialNum++){
            for(short seqNum = 1; seqNum <= 2; seqNum++) {
                int seqLength = Integer.parseInt(in.readLine());
                lemmingsEvolved.createSequence(seqNum, seqLength);

                // Reading sequence
                for (int lemmingPos = 0; lemmingPos < seqLength; lemmingPos++){
                    String[] lemmingInfo = in.readLine().split(" ");
                    char tribe = lemmingInfo[0].charAt(0);
                    long powerValue = Long.parseLong(lemmingInfo[1]);
                    lemmingsEvolved.addLemmingToSeq(seqNum, lemmingPos, tribe, powerValue);

                }
            }
            lemmingsEvolved.createGameTrial(trialNum);
        }

        // Printing the results
        for(int trialNum = 0; trialNum < gameTrials; trialNum++){
            System.out.printf("%d %d\n", lemmingsEvolved.maxPossiblePointsFor(trialNum), lemmingsEvolved.minNumOfPairNeededFor(trialNum));
        }

        in.close();
    }

}
