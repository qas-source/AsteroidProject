package src;
import java.util.prefs.Preferences;

/**
 * Keeps track of the current top score
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class HighScoreManager {
    private static HighScoreManager instance; // Singleton instance
    private Preferences prefs;

    // Private constructor to prevent direct instantiation.
    private HighScoreManager() {
        prefs = Preferences.userNodeForPackage(HighScoreManager.class);
    }

    // Static method to get the singleton instance of HighScoreManager.
    public static synchronized HighScoreManager getInstance() {
        if (instance == null) {
            instance = new HighScoreManager();
        }
        return instance;
    }

    // Retrieves the high score from user preferences.
    public int getHighScore() {
        return prefs.getInt("highScore", 0); // Default to 0 if not set
    }

    // Updates the high score in user preferences.
    public void setHighScore(int score) {
        prefs.putInt("highScore", score);
    }
}