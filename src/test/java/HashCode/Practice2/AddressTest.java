package HashCode.Practice2;
import Exams.HashCode.Practice2.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class AddressTest {
    public static final int HASH_ITERATIONS = 100;

    static Address[] addresses = new Address[200 * HASH_ITERATIONS];

    @Test
    public void testHashCodeDeterministic() {
        checkNotObjectHashCode();
        for (Address addr : addresses) {
            int hash = addr.hashCode();
            for (int i = 0; i < 10; i++) {
                int hash2 = addr.hashCode();
                assertTrue(hash == hash2, "address " + addr + "  returned different values for hashCode(): " + hash + ", " + hash2);
            }
        }
    }

    @Test
    public void testAllFields() {
        checkNotObjectHashCode();
        Address addr1 = new Address(108, "Main St", 2601);
        Address addr2 = new Address(108, "Main St", 2903);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Address(2601, "Main St", 108);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Address(109, "Main St", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Address(108, "North Road", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Address(108, "Stain M", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
    }

    @Test
    public void testEquals() {
        Address addr1 = new Address(108, "Main St", 2601);
        assertTrue(addr1.equals(addr1), "addr1.equals(addr1) returned false");
        Address addr2 = new Address(108, "Main St", 2903);
        assertFalse(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true");
        addr2.streetNumber = 109;
        addr2.postCode = 2601;
        assertFalse(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true");
        addr2.streetNumber = 108;
        addr2.streetName = "Stain M";
        assertFalse(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true");
        addr2.streetName = null;
        assertFalse(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true");
        addr1.streetName = null;
        assertTrue(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are equal, but addr1.equals(addr2) returned false");
        addr2.streetName = "Main St";
        assertFalse(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true");
        addr1.streetName = "Main St";
        assertTrue(addr1.equals(addr2), "addresses " + addr1 + " and " + addr2 + " are equal, but addr1.equals(addr2) returned false");
    }

    @Test
    public void testUniformA() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=10-1, prob=0.999) ~= 27.88
        testUniformity(10, 27.88);
    }

    @Test
    public void testUniformB() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=50-1, prob=0.999) ~= 85.35
        testUniformity(50, 85.35);
    }

    private void testUniformity(int buckets, double chiSqCriticalValue) {
        Random r = new Random();
        int[] count = new int[buckets];
        int samples = buckets * HASH_ITERATIONS;
        for (int i = 0; i < samples; i++) {
            Address addr = addresses[r.nextInt(addresses.length)];
            int h = Math.abs(addr.hashCode()) % buckets;
            count[h]++;
        }

        double chiSq = chiSquaredUniform(samples, count);
        assertTrue(chiSq < chiSqCriticalValue, "Distribution of hash function doesn't appear to be uniform over " + buckets + " buckets (chi squared value of " + chiSq + ").\nExpected " + samples / buckets + " elements per bucket, but got " + Arrays.toString(count));
    }

    private void checkNotObjectHashCode() {
        Random r = new Random();
        int range = 39;
        Consumer<Function<Address, Integer>> checkHash = (Function<Address, Integer> hashFunction) -> {
            Set<Address>[] myBuckets = new Set[range];
            Set<Address>[] defaultBuckets = new Set[range];
            for (int i = 0; i < range; i++) {
                myBuckets[i] = new HashSet<>();
                defaultBuckets[i] = new HashSet<>();
            }
            for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
                Address addr = addresses[r.nextInt(addresses.length)];
                int m = Math.abs(addr.hashCode()) % range;
                myBuckets[m].add(addr);
                int n = Math.abs(hashFunction.apply(addr)) % range;
                defaultBuckets[n].add(addr);
            }
            for (Set<Address> myBucket : myBuckets) {
                for (Set<Address> defaultBucket : defaultBuckets) {
                    assertFalse(myBucket.equals(defaultBucket), "It looks like you're using Object.hashCode() or Objects.hash()!");
                }
            }
        };

        checkHash.accept((Address addr) -> addr.passThroughHash());
        checkHash.accept((Address addr) -> Objects.hash(addr.streetNumber, addr.streetName, addr.postCode));
        checkHash.accept((Address addr) -> Objects.hash(addr.streetNumber, addr.postCode, addr.streetName));
        checkHash.accept((Address addr) -> Objects.hash(addr.streetName, addr.postCode, addr.streetNumber));
        checkHash.accept((Address addr) -> Objects.hash(addr.streetName, addr.streetNumber, addr.postCode));
        checkHash.accept((Address addr) -> Objects.hash(addr.postCode, addr.streetNumber, addr.streetName));
        checkHash.accept((Address addr) -> Objects.hash(addr.postCode, addr.streetName, addr.streetNumber));
    }

    private void testDifferent(Address addr1, Address addr2) {
        int hash1 = addr1.hashCode();
        int hash2 = addr2.hashCode();
        assertTrue(hash1 != hash2, "addresses " + addr1 + " and " + addr2 + " returned same hashCode(): " + hash1 + ", " + hash2);
    }

    @BeforeAll
    public static void generateAddresses() {
        Random r = new Random(0);
        for (int i = 0; i < addresses.length; i++) {
            char[] randomName = new char[r.nextInt(5) + 3];
            for (int c = 0; c < randomName.length; c++) {
                if (c == 0) {
                    randomName[c] = (char) (r.nextInt(90 - 65) + 'A');
                } else {
                    randomName[c] = (char) (r.nextInt(123 - 97) + 'a');
                }
            }

            addresses[i] = new Address(r.nextInt(10), String.valueOf(randomName), r.nextInt(100));
            System.out.println(addresses[i]);
        }
    }

    private static double chiSquaredUniform(int samples, int[] counts) {
        double uniformProb = 1.0 / counts.length;
        double total = 0;
        for (int count : counts) {
            double mi = ((double) samples) * uniformProb;
            total += ((double) count - mi) * ((double) count - mi) / mi;
        }
        return total;
    }
}
