
public class FeedStubOne implements FeedOfIntValues {

    private int i = 0;
    private int[] values = { -10, -1, 0, 1, 2, 3, 4, 5, 10 };

    @Override
    public boolean hasNext() {
        return i < values.length;

    }

    @Override
    public int getNextIntValue() {
        return values[i++];
    }

}
