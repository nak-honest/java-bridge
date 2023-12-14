package bridge.controller;

import bridge.domain.*;
import bridge.view.InputView;
import bridge.view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public GameController(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void startGame() {
        BridgeGame bridgeGame = createBridgeGame();
        startRound(bridgeGame);

        while (bridgeGame.getCurrentState() == GameState.LOSE) {
            RetryOption retryOption = RetryOption.of(inputView.readGameCommand());
            if (retryOption == RetryOption.QUIT) {
                break;
            }

            bridgeGame.retry();
            startRound(bridgeGame);
        }
    }

    private BridgeGame createBridgeGame() {
        int bridgeSize = inputView.readBridgeSize();
        return new BridgeGame(Bridge.of(bridgeMaker, bridgeSize));
    }

    private void startRound(BridgeGame bridgeGame) {
        do {
            processRound(bridgeGame);
        } while (bridgeGame.getCurrentState() == GameState.CONTINUE);
    }

    private void processRound(BridgeGame bridgeGame) {
        String userSection = inputView.readMoving();
        RoundResult roundResult = bridgeGame.move(Section.of(userSection));
        outputView.printMap(roundResult.getResults());
    }
}
