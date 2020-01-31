package Day11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetConcept {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<String> set = new HashSet<String>();
		System.out.println(set.size());
		// no indexing on Set
		//no Order
		set.add("Apple");
		set.add("Orange");
		set.add("Mango");
		set.add("Grapes");
		set.add("Grapes"); // no dupes are allowed
		set.add("Orange");
		// add as much elements.
		System.out.println(set.size());
		
		//use Iteratror to get elements of set
		Iterator<String> it = set.iterator();
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
	/*	while(it.hasNext()) {
			System.out.println(it.next());
		}*/
	}

}
