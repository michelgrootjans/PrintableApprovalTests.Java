package org.approvaltests.combinations;

import org.lambda.functions.Function1;
import org.lambda.functions.Function2;

public class PrintableCombinationApprovals {
    public <IN1, OUT> void verifyAllCombinations(Function1<IN1, OUT> call, IN1[] parameters1) {
        CombinationApprovals.verifyAllCombinations(call, parameters1);
    }

    public <IN1, IN2, OUT> void verifyAllCombinations(Function2<IN1, IN2, OUT> call, IN1[] parameters1, IN2[] parameters2) {
        CombinationApprovals.verifyAllCombinations(call, parameters1, parameters2);
    }
}
