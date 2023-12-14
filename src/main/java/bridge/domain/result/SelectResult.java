package bridge.domain.result;

import bridge.domain.GameState;
import bridge.domain.Section;

import java.util.Arrays;

public enum SelectResult {
    SUCCESS {
        @Override
        boolean matches(SectionResult sectionResult, Section selectSection) {
            return sectionResult.isSameSection(selectSection) && sectionResult.isSuccess();
        }
    }, FAIL {
        @Override
        boolean matches(SectionResult sectionResult, Section selectSection) {
            return sectionResult.isSameSection(selectSection) && !sectionResult.isSuccess();
        }
    }, NOT_SELECTED {
        @Override
        boolean matches(SectionResult sectionResult, Section selectSection) {
            return !sectionResult.isSameSection(selectSection);
        }
    };

    abstract boolean matches(SectionResult sectionResult, Section selectSection);

    public static SelectResult getResult(SectionResult sectionResult, Section selectSection) {
        return Arrays.stream(values())
                .filter(selectResult -> selectResult.matches(sectionResult, selectSection))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 나올 수 없는 GameState 입니다."));
    }
}
