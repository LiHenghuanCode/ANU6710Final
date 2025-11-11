package Exams.HashCode.Practice1;

import java.util.Random;

public class Constant extends Expression {
    int value;

    public Constant(int value) {
        this.value = value;
    }
    @Override
    public int evaluate() {
        return value;
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
