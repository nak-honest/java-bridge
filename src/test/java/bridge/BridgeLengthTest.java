package bridge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BridgeLengthTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 21, 22})
    void 올바르지_않은_경우_예외를_발생시킨다(int length) {
        assertThatThrownBy(() -> new BridgeLength(length))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideValidLengths")
    void 올바른_경우_예외를_발생시키지_않는다(int length) {
        assertThatCode(() -> new BridgeLength(length))
                .doesNotThrowAnyException();
    }

    static List<Integer> provideValidLengths() {
        return IntStream.rangeClosed(3, 20)
                .boxed()
                .collect(Collectors.toList());
    }
}
