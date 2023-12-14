package bridge.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SectionTest {
    @ParameterizedTest
    @ValueSource(strings = {"u", "d", "UD", " ", ""})
    void 각_칸이_U_또는_D가_아니라면_예외를_발생시킨다(String string) {
        assertThatThrownBy(() -> Section.of(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리의 각 칸은 U 또는 D여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"U", "D"})
    void 각_칸이_U_또는_D라면_예외를_발생시키지_않는다(String string) {
        assertThatCode(() -> Section.of(string))
                .doesNotThrowAnyException();
    }
}
