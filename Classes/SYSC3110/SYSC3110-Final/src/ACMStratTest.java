import org.junit.Test;

class ACMStratTest {

    private Article article = new Article("Kareem", "El Assad");

    @Test
    void testFormat(Article article) {
        assertEquals("Kareem El Assad.");
    }
}