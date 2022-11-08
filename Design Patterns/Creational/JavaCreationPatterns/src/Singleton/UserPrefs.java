package Singleton;

public class UserPrefs {

    private String prefColor;
    private String prefFont;

    private static UserPrefs singleton;

    private UserPrefs() {}

    public String getPrefColor() {
        return prefColor;
    }

    public void setPrefColor(String prefColor) {
        this.prefColor = prefColor;
    }

    public String getPrefFont() {
        return prefFont;
    }

    public void setPrefFont(String prefFont) {
        this.prefFont = prefFont;
    }

    public static UserPrefs getInstance() {
        if(singleton == null){
            singleton = new UserPrefs();
        }
        return singleton;
    }

}
