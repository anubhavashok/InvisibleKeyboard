import java.util.*;
class Parent
{
	ArrayList<Child> children = new ArrayList<Child>();

	void add_child(Child c)
	{
		children.add(c);
		Collections.sort(children, new ChildComparator());
	}

	public class ChildComparator implements Comparator<Child> {
		@Override
		public int compare(Child c1, Child c2) {
			return ((c1.frequency > c2.frequency)? 1:0);
		}
	}
}
