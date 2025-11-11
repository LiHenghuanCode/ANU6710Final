# Time complexity exercises

## Exercise 1 - `LinkedList<T>` and `ArrayList<T>` class implementations of a simpler `List<T>` interface

The code below contains a simpler `List` interface (compared to the one in the Java collections framework)
as well as full implementations of  `LinkedList` (a doubly-linked
list) and `ArrayList` for that simpler interface.

The `ListFunctions` class at the bottom
implements some generic static list methods on top of that.

Let `N` be the size of a list. For both `ArrayList` and `LinkedList`
(but separately), what are the worst-case time complexities in terms
of `N` for the following methods? Why?
- `add`
- `get`
- `remove`
- `reverse`
- `contains`
- `indexOf`

Under what conditions which of these methods could improve to `O(log N)`?

```java
public interface List<T> {

    void add(T o);

    T get(int index); //index must be between 0 and size of list-1
    
	int size();

    T remove(int index); // removes Object at index from list and returns it

    void reverse();
}
```

```java
public class LinkedList<T> implements List<T> {
    ListNode start; //is null if list empty, otherwise points to first element
    ListNode end; // is null if list empty, otherwise points to last element
    int size=0;
    private class ListNode {
        T value;
        ListNode next;
        public ListNode(T value, ListNode next) {
            this.value=value;
            this.next=next;
        }
    }
    @Override
    public void add(T o) {
        ListNode newNode = new ListNode(o, null);
        if(start==null) {
            start=newNode;
            end=newNode;
        } else {
            end.next=newNode;
            end=newNode;
        }
        size++;
    }

    //index must be between 0 and size of list-1
    @Override
    public T get(int index) {
        ListNode current=start;
        while(index>0 && current!=null) {
            current=current.next;
            index--;
        }
        if(current==null) {
            return null;
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        ListNode current=start;
        ListNode previous=null;
        while(index>0 && current!=null) {
            previous=current;
            current=current.next;
            index--;
        }
        if(current==null) {
            return null;
        }
        size--;
        if(current==end) {
            end=previous;
        }
        if(previous==null) {
            start = current.next;
            return current.value;
        } else {
            previous.next=current.next;
            return current.value;
        }
    }

    private ListNode reverseRecursive(ListNode current) {
        if(current.next==null) {
            start=current;
            return current;
        } else {
            ListNode newPrevious=reverseRecursive(current.next);
            newPrevious.next=current;
            current.next=null;
            end=current;
            return current;
        }
    }
    @Override
    public void reverse() {
        if(start!=null) {
            reverseRecursive(start);
        }
    }

    public String toString() {
        if(start==null) {
            return "";
        }
        String ret="";
        ListNode current=start;
        while(current!=null) {
            if(ret.length()>0) {
                ret+=" ";
            }
            ret+=current.value.toString();
            current=current.next;
        }
        return ret;
    }
}
```

```java
public class ArrayList<T> implements List<T> {
    public static final int GROW_FACTOR = 2;
    T[] elements=(T[])new Object[1];
    int size=0;
    @Override
    public void add(T o) {
        if(elements.length>size) {
            elements[size] = o;
        } else {
            T[] newArr=(T[])new Object[elements.length*GROW_FACTOR];
            for(int i=0;i<size; i++) {
                newArr[i] = elements[i];
            }
            newArr[size]=o;
            elements=newArr;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if(index>=size || index<0) {
            return null;
        }
        return elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if(index>=size || index<0) {
            return null;
        }
        T ret=elements[index];
        for(int i=index;i<size-1;i++) {
            elements[i]=elements[i+1];
        }
        elements[size-1]=null;
        size--;
        return ret;
    }

    @Override
    public void reverse() {
        T[] newArr=(T[])new Object[elements.length];
        for(int i=size-1;i>=0;i--) { // could just as well go from 0 to size
            newArr[i]=elements[size-i-1];
        }
        elements=newArr;
    }

    public String toString() {
        String ret="";
        for(int i=0;i<size;i++) {
            if(ret.length()>0) {
                ret+=" ";
            }
            ret+=elements[i].toString();
        }
        return ret;
    }
}
```

