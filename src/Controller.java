
import java.io.*;
import java.time.*;
import java.util.*;

/**
 * Lead Author(s):
 * Evens Brunel
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * <<add more references here>>
 *  
 * Version/date: 12/08/23
 * 
 * Responsibilities of class: Run the PlannerApp Program
 * 
 */

//TODO: implement and remove all TODOs

public class Controller {
	
	//Fields
	private static TaskList taskList;
	private static View view;
	private static File data;
	
	/*Temporary*/
	private static ArrayList<Task> tasks = new ArrayList<Task>();
	/*Temporary*/
	
	//Constructors
	public Controller() {
		for(int i = 0; i<10; i++) tasks.add(new Task(LocalDate.now()));
		this.taskList = new TaskList(tasks);
		this.view = new View(this);
		this.data = new File("src/PlannerAppData");
	}
	
	//Methods
	public static void main(String[] args) {
		
		Controller app = new Controller();
		
		//draws the GUI
		app.view.drawFrame();
		app.view.drawTaskList(taskList.getTasks());
		
	}
	
	/**
	 * Purpose: completes and deletes a task from the task list then repaints the GUI
	 * @return void
	 * @param int: taskIndex
	 */
	public static void completeTask(int taskIndex) {
		
		taskList.completeTask(taskIndex);
		repaintGUI();
		
	}
	
	public static void repaintGUI() {
		
		view.drawTaskList(taskList.getTasks());
		
	}
	
	
}
