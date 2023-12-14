package bridge;

import bridge.controller.GameController;
import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.view.Writer;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        Writer writer = new Writer(System.out::print, System.out::println);
        InputView inputView = new InputView(Console::readLine, writer);
        OutputView outputView = new OutputView(writer);
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

        GameController controller = new GameController(inputView, outputView, bridgeMaker);
        controller.startGame();
    }
}
