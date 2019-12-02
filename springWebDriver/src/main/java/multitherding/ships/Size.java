package multitherding.ships.types;

public enum Size {
TEN(10),FIFTEEN(500),HUNDRED(100)
    ;
int size;
    Size(int i) {
        this.size=i;
    }

    public int getValue() {
        return size;
    }
}
