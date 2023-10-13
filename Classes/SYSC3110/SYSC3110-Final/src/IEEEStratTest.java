import org.junit.Test;

class IEEEStratTest {

    private Article article = new Article("Kareem", "El Assad");

    @Test
    void testFormat(Article article) {
        assertEquals("K.El Assad");
    }
}