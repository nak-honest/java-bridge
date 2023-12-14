package bridge.domain.result;

import bridge.domain.Section;

import java.util.Set;

public class SectionResult {
    private static final Set<SectionResult> results = Set.of(
            new SectionResult(Section.UP, true),
            new SectionResult(Section.UP, false),
            new SectionResult(Section.DOWN, true),
            new SectionResult(Section.DOWN, false)
    );

    private final Section userSection;
    private final boolean isSuccess;

    private SectionResult(Section userSection, boolean isSuccess) {
        this.userSection = userSection;
        this.isSuccess = isSuccess;
    }

    public static SectionResult getResult(Section bridgeSection, Section userSection) {
        boolean isSuccess = bridgeSection.equals(userSection);
        return results.stream()
                .filter(result -> result.userSection.equals(userSection) && result.isSuccess == isSuccess)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 나올 수 없는 SectionResult 입니다."));
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public SelectResult getSelectResult(Section section) {
        if (section.equals(userSection)) {
            return getSelectResultWhenSelected();
        }

        return SelectResult.NOT_SELECTED;
    }

    private SelectResult getSelectResultWhenSelected() {
        if (isSuccess) {
            return SelectResult.SUCCESS;
        }

        return SelectResult.FAIL;
    }
}
