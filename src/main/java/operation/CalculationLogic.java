package operation;

public class CalculationLogic {
    public static Integer calculation(Integer number) {

        Integer[] factorCount = new Integer[number + 1];

        for (int i = 0; i <= number; i++) {
            factorCount[i] = 0;
        }

        for (int i = 2; i <= number; i++) {
            if (factorCount[i] == 0) {
                for (int j = i; j <= number; j += i) {
                    factorCount[j]++;
                }
            }
        }

        return factorCount[number];
    }
}
