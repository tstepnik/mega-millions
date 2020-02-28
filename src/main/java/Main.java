import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        MegaMillions mega = new MegaMillions();
        mega.getUniqueWinningNumbersWithOccur().forEach(System.out::println);
    }
}