public class TestingSplayTreeSet{

	public static void main(String[] args){
		testAdd();
		//testRotate();
	}

	private static void testAdd() {
		SimpleSet<Integer> list = new SplayTreeSet<>();

		list.add(2);
		System.out.println(list);
		list.add(1);
		System.out.println(list);
		list.add(4);
		System.out.println(list);
		list.add(5);
		System.out.println(list);
		list.add(9);
		System.out.println(list);
		list.add(3);
		System.out.println(list);
		list.add(6);
		System.out.println(list);
		list.add(7);
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
