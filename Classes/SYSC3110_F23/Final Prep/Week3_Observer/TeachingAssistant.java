/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.Date;

public class TeachingAssistant implements MidtermHandler{
	private String name;
	private Date midterm;

	public TeachingAssistant(String aName) {
		this.name = aName;
	}

	public String getName() {
		return this.name;
	}

	private void proctor(Date date){
		this.midterm = date;
		System.out.println(name + " : I have to proctor a midterm on " + this.midterm);
	}
	
	private void postpone(Date date){
		this.midterm = date;
		System.out.println(name + " : Now the midterm is on " + this.midterm);
	}

	@Override
	public void handleMidterm(CourseEvent event) {
		Prof prof = event.getProf();
		Date date = event.getMidtermDate();
		System.out.println(prof.getName());
		this.proctor(date);
	}

	@Override
	public void handlePostponeMidterm(CourseEvent event) {
		Prof prof = event.getProf();
		Date date = event.getMidtermDate();
		System.out.println(prof.getName());
		this.postpone(date);
	}
}
