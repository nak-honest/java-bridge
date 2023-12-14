package bridge.domain;

public enum RetryOption {
    RETRY("R"),
    QUIT("Q");

    private String symbol;

    RetryOption(String symbol) {
        this.symbol = symbol;
    }

    public static RetryOption of(String symbol) {
        for (RetryOption retryOption : RetryOption.values()) {
            if (retryOption.symbol.equals(symbol)) {
                return retryOption;
            }
        }
        throw new IllegalArgumentException("[ERROR] 재시작 여부는 R 또는 Q 이어야 합니다.");
    }
}
