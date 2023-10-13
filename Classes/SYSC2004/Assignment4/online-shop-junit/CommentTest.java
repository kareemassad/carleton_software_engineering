

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommentTest
{
    /**
     * Default constructor for test class CommentTest
     */
    public CommentTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testCreateComment()
    {
        Comment newComment = new Comment("Kaitlyn", "Kinda works", 4);
        assertEquals("Kaitlyn", newComment.getAuthor());
        assertEquals(4, newComment.getRating());
    }
    @Test 
    public void testUpvote()
    {
        Comment newComment = new Comment("Kaitlyn", "great", 5);
        newComment.upvote();
        newComment.upvote();
        newComment.upvote();
        assertEquals(3, newComment.getVoteCount());
    }
    public void testDownvote()
    {
        Comment newComment = new Comment("Kaitlyn", "Wasn't great", 2);
        newComment.upvote();
        newComment.upvote();
        newComment.upvote();
        newComment.downvote();
        newComment.downvote();
        assertEquals(1, newComment.getVoteCount());
    }

}

