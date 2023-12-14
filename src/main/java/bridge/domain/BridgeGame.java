package bridge.domain;

import bridge.domain.result.RoundResult;
import bridge.domain.result.SectionResult;

import java.util.Collections;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private RoundResult roundResult = new RoundResult(Collections.emptyList());
    private int retryCount;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
        this.retryCount = 1;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public RoundResult move(Section userSection) {
        Section bridgeSection = bridge.getNextSection(roundResult);
        SectionResult sectionResult = SectionResult.getResult(bridgeSection, userSection);

        roundResult = roundResult.updateResult(sectionResult);
        return roundResult;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        roundResult = new RoundResult(Collections.emptyList());
        retryCount++;
    }

    public List<SectionResult> getResults() {
        return roundResult.getResults();
    }

    public GameState getCurrentState() {
        return GameState.getState(roundResult, bridge.getSize());
    }

    public int getRetryCount() {
        return retryCount;
    }
}
