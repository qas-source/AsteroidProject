package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font; // Import statement for Font
import java.util.ArrayList;

public class MenuManager {

    private GameManager gameManager;
    private GraphicsContext draw;
    private GameState currentState;

    public MenuManager(GraphicsContext draw, GameManager gameManager) {
        this.draw = draw;
        this.gameManager = gameManager;
        this.currentState = GameState.MENU;
    }
    
    public void drawMenu() {
        System.out.println("Drawing Menu");
        // Clear the screen and draw the menu
        draw.clearRect(0, 0, 1000, 700); // Assuming canvas size, adjust as necessary
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, 1000, 700);

        draw.setFill(Color.WHITE);
        draw.setFont(new Font(24)); // Set the font for the text
        draw.fillText("Press S to Start", 100, 100);
        draw.setFont(new Font(18)); // Change the font size for other options
        draw.fillText("1: Easy", 100, 140);
        draw.fillText("2: Medium", 100, 180);
        draw.fillText("3: Hard", 100, 220);
    }

    public void handleInput(ArrayList<String> input) {
        if (currentState == GameState.MENU) {
            if (input.contains("S")) {
                currentState = GameState.RUNNING;
                gameManager.startGame();
            }
            if (input.contains("1")) {
                System.out.println("Difficulty set to EASY"); 
                gameManager.setDifficulty(Difficulty.EASY);
            }
            if (input.contains("2")) {
                System.out.println("Difficulty set to MEDIUM"); 
                gameManager.setDifficulty(Difficulty.MEDIUM);
            }
            if (input.contains("3")) {
                System.out.println("Difficulty set to HARD"); 
                gameManager.setDifficulty(Difficulty.HARD);
            }
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
