package bridge.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RoundResult {
    private final List<SectionResult> results;

    public RoundResult(List<SectionResult> results) {
        this.results = new ArrayList<>(results);
    }

    public RoundResult updateResult(SectionResult result) {
        List<SectionResult> newResults = new ArrayList<>(results);
        newResults.add(result);

        return new RoundResult(newResults);
    }

    public int getSize() {
        return results.size();
    }
}
