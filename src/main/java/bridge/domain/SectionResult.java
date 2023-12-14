package bridge.domain;

import java.util.Arrays;

public enum SectionResult {
    SUCCESS {
        @Override
        public boolean matches(Section bridgeSection, Section userSection) {
            return bridgeSection.equals(userSection);
        }
    },
    FAIL {
        @Override
        public boolean matches(Section bridgeSection, Section userSection) {
            return !bridgeSection.equals(userSection);
        }
    };

    abstract boolean matches(Section bridgeSection, Section userSection);

    public static SectionResult getResult(Section bridgeSection, Section userSection) {
        return Arrays.stream(values())
                .filter(result -> result.matches(bridgeSection, userSection))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 나올 수 없는 SectionResult 입니다."));
    }
}
