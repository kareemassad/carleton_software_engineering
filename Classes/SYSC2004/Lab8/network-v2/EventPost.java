/**
 * @author Kareem El Assad
 * @version 101107739 
 */

public class EventPost extends Post{ 

    private String eventType;

    public EventPost(String author, String eventType) {
        super(author);
        this.eventType = eventType;
    }
    //mutator
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    //accessor
    public String getEventType() {
        return eventType;
    }

    public String toString(Post post) {
        return post.toString();
    }

}