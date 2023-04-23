package org.approvaltests.combinations;

import org.approvaltests.strings.Printable;
import org.lambda.functions.Function1;
import org.lambda.functions.Function2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PrintableCombinationApprovals {
    private final Printers printers = new Printers();

    public <T> void registerPrinter(Class<T> type, Function<T, String> printer) {
        printers.add(type, printer);
    }

    public <IN1, OUT> void verifyAllCombinations(Function1<IN1, OUT> call, IN1[] parameters1) {
        CombinationApprovals.verifyAllCombinations(
            parameter -> printers.print(call.call(parameter.get())),
            Printable.create(printers::print, parameters1)
        );
    }

    public <IN1, IN2, OUT> void verifyAllCombinations(Function2<IN1, IN2, OUT> call, IN1[] parameters1, IN2[] parameters2) {
        CombinationApprovals.verifyAllCombinations(call, parameters1, parameters2);
    }

    private static class Printers {
        private final Map<Class<?>, Printer<?>> printerRegistry = new HashMap<>();

        public <T> void add(Class<T> type, Function<T, String> print) {
            printerRegistry.put(type, new Printer<>(print));
        }

        private <T> String print(T testResult) {
            if (printerRegistry.containsKey(testResult.getClass())) {
                Printer<T> printer = (Printer<T>) printerRegistry.get(testResult.getClass());
                return printer.print(testResult);
            }
            return testResult.toString();
        }
    }


    private static class Printer<T> {
        private final Function<T, String> print;

        public Printer(Function<T, String> print) {
            this.print = print;
        }

        public String print(T t) {
            return print.apply(t);
        }
    }
}
