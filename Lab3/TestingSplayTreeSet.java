public class TestingSplayTreeSet{

	public static void main(String[] args){
		// testAdd();
		testRotate();
	}

	private static void testAdd() {
		SimpleSet<Integer> list = new SplayTreeSet<>();

		list.add(4);
		list.add(2);
		list.add(9);
		list.add(10);
		list.add(10);
		list.add(10);
		list.add(99);
		list.add(3);
		list.add(1);
		list.add(2);
		System.out.println(list);
	}

	private static void testRotate() {
		SimpleSet<Integer> list = new SplayTreeSet<>();
		list.add(6);
		list.add(7);
		list.add(5);
		System.out.println(list);
	}
}
