## Exercise 2 - A `Bucket` class (classes and objects recap)

### Design a new class

Design a new Java class,
`Bucket`.  Each instance of this class represents a bucket that can be
filled with water.  When you create a new `Bucket` object, that new
object represents a bucket with a particular capacity, `capacity` and
certain contents, `contents`.  The units of measurement are not
important to this exercise, but you can imagine that it is litres.

### Write the constructor for the Bucket class

The constructor for a `Bucket` should take the capacity as an argument.
The constructor's signature should thus be `Bucket(double capacity)`.
You can assume that a `Bucket` is empty when it is initialised.

In your object, both `capacity` and `contents` should be stored as
`double`s.  Because `capacity` and `contents` are distinct
properties of *each object* (not properties of the class), they need to be
declared as **instance** fields, not *class* fields.
This means you don't use a `static` qualifier when you declare the field.
The `capacity` field will never change (the capacity of a bucket is
something that does not (normally) change).
Both fields should be private to the `Bucket` class.

### Add methods to the Bucket class

The `Bucket` class should have four public **instance** methods (not
*static* methods).

* The first is` getCapacity()`, which returns a `double` reflecting the
  capacity of the bucket.

* The second is `getContents()`, which returns a `double` reflecting the
  current contents of the bucket.

* The third is `empty()`, which will empty the bucket (its contents
  will now be zero), and will return the contents of the bucket before
  it was emptied (i.e. how much was emptied from the bucket).

* The fourth is `add(double amount)`, which will add the given amount
  to the bucket, and increase the contents accordingly, but it will
  never increase the contents to be more than the capacity (i.e. any
  extra water will be spilled, so the total contents can never be
  higher than the capacity). It doesn't return anything.

Do you identify any invariants for the objects of the `Bucket` class.
If yes, please document them following the design recipe.


### Create instances of type Bucket and test their methods

Write a `main` method, and in that method, declare two local variables:
`big` and `small`, both of type `Bucket`. Initialise `big` by
instantiating a new bucket with capacity 10.0. Initialise `small` by
instantiating a new bucket with capacity 1.0.

Besides:
* Use the `add()` method to add 20.0 to each bucket
* Print out the bucket contents using `getContents()`.
  You should find that both buckets are full to capacity,
  but not more.
* Use `empty()` to empty the `big` bucket, print out the bucket's
  contents, and ensure that it is in fact empty.
* Empty the `small` bucket into the `big` bucket.
  Print out the contents of each of them and ensure that the
  `small` bucket is empty and the `big` bucket has 1.0 in it.

Some JUnit tests for the `Bucket` class are as follows:

```java
public class BucketTest {

    @Test
    public void testCreationZero() {
        Bucket b = new Bucket(0.0);
        assertEquals(0.0, b.getCapacity(), "After creating Bucket of capacity zero, b.getCapacity() returned incorrect value");
        assertEquals(0.0, b.getContents(), "After creating Bucket of capacity zero, b.getContents() returned incorrect value");
    }

    @Test
    public void testCreationNonZero() {
        Bucket b1 = new Bucket(10);
        Bucket b2 = new Bucket(1);

        assertEquals(10.0, b1.getCapacity(), "After creating Bucket b1 of capacity 10, b1.getCapacity() returned incorrect value");
        assertEquals(0.0, b1.getContents(), "After creating Bucket b1 of capacity 10, b1.getContents() returned incorrect value");
        assertEquals(1.0, b2.getCapacity(), "After creating Bucket b2 of capacity 1, b2.getCapacity() returned incorrect value");
        assertEquals(0.0, b2.getContents(), "After creating Bucket b2 of capacity 1, b2.getContents() returned incorrect value");
    }

    @Test
    public void testContentsOverflow() {
        Bucket b1 = new Bucket(10.0);
        Bucket b2 = new Bucket(1.0);

        b1.add(20.0);
        b2.add(20.0);
        double contents = b1.getContents();
        assertEquals(10.0, contents, "Bucket contents after overflow should be equal to capacity");
        contents = b2.getContents();
        assertEquals(1.0, contents, "Bucket contents after overflow should be equal to capacity");
    }

    @Test
    public void testContentsCumulative() {
        Bucket b1 = new Bucket(10.0);
        Bucket b2 = new Bucket(1.0);

        double c1 = 0.0;
        double c2 = 0.0;
        for (int i = 0; i < 30; i++) {
            b1.add(0.5);
            c1 += 0.5;
            if (c1 > 10) c1 = 10;
            b2.add(0.5);
            c2 += 0.5;
            if (c2 > 1.0) c2 = 1;
            assertEquals(c1, b1.getContents(), "Bucket b1.getContents()");
            assertEquals(c2, b2.getContents(), "Bucket b2.getContents()");
        }
    }

    @Test
    public void testEmpty() {
        Bucket b1 = new Bucket(10);
        Bucket b2 = new Bucket(1);

        b1.add(4.0);
        b2.add(2.0);

        assertEquals(4.0, b1.empty(), "Given Bucket b of capacity 10, after calling b.add(4), b.empty() returned incorrect value");
        assertEquals(0.0, b1.getContents(), "Given Bucket b of capacity 10, after calling b.empty(), b.getContents() returned incorrect value");
        assertEquals(1.0, b2.empty(), "Given Bucket b2 of capacity 1, after calling b2.add(2), b2.empty() returned incorrect value");
        assertEquals(0.0, b2.getContents(), "Given Bucket b2 of capacity 1, after calling b2.empty(), b2.getContents() returned incorrect value");
    }
}
```

