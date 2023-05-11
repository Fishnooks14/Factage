package Main;

public record DynType<T>(T value) {

    public char getIcon() {
        if (value instanceof Character) return (char) value;
        else if (value instanceof Integer) {
            if ((int) value < 10 && (int) value >= 0)
                return (char) ((int) value + '0');
            else
                return 'n';
        } else
            return '~';
    }

    public int getOperableValue() {
        if (value instanceof Character)
            return (Character) value;
        else
            return (int) (value);
    }
}
