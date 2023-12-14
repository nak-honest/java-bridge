package bridge.domain;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BridgeTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 21, 22})
    void 다리의_길이가_3부터_20_사이의_숫자가_아닌_경우_예외를_발생시킨다(int length) {
        // given
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

        // when & then
        assertThatThrownBy(() -> Bridge.of(bridgeMaker, length))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideValidLengths")
    void 다리의_길이가_3부터_20_사이의_숫자인_경우_예외를_발생시키지_않는다(int length) {
        assertThatCode(() -> Bridge.of(new BridgeMaker(new BridgeRandomNumberGenerator()), length))
                .doesNotThrowAnyException();
    }

    static List<Integer> provideValidLengths() {
        return IntStream.rangeClosed(3, 20)
                .boxed()
                .collect(Collectors.toList());
    }
}