## Exercise 3 - Stackable bucket (class inheritance)

Design a new class `StackableBucket` that `extend`s the `Bucket` class.

Add a new instance field `innerBucket` of type `StackableBucket`.
(Imagine you want to put a smaller bucket into a larger bucket.)
Don't include this as an argument in the constructor.
Set its default value to `null`.

Add the constructor `StackableBucket(double capacity, String name)`.
`name` should be used to set a new instance field of `StackableBucket`,
while `capacity` should be used to construct the parent `Bucket`
class instance.

Add an instance method `getInnerBucketName()` that will return the name
of the `innerBucket`, or `null` is there is currently no inner bucket.

Add a new public instance method
`setInnerBucket(StackableBucket smallerBucket)` which should:

* Print `"Too large to stack!"` on screen if the `smallerBucket` has a larger
  or equal capacity than the bucket. In this case, the method should not touch the state
  of the bucket at all.
* Set the `smallerBucket` as the `innerBucket` of your bucket, if your
  bucket has a larger capacity **and** it does not currently have a
  `innerBucket`.
* If the bucket already has an `innerBucket`, it should try to stack `smallerBucket`
  into the `innerBucket`.

Add a main method to the `StackableBucket` class. The method should
create three instances of `StackableBucket`: `big` with capacity `10.0`, `small` with
capacity `1.0` and `medium` with capacity `5.0`. The names passed to the
`StackableBucket` constructor should be `"big"`, `"small"`, and `"medium"`,
respectively.

Besides the method should:
* Use `setInnerBucket` to stack the `big` bucket into the `medium`
  bucket. If you run this, your code should print `"Too large to stack!"`.
* Use `setInnerBucket` to stack the `medium` bucket into the `big`
  bucket. Print the result of `getInnerBucketName()` of the `big` bucket. This
  should return the `String` `"medium"`.
* Use `setInnerBucket` to stack the `small` bucket into the `big`
  bucket. Print the results of `getInnerBucketName()` of the `big` and `medium`
  buckets. The small bucket should be stacked in the medium bucket,
  which is stacked in the big bucket.

Add a new public instance method `unstackBuckets()` to `StackableBucket`.
This method should remove all buckets from inside the bucket, and unstack them from
each other. For example, if you run this method on the `big` bucket, the big and
medium buckets should now have no innerBucket.

Following the design recipe, write tests for the `StackableBucket` class.

## Exercise 4 -  Sorting stackable buckets

Make your `StackableBucket` class implement the
[`Comparable` interface](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/lang/Comparable.html).
This is a **generic** interface, meaning that it is parameterised with a
type `T`. The specific instance of the interface you should implement
is `Comparable<StackableBucket>`, so that your stackable buckets are
comparable (orderable) with other stackable buckets (but not with objects
of any type). To implement this interface, your class must have (override) the method
`int compareTo(StackableBucket otherBucket)`, which returns a
negative value if `this` bucket is "smaller" than `otherBucket`, a
positive value if `otherBucket` is "smaller" than `this`, and zero if
they are equal. The `compareTo` method should use the `capacity` of the
bucket to compare the buckets.

After you have implemented this interface, create an array of stackable
buckets of mixed capacities in the `main` method.
Then sort the array using the (static) function
`java.util.Arrays.sort`.

