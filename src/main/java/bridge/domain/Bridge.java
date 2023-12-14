package bridge.domain;

import bridge.domain.result.RoundResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Bridge {
    private static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 20;

    private final List<Section> sections;

    private Bridge(List<Section> sections) {
        this.sections = new ArrayList<>(sections);
        validateLength();
    }

    private void validateLength() {
        if (sections.size() < MIN_LENGTH || sections.size() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 다리 길이는 %d부터 %d 사이의 숫자여야 합니다.", MIN_LENGTH, MAX_LENGTH));
        }
    }

    public static Bridge of(BridgeMaker bridgeMaker, int length) {
        return new Bridge(bridgeMaker.makeBridge(length).stream()
                .map(Section::of)
                .collect(Collectors.toList()));
    }

    public Section getNextSection(RoundResult roundResult) {
        return sections.get(roundResult.getSize());
    }

    public int getSize() {
        return sections.size();
    }

    public List<Section> getCollection() {
        return Collections.unmodifiableList(sections);
    }
}
