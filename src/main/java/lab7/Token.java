package lab7;

public class Token {
    private int first, second;
    private int value;

    public Token() {
    }

    public Token(int value, int first, int second) {
        validateValue(value);
        validateValue(first);
        validateValue(second);
        this.value = value;
        this.first = first;
        this.second = second;
    }

    private void validateValue(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("value cannot be less than 1");
        }
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        validateValue(first);
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        validateValue(second);
        this.second = second;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        validateValue(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (first != other.first)
            return false;
        if (second != other.second)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Token [" + value + "] (" + first + ", " + second + ")";
    }
}
