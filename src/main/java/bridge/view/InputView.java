package bridge.view;

import java.util.function.Supplier;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    private final Supplier<String> reader;
    private final Writer writer;

    public InputView(Supplier<String> reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        String input = reader.get();
        try {
            int size = Integer.parseInt(input);
            writer.writeLine("");

            return size;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 다리 길이는 숫자여야 합니다.");
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return null;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }
}
