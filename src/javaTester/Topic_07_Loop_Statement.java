package javaTester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Topic_07_Loop_Statement {

	public static void main(String[] args) {
		String[] studentName = { "Hưng", "Nam", " Hoang", " hanh" };

		for (int i = 0; i < studentName.length; i++) {
			System.out.println(studentName[i]);
		}
		
		List<String> address = new ArrayList<String>();
		address.add("Truong");
		address.add("Hang");
		for (Iterator iterator =address.iterator();iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

}
