package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Gameover Manager
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class GameOverManager {
    private GameManager gameManager;
    private HighScoreManager highScoreManager;
    private ScoreManager scoreManager;
    private GraphicsContext graphicsContext;
    private boolean isGameOver = false;
    private Runnable resetAction;

    public GameOverManager(GameManager gameManager, HighScoreManager highScoreManager, ScoreManager scoreManager, GraphicsContext gc) {
        this.gameManager = gameManager;
        this.highScoreManager = HighScoreManager.getInstance();
        this.scoreManager = scoreManager;
        this.graphicsContext = gc;
        this.resetAction = resetAction;
    }
    /**
     * Handles the end of the game, displaying the game over screen and setting up a key listener for restart.
     */

    public void endGame(int score) {
        isGameOver = true;
        System.out.println("GAME OVER");
        graphicsContext.clearRect(0, 0, 1000, 1000);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 1000, 1000);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Arial", 20));
        graphicsContext.fillText("Game Over! Your score: " + score, 100, 100);
        graphicsContext.fillText("Press F to restart", 400, 400);
        graphicsContext.getCanvas().setFocusTraversable(true);
        graphicsContext.getCanvas().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F) {
                resetGame();
            }
        });
    }

    private void resetGame() {
        isGameOver = false;
        gameManager.setCurrentState(GameState.MENU);
        gameManager.resetGame();
        if (resetAction != null) {
            resetAction.run();
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
