package bridge.controller;

import bridge.domain.*;
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
        int retryCount = 1;
        BridgeGame bridgeGame = ExceptionRetryHandler.retryUntilValid(this::createBridgeGame);
        startRound(bridgeGame);

        while (bridgeGame.getCurrentState() == GameState.LOSE) {
            RetryOption retryOption = ExceptionRetryHandler.retryUntilValid(this::selectRetryOption);
            if (retryOption == RetryOption.QUIT) {
                break;
            }

            retryCount++;
            bridgeGame.retry();
            startRound(bridgeGame);
        }

        outputView.printResult(bridgeGame.getResults(), bridgeGame.getCurrentState(), retryCount);
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

    private RetryOption selectRetryOption() {
        String retryOption = inputView.readGameCommand();
        return RetryOption.of(retryOption);
    }
}
