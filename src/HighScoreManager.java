package src;
import java.util.prefs.Preferences;

/**
 * Keeps track of the current top score
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class HighScoreManager {
    private Preferences prefs;
    // Constructor initializes the instance for HighScoreManager class.
    public HighScoreManager() {
        prefs = Preferences.userNodeForPackage(HighScoreManager.class);
    }

    public int getHighScore() {
        return prefs.getInt("highScore", 0); // Default to 0 if not set
    }

    public void setHighScore(int score) {
        prefs.putInt("highScore", score);
    }  // Updates the high score in user preferences.
}
