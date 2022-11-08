package Singleton;

public class TestDriver {
    public static void main(String args[]) {
        UserPrefs pref = UserPrefs.getInstance();
        pref.setPrefColor("Blue");
        UserPrefs pref1 = UserPrefs.getInstance();
        pref1.setPrefColor("Red");
        System.out.println("pref color - " + pref.getPrefColor() + ":" + " pref1 color - " + pref1.getPrefColor());
    }
}
