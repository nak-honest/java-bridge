package bridge.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetryOptionTest {
    @ParameterizedTest
    @ValueSource(strings = {"r", "q", "RQ", " ", ""})
    void 재시도_여부가_R_또는_Q가_아니라면_예외를_발생시킨다(String string) {
        assertThatThrownBy(() -> RetryOption.of(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 재시작 여부는 R 또는 Q 이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void 재시도_여부가_R_또는_Q라면_예외를_발생시키지_않는다(String string) {
        assertThatCode(() -> RetryOption.of(string))
                .doesNotThrowAnyException();
    }
}
