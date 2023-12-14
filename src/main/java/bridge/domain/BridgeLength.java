package bridge.domain;

public class BridgeLength {
    private static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 20;

    private final int length;

    public BridgeLength(int length) {
        validateLength(length);
        this.length = length;
    }

    private void validateLength(int length) {
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 다리 길이는 %d부터 %d 사이의 숫자여야 합니다.", MIN_LENGTH, MAX_LENGTH));
        }
    }
}
