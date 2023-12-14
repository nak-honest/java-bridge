package bridge.domain;

import bridge.domain.result.RoundResult;

import java.util.Arrays;

public enum GameState {
    CONTINUE {
        @Override
        boolean matches(RoundResult roundResult, int bridgeSize) {
            return roundResult.isLastSuccess() && !roundResult.isSameSize(bridgeSize);
        }
    },
    WIN {
        @Override
        boolean matches(RoundResult roundResult, int bridgeSize) {
            return roundResult.isLastSuccess() && roundResult.isSameSize(bridgeSize);
        }
    },
    LOSE {
        @Override
        boolean matches(RoundResult roundResult, int bridgeSize) {
            return !roundResult.isLastSuccess();
        }
    };

    abstract boolean matches(RoundResult roundResult, int bridgeSize);

    public static GameState getState(RoundResult roundResult, int bridgeSize) {
        return Arrays.stream(values())
                .filter(state -> state.matches(roundResult, bridgeSize))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 나올 수 없는 GameState 입니다."));
    }
}
