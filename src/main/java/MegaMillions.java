import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MegaMillions {

    File file = new File("src/main/resources/mega_millions.csv");
    String absolutePath = file.getAbsolutePath();

    public void filterAndPrintNumbers() {
        List<Integer> allWinningNumbers = new ArrayList<>();
        List<Integer> allWinningMegaBall = new ArrayList<>();
        getAllWinningNumbers(allWinningNumbers, allWinningMegaBall);
        TreeMap<String, Integer> winning75Numbers = getUniqueNumbersWithOccurs(allWinningNumbers);
        TreeMap<String, Integer> winningMegaBalls = getUniqueNumbersWithOccurs(allWinningMegaBall);
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
                addMegaBalls(line, megaBalls);
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

    public void addMegaBalls(String line, List<Integer> megaBalls) {
        String[] splitByComma = line.split(",");
        String[] megaBall = splitByComma[2].split(" ");
        for (String ball : megaBall) {
            if (ball.matches("-?\\d+")) {
                megaBalls.add(Integer.parseInt(ball));
            }
        }
    }

    private TreeMap<String, Integer> getUniqueNumbersWithOccurs(List<Integer> list) {
        TreeMap<String, Integer> numbersWithOccurs = new TreeMap<>();
        for (int i = 1; i <= Collections.max(list); i++) {
            int occurs = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == i) {
                    occurs++;
                }
            }
            numbersWithOccurs.put(String.valueOf(i), occurs);
        }
        return numbersWithOccurs;
    }

    public void mostPopularNumberFromList(TreeMap<String, Integer> numbers, int howManyPrint) {
        LinkedHashMap<String, Integer> hashMap = numbers.entrySet().stream().sorted(Comparator.comparing(
                Map.Entry<String, Integer>::getValue).reversed()).collect(
                LinkedHashMap<String, Integer>::new,
                (map1, e) -> map1.put(e.getKey(), e.getValue()),
                LinkedHashMap::putAll);

        LinkedHashMap<String, Integer> topXNumbers = putFirstEntries(howManyPrint, hashMap);

        topXNumbers.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        });
    }

    public LinkedHashMap<String, Integer> putFirstEntries(int max, LinkedHashMap<String, Integer> source) {
        int count = 0;
        LinkedHashMap<String, Integer> target = new LinkedHashMap();
        for (Map.Entry<String, Integer> entry : source.entrySet()) {
            if (count >= max) break;

            target.put(entry.getKey(), entry.getValue());
            count++;
        }
        return target;
    }


}
