package bridge.domain;

import bridge.BridgeRandomNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BridgeMakerTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void 다리의_길이가_음수인_경우_예외를_발생시킨다(int size) {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

        // when & then
        assertThatThrownBy(() -> bridgeMaker.makeBridge(size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리 길이는 음수가 될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 다리의_길이가_음수가_아닌_경우_예외를_발생시키지_않는다(int size) {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

        // when & then
        assertThatCode(() -> bridgeMaker.makeBridge(size))
                .doesNotThrowAnyException();
    }

    @Test
    void 주어진_생성기에_따라_다리를_생성할_수_있다() {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(() -> 1);

        // when
        List<String> bridge = bridgeMaker.makeBridge(3);

        // then
        assertThat(bridge).containsExactly("U", "U", "U");
    }
}
