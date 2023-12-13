/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.ArrayList;
import java.util.Date;

public class Prof {
	private String name;
	private Date midtermDate;
	private ArrayList<MidtermHandler> midtermHandlers;

	public Prof(String aName) {
		this.name = aName;
		this.midtermHandlers = new ArrayList<MidtermHandler>();
	}

	public Date getMidterm() {
		return this.midtermDate;
	}

	public String getName() {
		return this.name;
	}

	public void setMidterm(Date date, Prof prof) {
		this.midtermDate = date;
        CourseEvent event = new CourseEvent(this.midtermDate, prof);
		for(MidtermHandler s: this.midtermHandlers){
			s.handleMidterm(event);
		}
	}
	
	public void postponeMidterm(Date date, Prof prof){
		this.midtermDate = date;
        CourseEvent event = new CourseEvent(date, prof);
		for(MidtermHandler s: this.midtermHandlers){
			s.handlePostponeMidterm(event);
		}
	}

	public void addMidtermHandler(MidtermHandler mh){
		this.midtermHandlers.add(mh);
	}


	public static void main(String[] args) {

		Prof p = new Prof("Babak");
		Student s = new Student("Homer");
		Student s2 = new Student("Bart");
		TeachingAssistant ta = new TeachingAssistant("Michael");
	
	
		p.addMidtermHandler(s);
		p.addMidtermHandler(s2);
		p.addMidtermHandler(ta);
	
		Date midterm = new Date();
		p.setMidterm(midterm, p);
	
		p.postponeMidterm(new Date(midterm.getTime() + 1000000000), p);
	}

}
