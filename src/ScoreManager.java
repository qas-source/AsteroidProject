package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Manages the current game score
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    /**
     * Increments the score
     * @param points, increment of score
     */
    public void addScore(int points) {
        score += points;
    }

    /**
     * Resets the score
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Renders the score
     * @param gc, graphics context of rendering
     */
    public void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText("Score: " + score, 10, 20); // Position the score text on the screen
    }

    public int getScore() {
        return score;
    }
}
