package bridge.domain.result;

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

    public boolean isLastSuccess() {
        SectionResult lastResult = results.get(results.size() - 1);
        return lastResult.isSuccess();
    }

    public boolean isSameSize(int bridgeSize) {
        return results.size() == bridgeSize;
    }

    public List<SectionResult> getResults() {
        return Collections.unmodifiableList(results);
    }
}
