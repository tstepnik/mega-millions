import java.util.Comparator;

public class Number {
    private int number;
    private int howManyTimesOccurs;

    public Number(int number, int howManyTimesOccurs) {
        this.number = number;
        this.howManyTimesOccurs = howManyTimesOccurs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHowManyTimesOccurs() {
        return howManyTimesOccurs;
    }

    public void setHowManyTimesOccurs(int howManyTimesOccurs) {
        this.howManyTimesOccurs = howManyTimesOccurs;
    }

    @Override
    public String toString() {
        return  number + " = " + howManyTimesOccurs;
    }

}
