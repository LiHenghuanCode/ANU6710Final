package Exams.HashCode.Practice1;

public enum OperatorType {
    SUM, PRODUCT;

    public String toString() {
        switch (this) {
            case SUM:
                return "+";
            case PRODUCT:
                return "*";
        }
        return "?";
    }
}
