package bridge.view;

import bridge.domain.GameState;
import bridge.domain.Section;
import bridge.domain.SectionResult;
import bridge.domain.SelectResult;

import java.util.EnumMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final EnumMap<SelectResult, String> SELECT_RESULT_STRING = new EnumMap<>(SelectResult.class);
    private static final EnumMap<GameState, String> GAME_RESULT_STRING = new EnumMap<>(GameState.class);

    static {
        SELECT_RESULT_STRING.put(SelectResult.SUCCESS, "O");
        SELECT_RESULT_STRING.put(SelectResult.FAIL, "X");
        SELECT_RESULT_STRING.put(SelectResult.NOT_SELECTED, " ");

        GAME_RESULT_STRING.put(GameState.WIN, "성공");
        GAME_RESULT_STRING.put(GameState.LOSE, "실패");
    }

    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<SectionResult> results) {
        printSection(results, Section.UP);
        printSection(results, Section.DOWN);
        writer.writeLine("");
    }

    private void printSection(List<SectionResult> results, Section printSection) {
        StringJoiner stringJoiner = new StringJoiner(" | ", "[ ", " ]");
        for (SectionResult sectionResult : results) {
            SelectResult selectResult = sectionResult.getSelectResult(printSection);

            stringJoiner.add(SELECT_RESULT_STRING.get(selectResult));
        }

        writer.writeLine(stringJoiner.toString());
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<SectionResult> results, GameState gameState, int retryCount) {
        writer.writeLine("최종 게임 결과");
        printMap(results);
        writer.writeLine(String.format("게임 성공 여부: %s", GAME_RESULT_STRING.get(gameState)));
        writer.writeLine(String.format("총 시도한 횟수: %d", retryCount));
    }
}
