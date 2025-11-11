package Exams.HashCode.Practice1;

import java.util.Random;

public class Operator extends Expression {
    OperatorType type;
    Expression left;
    Expression right;

    public Operator(OperatorType type, Expression left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }
    @Override
    public int evaluate() {
        int lv = left.evaluate();
        int rv = right.evaluate();
        switch (type) {
            case SUM:
                return lv + rv;
            case PRODUCT:
                return lv * rv;
        }
        return 0;
    }
    @Override
    public boolean equals(Object other) {
        // FIXME
        return false;
    }
    @Override
    public int hashCode() {
        // FIXME
        return new Random().nextInt(2);
    }
}
