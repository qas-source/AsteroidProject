package src;

public class GameOverManager {
    private GameManager gameManager;
    private HighScoreManager highScoreManager;
    private ScoreManager scoreManager;

    public GameOverManager(GameManager gameManager, HighScoreManager highScoreManager, ScoreManager scoreManager) {
        this.gameManager = gameManager;
        this.highScoreManager = highScoreManager;
        this.scoreManager = scoreManager;
    }

    public void endGame() {
        // Save high score
        checkAndUpdateHighScore();

        // Display game over screen
        gameManager.displayGameOverScreen();

        // Schedule return to main menu
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    gameManager.setCurrentState(GameState.MENU);
                    gameManager.resetGame();
                }
            },
            3000 // Delay before returning to the main menu (in milliseconds)
        );
    }

    private void checkAndUpdateHighScore() {
        int currentScore = scoreManager.getScore();
        if (currentScore > highScoreManager.getHighScore()) {
            highScoreManager.setHighScore(currentScore);
        }
    }

    private void resetGame() {
        // Reset game state, remove all objects, etc.
        gameManager.resetGame();
    }
}
