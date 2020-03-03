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

    public void filterAndPrintNumbers() {
        List<Integer> allWinningNumbers = new ArrayList<>();
        List<Integer> allWinningMegaBall = new ArrayList<>();
        getAllWinningNumbers(allWinningNumbers, allWinningMegaBall);
        List<Number> winning75Numbers = getUniqueNumbersWithOccurs(allWinningNumbers);
        List<Number> winningMegaBalls = getUniqueNumbersWithOccurs(allWinningMegaBall);
        System.out.println("Top 10 winning numbers:");
        mostPopularNumberFromList(winning75Numbers, 10);
        System.out.println("Top 3 winning mega ball:");
        mostPopularNumberFromList(winningMegaBalls, 3);
    }

    private void getAllWinningNumbers(List<Integer> winningNumbers, List<Integer> megaBalls) {
        String line = "";
        try (
                var fileReader = new FileReader(absolutePath);
                var bufferedReader = new BufferedReader(fileReader);
        ) {
            while ((line = bufferedReader.readLine()) != null) {
                addWinningNumbers(line, winningNumbers);
                addMegaBalls(line,megaBalls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWinningNumbers(String line, List<Integer> winningNumbersList) {
        String[] splitByComma = line.split(",");
        String[] numbers = splitByComma[1].split(" ");
        for (String number : numbers) {
            if (number.matches("-?\\d+")) {
                winningNumbersList.add(Integer.parseInt(number));
            }
        }
    }

    public void addMegaBalls(String line, List<Integer> megaBalls){
        String[] splitByComma = line.split(",");
        String[] megaBall = splitByComma[2].split(" ");
        for (String ball : megaBall) {
            if (ball.matches("-?\\d+")) {
                megaBalls.add(Integer.parseInt(ball));
            }
        }
    }

    private List<Number> getUniqueNumbersWithOccurs(List<Integer> list) {
        List<Number> numbersWithOccurs = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
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

    public void mostPopularNumberFromList(List<Number> numbers, int howManyPrint) {
        numbers.sort(Comparator.comparingInt(Number::getHowManyTimesOccurs).reversed());
        for (int i = 0; i < howManyPrint; i++) {
            String info = numbers.get(i).toString();
            System.out.println(info);
        }
        System.out.println();
    }
}
