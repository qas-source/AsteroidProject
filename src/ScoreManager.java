package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public void addScore(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }

    public void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText("Score: " + score, 10, 20); // Position the score text on the screen
    }

    public int getScore() {
        return score;
    }
}
