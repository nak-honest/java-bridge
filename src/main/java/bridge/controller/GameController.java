package bridge.controller;

import bridge.domain.*;
import bridge.domain.result.RoundResult;
import bridge.util.ExceptionRetryHandler;
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
        BridgeGame bridgeGame = ExceptionRetryHandler.retryUntilValid(this::createBridgeGame);
        startRound(bridgeGame);

        retryGame(bridgeGame);

        outputView.printResult(bridgeGame.getResults(), bridgeGame.getCurrentState(), bridgeGame.getRetryCount());
    }

    private BridgeGame createBridgeGame() {
        int bridgeSize = inputView.readBridgeSize();
        return new BridgeGame(Bridge.of(bridgeMaker, bridgeSize));
    }

    private void startRound(BridgeGame bridgeGame) {
        do {
            ExceptionRetryHandler.retryUntilValid(this::processRound, bridgeGame);
        } while (bridgeGame.getCurrentState() == GameState.CONTINUE);
    }

    private void processRound(BridgeGame bridgeGame) {
        String userSection = inputView.readMoving();
        RoundResult roundResult = bridgeGame.move(Section.of(userSection));
        outputView.printMap(roundResult.getResults());
    }

    private void retryGame(BridgeGame bridgeGame) {
        while (bridgeGame.getCurrentState() == GameState.LOSE) {
            RetryOption retryOption = ExceptionRetryHandler.retryUntilValid(this::selectRetryOption);
            if (retryOption == RetryOption.QUIT) {
                return;
            }

            bridgeGame.retry();
            startRound(bridgeGame);
        }
    }

    private RetryOption selectRetryOption() {
        String retryOption = inputView.readGameCommand();
        return RetryOption.of(retryOption);
    }
}
