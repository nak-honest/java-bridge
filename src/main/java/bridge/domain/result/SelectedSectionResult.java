package bridge.domain.result;

import bridge.domain.Section;

import java.util.Arrays;

public enum SelectedSectionResult {
    SUCCESS {
        @Override
        boolean matches(SectionResult sectionResult, Section selectedSection) {
            return sectionResult.isSameSection(selectedSection) && sectionResult.isSuccess();
        }
    },
    FAIL {
        @Override
        boolean matches(SectionResult sectionResult, Section selectedSection) {
            return sectionResult.isSameSection(selectedSection) && !sectionResult.isSuccess();
        }
    },
    NOT_SELECTED {
        @Override
        boolean matches(SectionResult sectionResult, Section selectedSection) {
            return !sectionResult.isSameSection(selectedSection);
        }
    };

    abstract boolean matches(SectionResult sectionResult, Section selectedSection);

    public static SelectedSectionResult getResult(SectionResult sectionResult, Section selectedSection) {
        return Arrays.stream(values())
                .filter(selectedSectionResult -> selectedSectionResult.matches(sectionResult, selectedSection))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 나올 수 없는 GameState 입니다."));
    }
}