```java
public class ListFunctions {
    static boolean contains(int element, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while(left <= right) {
            int pos = left + ((right - left) / 2);
            int num = list.get(pos);
            if(num == element) {
                return true;
            }
            if(num < element) {
                left = pos + 1;
            }
            else
            {
                right = pos - 1;
            }
        }
        return false;
    }
    int indexOf(int element, List<Integer> list) {
        int pos = 0;
        while(pos < list.size()) {
            if(list.get(pos) == element) {
                return pos;
            }
            pos++;
        }
        return -1;
    }
}

```

## Exercise 2 - Determining the worst-case time complexity of a couple of methods

Given the following Java class and its methods:

```java
public class DetermineComplexity {

  public static int determineComplexity21(int [] numbers)
  {
     int n=numbers.length;
     int result=0;
     for (int i=0; i<n; i++)
     {
        for (int j=i+1; j<n; j++)
        {
            if (numbers[i]==numbers[j])
            {
                result++;
            }
        }
     }
     return result;
  }

  public static double determineComplexity22(double [][] numbers)
  {
     int n = numbers.length;
     double result=0.0;
     for (int i=0; i<n; i++)
     {
        for (int j=Math.max(i-1,0); j<Math.min(i+2,n); j++) 
        {
            result = result + numbers[i][j];
        }
     }
     return result;
  }

}
```

### Part 1

What is the tightest upper bound for the worst-case time complexity of `determineComplexity21` in terms of `N` as the size of the `numbers` array? Why?

a. O(N<sup>4</sup>)

b. O(N * log(N))

c. O(N<sup>2</sup>)

d. O(1)

e. O(N<sup>N</sup>)

f. O(2<sup>N</sup>)

g. O(N!)

h. O(N)

i. O(N<sup>3</sup>)

j. O(log(N))


### Part 2

What is the tightest upper bound for the worst-case time complexity of `determineComplexity22` in terms of `N` as the size of the `numbers` array of arrays? Why?


You can assume that the size of `numbers` is the same as the size of all arrays that it contains. That is, `numbers.length` is
equal to `numbers[0].length`, `numbers.length` is equal to `numbers[1].length`, and so on till the last element of `numbers`.

a. O(N<sup>4</sup>)

b. O(N * log(N))

c. O(N<sup>2</sup>)

d. O(1)

e. O(N<sup>N</sup>)

f. O(2<sup>N</sup>)

g. O(N!)

h. O(N)

i. O(N<sup>3</sup>)

j. O(log(N))


## Exercise 3 - Determining the worst-case time complexity of a couple of methods

Given the following Java class and its methods:

```java
public class DetermineComplexity {

 public static int determineComplexity31(int [] numbers)
 {
     int n=numbers.length;
     int result=0;
     for (int i=1; i<n-1; i++)
     {
        for (int j=i-1; j<i+2; j++)
        {
            result += numbers[j];
        }
     }
     return result;
  }

  public static int determineComplexity32(int n)
  {
     int result=0;
     for (int i=1; i<n; i++)
     {
        for (int j=1; j<n; j++) 
        {
           for (int k=1; k<n; k++) 
           {
              result += i*j*k; 
           }
        }
     }
     return result;
  }

}
```

### Part 1

What is the tightest upper bound for the worst-case time complexity of `determineComplexity31` in terms of `N` as the size of the `numbers` array? Why?

a. O(N<sup>4</sup>)

b. O(N * log(N))

c. O(N<sup>2</sup>)

d. O(1)

e. O(N<sup>N</sup>)

f. O(2<sup>N</sup>)

g. O(N!)

h. O(N)

i. O(N<sup>3</sup>)

j. O(log(N))


### Part 2

What is the tightest upper bound for the worst-case time complexity of `determineComplexity32` in terms of `N` the value of the input parameter? Why?

a. O(N<sup>4</sup>)

b. O(N * log(N))

c. O(N<sup>2</sup>)

d. O(1)

