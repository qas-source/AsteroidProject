package src;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class GameOverManager {
    private GameManager gameManager;
    private HighScoreManager highScoreManager;
    private ScoreManager scoreManager;
    private GraphicsContext graphicsContext;

    public GameOverManager(GameManager gameManager, HighScoreManager highScoreManager, ScoreManager scoreManager, GraphicsContext gc) {
        this.gameManager = gameManager;
        this.highScoreManager = highScoreManager;
        this.scoreManager = scoreManager;
        this.graphicsContext = gc;

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
    public void showGameOverScreen(int score) {
        graphicsContext.clearRect(0, 0, 800, 600); // Adjust to your canvas size
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font("Arial", 20));
        graphicsContext.fillText("Game Over! Your score: " + score, 100, 100);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        gameManager.setCurrentState(GameState.MENU);
                        gameManager.resetGame();
                    }
                },
                3000
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
        gameManager.setCurrentState(GameState.MENU);
        gameManager.resetGame();
    }
}
