public class CodeTimer {
    private final long mStartTime;

    private static CodeTimer instance = new CodeTimer();

    private CodeTimer() {
        mStartTime = System.currentTimeMillis();
    }

    public long getCurrentServerTime() {
        return System.currentTimeMillis() - mStartTime;
    }

    public static final CodeTimer getInstance() {
        return instance;
    }
}