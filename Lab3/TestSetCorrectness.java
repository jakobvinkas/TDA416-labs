import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class TestSetCorrectness {
    public static void main(String[] args) {
        int n1 = 0, n2 = 0, n3 = 0, n4 = 0;
        try {
            n1 = Integer.parseInt(args[0]);
            n2 = Integer.parseInt(args[1]);
            n3 = Integer.parseInt(args[2]);
            n4 = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal arguments given to program");
            return;
        }

        Random random = new Random();

        // how many resets?
        for (int i = 0; i < n2; i++) {
            SimpleSet<Integer> testSet = null;
            Set<Integer> javaSet = new HashSet<>();

            if (n1 == 1) {
                testSet = new SortedLinkedListSet<>();
            } else if (n1 == 2) {
                testSet = new SplayTreeSet<>();
            } else {
                System.out.println("n1 must be either 1 or 2");
                return;
            }

            // how many operations?
            for (int j = 0; j < n3; j++) {
                int operation = random.nextInt(4);
                int value = random.nextInt(n4);
                switch (operation) {
                    case 0: // size
                        if (testSet.size() != javaSet.size()) {
                            System.out.println("Error! size(): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 1: // add
                        if (testSet.add(value) != javaSet.add(value)) {
                            System.out.println("Error! add(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 2: // remove
                        if (testSet.remove(value) != javaSet.remove(value)) {
                            System.out.println("Error! remove(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 3: // contains
                        if (testSet.contains(value) != javaSet.contains(value)) {
                            System.out.println("Error! contains(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                }
            }
        }

        System.out.println("All tests passed!");
    }
}
