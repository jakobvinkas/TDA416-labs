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

        SimpleSet<Integer> testSet;

        if (n1 == 1) {
            testSet = new SortedLinkedListSet<>();
        } else if (n1 == 2) {
            // testSet = new SplayTreeSet<>();
        } else {
            System.out.println("n1 must be either 1 or 2");
            return;
        }


    }
}
