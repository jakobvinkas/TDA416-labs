public class TestingSortedList{

	public static void main(String[] args){
		SimpleSet<Integer> list = new SortedLinkedListSet<>();

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
		System.out.println(list.size());
	}
}