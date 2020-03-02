import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MegaMillions {

    File file = new File("src/main/resources/mega_millions.csv");
    String absolutePath = file.getAbsolutePath();
    private List<Integer> allWinningNumbers = getAllWinningNumbers();
    private List<Number> uniqueWinningNumbersWithOccur = getUniqueNumbersWithOccurs(allWinningNumbers);

    public List<Number> getUniqueWinningNumbersWithOccur() {
        return uniqueWinningNumbersWithOccur;
    }

    private List<Integer> getAllWinningNumbers() {
        String line = "";
        List<Integer> winningNumbers = new ArrayList<>();
        try (
                var fileReader = new FileReader(absolutePath);
                var bufferedReader = new BufferedReader(fileReader);
        ) {
            while (bufferedReader.readLine() != null) {
                line = bufferedReader.readLine();
                addNumbersFromLineToList(line, winningNumbers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return winningNumbers;
    }

    public void addNumbersFromLineToList(String line, List<Integer> winningNumbersList) {
        String[] splitedByComma = line.split(",");
        String[] numbers = splitedByComma[1].split(" ");
        for (String number : numbers) {
            System.out.print(number + " ");
            winningNumbersList.add(Integer.parseInt(number));
        }
        System.out.println();
    }

    private List<Number> getUniqueNumbersWithOccurs(List<Integer> list) {
        List<Number> numbersWithOccurs = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            int occurs = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == i) {
                    occurs++;
                }
            }
            numbersWithOccurs.add(new Number(i, occurs));
        }
        return numbersWithOccurs;
    }

    //    i did this method according to Task but still cant't figure out how to count numbers properly.gi
    public void tenMostPopularNumberFromList(List<Number> list) {
        System.out.println("10 najpopularniejszych liczb:");
        list.sort(Comparator.comparingInt(Number::getNumber).reversed());
        for (int i = 0; i < list.size(); i++) {
            String info = list.get(i).toString();
            System.out.println(info);
        }
    }
}
