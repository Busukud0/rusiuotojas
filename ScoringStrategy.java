public interface ScoringStrategy {
    double calculateScore(double currentScore, double multiplier);
    double updateMultiplier(double currentMultiplier);
}