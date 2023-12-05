package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font; // Import statement for Font
import java.util.ArrayList;

/**
 * Smallest drawn unit
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class MenuManager {

    private GameManager gameManager;
    private GraphicsContext draw;
    private GameState currentState;
    private HighScoreManager highScoreManager;


    public MenuManager(GraphicsContext draw, GameManager gameManager, HighScoreManager highScoreManager) {
        this.draw = draw;
        this.gameManager = gameManager;
        this.currentState = GameState.MENU;
        this.highScoreManager = highScoreManager;
    }

    /**
     * Draws the menu
     */
    public void drawMenu() {
        // Clear the screen and draw the menu
        draw.clearRect(0, 0, 1000, 700); // Assuming canvas size, adjust as necessary
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, 1000, 700);

        draw.setFill(Color.WHITE);
        draw.setFont(new Font(24)); // Set the font for the text
        draw.fillText("Press S to Start", 100, 100);
        draw.setFont(new Font(18)); // Change the font size for other options

        if (gameManager.getDifficulty() == Difficulty.EASY){ // Sets the selected value as colored
            draw.setFill(Color.GOLD);
        } else {
            draw.setFill(Color.WHITE);
        }

        draw.fillText("1: Easy", 100, 140);

        if (gameManager.getDifficulty() == Difficulty.MEDIUM){
            draw.setFill(Color.GOLD);
        } else {
            draw.setFill(Color.WHITE);
        }

        draw.fillText("2: Medium", 100, 180);

        if (gameManager.getDifficulty() == Difficulty.HARD){
            draw.setFill(Color.GOLD);
        } else {
            draw.setFill(Color.WHITE);
        }

        draw.fillText("3: Hard", 100, 220);

        draw.setFill(Color.WHITE);

        draw.fillText("High Score: " + highScoreManager.getHighScore(), 100, 260);
    }

    /**
     * Handles inputs for starting and selecting difficulty
     * @param input
     */
    public void handleInput(ArrayList<String> input) {
        if (currentState == GameState.MENU) {
            if (input.contains("S")) {
                currentState = GameState.RUNNING;
                gameManager.startGame();
            }
            if (input.contains("DIGIT1")) {
                gameManager.setDifficulty(Difficulty.EASY);
            }
            if (input.contains("DIGIT2")) {
                gameManager.setDifficulty(Difficulty.MEDIUM);
            }
            if (input.contains("DIGIT3")) {
                gameManager.setDifficulty(Difficulty.HARD);
            }
            if (currentState == GameState.GAME_OVER) {
                if (input.contains("CONTINUE")) {
                    currentState = GameState.MENU;
                    gameManager.resetGame();
                }
            }
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
