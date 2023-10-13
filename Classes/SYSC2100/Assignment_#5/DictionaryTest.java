// The "DictionaryTest" class.
// This class tests the BST implementations of the Dictionary 
// interface by inserting 676 different entries, removing 
// half of them, and inserting 156 more entries. It also
// prints the initial dictionary (i.e., the one after
// inserting the first 676 entries) and searches for 6 
// randomly chosen entries. 
public class DictionaryTest {
	protected static String[] entries = new String[26 * 26];

	protected static void fill() {
		// Insert 26 * 26 entries
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 26; j++) {
				StringBuffer s = new StringBuffer();
				s.append((char) ((int) 'A' + i));
				s.append((char) ((int) 'A' + j));
				entries[i * 26 + j] = s.toString();
			}
	} // fill method

	
	public static void main(String[] args) {
		BSTDictionary<String, SortableString> dict1 = new BSTDictionary<String, SortableString>();

		// Insert lots of entries
		fill();
		for (int i = 0; i < 26 * 26; i++) {
			int e;
			do {
				e = (int) (Math.random() * (26 * 26));
			} while (entries[e] == null);

			dict1.insert(new SortableString(entries[e]), entries[e]);
			entries[e] = null;
		}

		// print the two dictionaries
		dict1.printTree();
		// print the depth
		System.out.println("The initial BST tree has a maximum depth of "
				+ dict1.depth());

		// Delete half the entries
		fill();
		for (int i = 0; i < 13 * 26; i++) {
			int e;
			do {
				e = (int) (Math.random() * (26 * 26));
			} while (entries[e] == null);

			dict1.delete(new SortableString(entries[e]));
		}

		System.out
				.println("After deletes, the BST tree has a maximum depth of "
						+ dict1.depth());

		// Add a quarter the entries
		fill();
		for (int i = 0; i < 6 * 26; i++) {
			int e;
			do {
				e = (int) (Math.random() * (26 * 26));
			} while (entries[e] == null);

			dict1.insert(new SortableString(entries[e]), entries[e]);
		}

		System.out
				.println("After insertions, the BST tree has a maximum depth of "
						+ dict1.depth());

		// Search for a few random entries
		fill();
		for (int i = 0; i < 6; i++) {
			int e;
			do {
				e = (int) (Math.random() * (26 * 26));
			} while (entries[e] == null);

			System.out.print("Searching for " + entries[e] + ": ");
			if (dict1.search(new SortableString(entries[e])) == null) {
				System.out.print("Not found\n");
			} else {
				System.out.print("Found\n");
			}
		}
	} // main method
} /* DictionaryTest class */
