package app.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class to manage internationalization.
 */
public abstract class Strings {

    private static ResourceBundle BUNDLE;
    private static final String BUNDLE_NAME = "strings";

    public static final String MAIN_APP_TITLE;

    static {
        final Locale locale = Locale.getDefault();
        ResourceBundle tempBundle = null;

        try {
            tempBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        } catch (MissingResourceException e) {
            System.out.println("Warning: Missing resource bundle: " + BUNDLE_NAME);
        }

        BUNDLE = tempBundle;

        if (BUNDLE != null) {
            MAIN_APP_TITLE = BUNDLE.getString("Main.AppTitle");
        } else {
            MAIN_APP_TITLE = "SimpleHR";
        }
    }

    public static ResourceBundle GetBundle() {
        return BUNDLE;
    }
}
