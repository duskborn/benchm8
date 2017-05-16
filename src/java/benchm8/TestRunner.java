package benchm8;

import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {

    public static TestMeasure runTest(Runnable test) {
        long start = System.currentTimeMillis();
        try {
            test.run();
        } catch (Throwable t) {
            return TestMeasure.fail(t, start - System.currentTimeMillis());
        }
        return TestMeasure.success(start - System.currentTimeMillis());
    }

    public static List<TestMeasure> runTests(List<Runnable> tests) {
        return tests.stream().map(TestRunner::runTest).collect(Collectors.toList());
    }

}
