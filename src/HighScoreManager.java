package src;
import java.util.prefs.Preferences;

public class HighScoreManager {
    private Preferences prefs;

    public HighScoreManager() {
        prefs = Preferences.userNodeForPackage(HighScoreManager.class);
    }

    public int getHighScore() {
        return prefs.getInt("highScore", 0); // Default to 0 if not set
    }

    public void setHighScore(int score) {
        prefs.putInt("highScore", score);
    }
}
