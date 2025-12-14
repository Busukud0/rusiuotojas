public class NormalScoring implements ScoringStrategy {
    
    @Override
    public double calculateScore(double currentScore, double multiplier) {
        return currentScore + (1 * multiplier);
    }
    
    @Override
    public double updateMultiplier(double currentMultiplier) {
        return currentMultiplier * 1.05;
    }
}