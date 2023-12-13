import java.util.Date;
import java.util.EventObject;

public class CourseEvent extends EventObject {
    private final Date midtermDate;

    public CourseEvent(Date md, Prof prof){
        super(prof);
        this.midtermDate = md;
    }

    public Date getMidtermDate(){
        return this.midtermDate;
    }

   public Prof getProf(){
        return (Prof) this.getSource();
    }



}
