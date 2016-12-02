

public class DefinitionHelper {
    public static final int MIXREGLS_MIXREG_KEY = 1;
    public static final int MIXREGLS_MIXOR_KEY = 2;
    public static final int MIXREGMLS_MIXREG_KEY = 3;
    public static final int MIXREGMLS_MIXOR_KEY = 4;

    /**
     *
     * @param randomLocationEffects, (int) the number of random location effects selected by user
     * @param stageTwoBinary, (boolean) true if user selects dichotomous or ordinal outcome for stage two
     * @return sequence Decision, (int) key determining stage 1+stage 2 model UI views
     *
     */
    public static int sequenceDecision(int randomLocationEffects, boolean stageTwoBinary) {
        if(stageTwoBinary){
            if(randomLocationEffects<2){return MIXREGLS_MIXOR_KEY;}
            else{return MIXREGLS_MIXREG_KEY;}
        }
        else{
            if(randomLocationEffects<2){return MIXREGMLS_MIXOR_KEY;}
            else{return MIXREGMLS_MIXREG_KEY;}
        }
    }
}