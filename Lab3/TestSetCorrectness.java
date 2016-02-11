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

        System.out.println(n1);
    }
}
