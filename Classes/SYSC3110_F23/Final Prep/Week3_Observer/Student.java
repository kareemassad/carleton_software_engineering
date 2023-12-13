/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.Date;

public class Student implements MidtermHandler{
	private String name;
	private Date midterm;

	public Student(String aName) {
		this.name = aName;
	}

	public String getName() {
		return this.name;
	}

	private void study(Date date){
		this.midterm = date;
		System.out.println(name + " : Doh! I have to study hard for my midterm on " + this.midterm);
	}
	
	private void party(Date date){
		this.midterm = date;
		System.out.println(name + " : Alright! I get to party since my midterm isn't until " + this.midterm);
	}

	@Override
	public void handleMidterm(CourseEvent event) {
		Prof prof = event.getProf();
		Date date = event.getMidtermDate();
		System.out.println(prof.getName());
		this.study(date);
	}

	public void handlePostponeMidterm(CourseEvent event){
		Prof prof = event.getProf();
		Date date = event.getMidtermDate();
		System.out.println(prof.getName());
		this.party(date);
	}
}