e. O(N<sup>N</sup>)

f. O(2<sup>N</sup>)

g. O(N!)

h. O(N)

i. O(N<sup>3</sup>)

j. O(log(N))


## Exercise 4 - Determining the worst-case time complexity of a couple of methods


Given the following Java class and its methods:

```java
class Primality {
    private static final int[] factors = {2, 3, 5, 7, 11, 13, 17, 19};
    /**
     * Given an array of integers >= 0, returns false if any of the integers is definitely not prime, true otherwise
     */
    public static boolean basicPrimalityCheck(int[] numbers) {
        for(int number : numbers) {
            for(int factor : factors) {
                if(number % factor == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Given an integer >= 0, returns true if it is prime, false otherwise
     */
    public static boolean isPrime(int number) {
        for(int i=number/2; i>1; i--) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given an array of integers >= 0, true if all integers are prime, false otherwise
     */
    public static boolean fullPrimalityCheck(int[] numbers) {
        for(int number : numbers) {
            if(!isPrime(number)) {
                return false;
            }
        }
        return true;
    }
}
```

### Part 1

What is the tightest upper bound for the worst-case time complexity of `basicPrimalityCheck` in terms of `N` as the size of the `numbers` array and
`M` as the maximum size of any integer in the array? Why?

a. O(N<sup>2</sup>)

b. O(M<sup>2</sup>)

c. O(N * log(N))

d. O(N * log(M))

e. O(1)

f. O(N)

g. O(M)

h. O(N + M)

i. O(N * M)

j. O(log(max(N, M))

#### Part 2

What is the tightest upper bound for the worst-case time complexity of `fullPrimalityCheck` in terms of `N` as size of the `numbers` array and
`M` as the maximum size of any integer in the array? Why?

a. O(N<sup>2</sup>)

b. O(M<sup>2</sup>)

c. O(N * log(N))

d. O(N * log(M))

e. O(1)

f. O(N)

g. O(M)

h. O(N + M)

i. O(N * M)

j. O(log(max(N, M))




## Exercise 5 - Determining the worst-case time complexity of a couple of functions (distinction-level)

Given the following functions:

```java
int splitSum(int[] ints, int start, int end) {
    if(start==end) {
        return 0;
    } else if(end-start=1) {
        return ints[start];
    } else {
        return splitSum(ints, start, start+(end-start)/2) + splitSum(ints, start+(end-start)/2, end);
    }
}

class BinTreeNode {
    int value;
    BinTreeNode left;
    BinTreeNode right;
}

List<Integer> treeList(BinTreeNode root) {
    return treeListRec(root, new ArrayList<>());
}
List<Integer> treeListRec(BinTreeNode root, List<Integer> acc) {
    if(root!=null) {
        treeListRec(root.left, acc);
        acc.add(root.value);
        treeListRec(root.right, acc);
    }
}
```

Which are the tightest bounds on the worst-case running times of the above functions? Why?

a. for `splitSum`, O(log(N)), where N is the length of `ints`

b. for `splitSum`, O(N), where N is the length of `ints`

c. for `splitSum`, O(N * log(N)), where N is the length of `ints`

d. for `splitSum`, O(N<sup>2</sup>), where N is the length of `ints`

e. for `splitSum`, O(N<sup>3</sup>), where N is the length of `ints`

f. for `splitSum`, O(N!), where N is the length of `ints`

g. for `splitSum`, O(2<sup>N</sup>), where N is the length of `ints`

h. for `treeList`, O(log(H)), where H is the height of the tree with root `root`

i. for `treeList`, O(H), where H is the height of the tree with root `root`

j. for `treeList`, O(H * log(H)), where H is the height of the tree with root `root`

k. for `treeList`, O(H<sup>2</sup>), where H is the height of the tree with root `root`

l. for `treeList`, O(H<sup>3</sup>), where H is the height of the tree with root `root`

m. for `treeList`, O(H!), where H is the height of the tree with root `root`

n. for `treeList`, O(2<sup>H</sup>), where H is the height of the tree with root `root`

