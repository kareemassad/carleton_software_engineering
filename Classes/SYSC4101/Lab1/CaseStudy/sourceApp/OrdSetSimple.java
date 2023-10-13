public class OrdSetSimple {
  private int _setCapacity;
  private int _set[];
  private int _indexLastElement;


  /**
   * Constructor of an ordered set of integers.
   * @param capacity The capacity of the set, that is the maximum number of elements the set can contain.
   */
  public OrdSetSimple(int capacity) {
    int index;
  	_setCapacity=capacity;
    _set = new int[_setCapacity];
  	for(index=0; index<_setCapacity; index++)
	  	_set[index]=0;
    _indexLastElement=-1;
  }
  
  /**
   * When adding an element to an non-empty set, we first need to make some room for it.
   * @param val The integer element to add to the set
   * @return The position that has been freed and where the element can be added.
   */
  private int make_a_free_slot(int val) {
  	int pos, i, j;

  	pos = find(val);
  	if (pos >= 0)
		return -1;
  	for (i=0; i<=_indexLastElement; i++) {
	  	if (_set[i] > val)
		  	break;
   	}

  	if ((i<=_indexLastElement)||(_indexLastElement==-1)) {
  		for (j=_indexLastElement; j>=i; j--)
		  	_set[j+1] = _set[j];
  		pos = i;
    } else {
		pos = _indexLastElement+1;
  	}
	_indexLastElement++;
  	return pos;
  }
 
  /**
   * Adding an element to the set.
   * @param n The integer value to add to this set.
   */
  public void addElement(int n) {
  	int pos;
	if (n<=0) {
		System.out.println("Addition of a negative integer impossible\n");
  		return;
	}
  	if (_indexLastElement+1>=_setCapacity) {
  		System.out.print("Addition of " + n);
  		System.out.println(" impossible since the array is already full");
  		System.out.println("The array is: " + toString());
  	} else {
  		pos = make_a_free_slot(n);
  		if (pos>=0)
	  		_set[pos]=n;
  	}
    return;
  }

  /**
   * Remoning an element from the set
   * @param val The integer value to remove
   * @return true if the element has been removed, false otherwise (i.e., the element is not in the set)
   */
  public boolean remove(int val) {
	int where = find(val);
	int index;
	if (where >= 0) {
		for (index = where; index < _indexLastElement; index++) {
			_set[index] = _set[index+1];
		}
		_indexLastElement --;
		return true;
	} else {
		return false;
	}
  }

  /**
   * Returns the capacity of this set, that is the maximum number of elements we can have in this set.
   * @return the capacity.
   */
  public int getCapacity() {
	  return _setCapacity;
  }
  
  /**
   * Returns the size of the set, that is the number of elements it contains.
   * @return The size.
   */
  public int getSize() {
    return _indexLastElement+1;
  }

  /**
   * Returns the element from this set at a given index.
   * @param i The index of the element we want.
   * @return The element at index i.
   */
  public int getElementAt(int i) {
  	if ((i<0)||(i>_indexLastElement))
	  	return -1;
  	else
	  	return _set[i];
  }
  
  /**
   * Searches for an element in this set
   * @param x The element we are looking for
   * @return The index where the element has been found, -1 otherwise
   */
  public int find(int x) {
  	int i=0;
	int j=_indexLastElement;
  	int m=0;

  	if (_indexLastElement==-1)
	  	return -1;

  	while(i<j) {
	  	m= (i+j)/2;
    	if (x>_set[m])
		  	i=m+1;
  		else
	  		j=m;
  	}
  	if (x == _set[i]) return i;
    else return -1;
  }

  /**
   * Computes the set difference between two sets
   * @param The other set. 
   * @return The result of this set minus the set passed as an argument
   */
  public OrdSetSimple difference(OrdSetSimple s2) {
    OrdSetSimple s1 = this;
    OrdSetSimple newSet=new OrdSetSimple(s2.getSize());
    for(int k=0; k<s1.getSize(); k++)
		if (s2.find(s1.getElementAt(k)) < 0)
			newSet.addElement(s1.getElementAt(k));
    return newSet;
  }

  /**
   * Computes the union of two sets
   * @param The other set
   * @return The result of this set union (plus) the set passed as a parameter.
   */
  public OrdSetSimple union(OrdSetSimple s2) {
	OrdSetSimple s1 = this;
	int size1 = s1.getSize();
	int size2 = s2.getSize();
		
	OrdSetSimple newSet = new OrdSetSimple(size1 + size2);
		
	int lb1 = 0, lb2 = 0, pos = 0;
	while (lb1 < size1 && lb2 < size2) {
		if (s1.getElementAt(lb1) < s2.getElementAt(lb2)) { 
			newSet.addElement(s1.getElementAt(lb1));
			pos = pos + 1;
			lb1 = lb1 + 1;
		} else if (s2.getElementAt(lb2) < s1.getElementAt(lb1)) {
			newSet.addElement(s2.getElementAt(lb2));
			pos = pos + 1;
			lb2 = lb2 + 1;
		} else if (s1.getElementAt(lb1) == s2.getElementAt(lb2)) {
			newSet.addElement(s2.getElementAt(lb2));
			pos = pos + 1;
			lb1 = lb1 + 1;
			lb2 = lb2 + 1;
		} else {
			System.out.println("error in comparing elements in union.");
			System.exit(1);
		}
	}	
	while (lb1 < size1) {
		newSet.addElement(s1.getElementAt(lb1));
		pos = pos + 1;
		lb1 = lb1 + 1;
	}	
	while (lb2 < size2) {
		newSet.addElement(s2.getElementAt(lb2));
		pos = pos + 1;
		lb2 = lb2 + 1;
	}
	return newSet;
  }

  /**
   * Produces a string repsentation of this set with a '{' and a '}' starting and ending the string, and elements separated with spaces.
   * @return The string representation
   */
  public String toString() {
    String s="{ ";

    for (int k=0; k<=_indexLastElement; k++)
  		s += _set[k] + " ";
    s += "}";
    return s;
  }
  
  /**
   * Determines whether two sets are equal, i.e., same elements.
   * @param The other set 
   * @return true if this set is equal to the set passed as an argument, false otherwise.
   */
  public boolean equals(OrdSetSimple s2) {
		OrdSetSimple s1 = this;
		
		if (s1.getSize() != s2.getSize())
			return false;
		
		for (int n = 0; n < s1.getSize(); n++) {
			if (s1.getElementAt(n) != s2.getElementAt(n)) {
				return false;
			}
		}
		return true;
	}

}




 


