package benchm8;

public class TestMeasure {

    private final Throwable exception;
    private final long elapsedTimeMillis;

    private TestMeasure(Throwable exception, long elapsedTimeMillis) {
        this.exception = exception;
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public boolean isSuccessful() {
        return exception == null;
    }

    public double getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    public Throwable getException() {
        return exception;
    }

    public static TestMeasure fail(Throwable t, long elapsedTimeMillis) {
        return new TestMeasure(t, elapsedTimeMillis);
    }

    public static TestMeasure success(long elapsedTimeMillis) {
        return new TestMeasure(null, elapsedTimeMillis);
    }

}
