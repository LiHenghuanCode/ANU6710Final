package Exams.HashCode.Practice2;

import java.util.Random;

public class Address {
    /**
     * The street number of the address (must be greater than zero) e.g. 108.
     */
    public int streetNumber;

    /**
     * The street name is a string of ASCII characters e.g. "North Road"
     */
    public String streetName;

    /**
     * The postcode is a number from 0 to 9999.
     */
    public int postCode;

    public Address(int streetNumber, String streetName, int postCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
    }

    /**
     * @return a hash code value for this object.
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        // FIXME complete this method
        return new Random().nextInt(2);
    }

    /**
     * @return true if this address is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        // FIXME complete this method
        return false;
    }

    @Override
    public String toString() {
        return streetNumber + " " + streetName + " " + postCode;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    public int passThroughHash() {
        return super.hashCode();
    }
}

