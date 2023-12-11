
import java.time.*;


/**
 * Lead Author(s):
 * Evens Brunel
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * 
 * <<add more references here>>
 *  
 * Version/date: 12/08/23
 * 
 * Responsibilities of class: represents a task 
 * 
 */

//TODO: implement and remove all TODOs

public class Task {
	
	//Fields
	private LocalDate dueDate;
	private boolean completed;
	
	//Constructors
	/**
	 * Purpose: construct a task 
	 * @return nothing
	 */
	public Task(LocalDate dueDate) {
		if(dueDate != null) this.dueDate = dueDate;
	}
	
	//Methods
	
	/**
	 * Purpose: sets the completed field of the task to true
	 * @return void
	 */
	public void setCompleted() {
		this.completed = true;
	}
	
	/**
	 * Purpose: returns the due date 
	 * @return LocalDate: dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	/**
	 * Purpose: returns the task in string format
	 * @return String: task
	 */
	@Override
	public String toString() {
		return dueDate.toString();
	}
	
}
