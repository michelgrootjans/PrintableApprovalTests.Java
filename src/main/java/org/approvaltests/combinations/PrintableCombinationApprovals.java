package org.approvaltests.combinations;

import org.lambda.functions.Function2;

public class PrintableCombinationApprovals {
    public <IN1, IN2, OUT> void verifyAllCombinations(Function2<IN1, IN2, OUT> call, IN1[] parameters1, IN2[] parameters2) {
        CombinationApprovals.verifyAllCombinations(call, parameters1, parameters2);
    }
}
