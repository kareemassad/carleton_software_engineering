import java.util.List;

//as given by the prof
public abstract interface Actor {
    abstract public void act(List<Actor> newActors);
    abstract public boolean isActive();

}