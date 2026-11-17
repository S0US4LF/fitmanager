
package br.com.fitmanager.util;

import java.net.URL;
import java.util.prefs.Preferences;

import javafx.scene.Scene;

public class ThemeManager {

    private static final Preferences PREFERENCES =
        Preferences.userNodeForPackage(ThemeManager.class);

    private static final String DARK_MODE_KEY = "darkMode";

    private static final String LIGHT_THEME =
        "/br/com/fitmanager/css/styles.css";

    private static final String DARK_THEME =
        "/br/com/fitmanager/css/dark-styles.css";

    public static boolean isDarkMode() {
        return PREFERENCES.getBoolean(DARK_MODE_KEY, false);
    }

    public static void setDarkMode(boolean darkMode) {
        PREFERENCES.putBoolean(DARK_MODE_KEY, darkMode);
    }

    public static void applyTheme(Scene scene, boolean darkMode) {

        String caminho = darkMode ? DARK_THEME : LIGHT_THEME;

        URL arquivoCSS = ThemeManager.class.getResource(caminho);

        if (arquivoCSS == null) {
            throw new IllegalStateException(
                "Não foi possível encontrar o arquivo de tema: " + caminho
            );
        }

        scene.getStylesheets().setAll(arquivoCSS.toExternalForm());
    }
}
