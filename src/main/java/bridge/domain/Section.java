package bridge.domain;

import java.util.Arrays;

public enum Section {
    UP("U"),
    DOWN("D");

    private String symbol;

    Section(String symbol) {
        this.symbol = symbol;
    }

    public static Section of(String symbol) {
        return Arrays.stream(values())
                .filter(course -> course.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 다리의 각 칸은 U 또는 D여야 합니다."));
    }
}
