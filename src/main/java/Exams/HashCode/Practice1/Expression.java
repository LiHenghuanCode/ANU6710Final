package Exams.HashCode.Practice1;
import java.util.Random;
/**
 * The class Expression represents an arithmethic expression, made
 * up of integer constants and two binary operators (namely, + and *). The
 * class is abstract: any Expression instance must be an instance
 * of one of the two subclasses Constant or Operator, defined below.
 *
 * You must implement the `equals` and `hashCode` methods of the two
 * subclasses.
 *
 * Two Expressions are equal if they represent the exact same expression,
 * including ordering and grouping of terms. For example, 1 + 2 and 2 + 1
 * are not considered equal, even if they evaluate to the same value.
 *
 * Your hash functions do not have to be perfect, but they should be
 * non-trivial (hashing a collection of different Expressions should
 * yield a number of different hash values, although not necessarily
 * as many as the number of Expressions hashed).
 *
 * Remember that two objects that are equal must have the same hash
 * value.
 */
public abstract class Expression {
    /*
     * Returns the value of the Expression.
     */
    public abstract int evaluate();
}

