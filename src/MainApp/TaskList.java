package MainApp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import MainApp.Controller.*;

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
 * Responsibilities of class: represents a list of Tasks 
 * 
 */

//TODO: implement and remove all TODOs

public class TaskList {
	
	//Fields
	private ArrayList<Task> tasks;
	
	//Constructors
	/**
	 * Purpose: construct a TaskList 
	 * @return nothing
	 */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	//Methods
	/**
	 * Purpose: returns an array of the tasks in string format
	 * @return String[] tasks
	 */
	public TaskData[] getTasks() {
		TaskData[] taskDatas = new TaskData[tasks.size()];
		for(int i = 0; i < tasks.size(); i++) {
			TaskData taskData = new TaskData(
					Integer.toString(i), 
					tasks.get(i).getDescription(), 
					tasks.get(i).getDueDate(true));
 			taskDatas[i] = taskData;
		}
		return taskDatas;
	}
	
	/**
	 * Purpose: sorts the tasks by due date
	 * @return void
	 * @param ArrayList<Task>
	 */
	public List<Task> organizeTasks(List<Task> tasks) {
		if(tasks.size() <= 1) return tasks; // returns a list of size 1 when the base case is met
				
		List<Task> firstHalf = organizeTasks(tasks.subList(0, tasks.size()/2)); // creates a sorted list of the first half of the list
		List<Task> secondHalf = organizeTasks(tasks.subList(tasks.size()/2, tasks.size())); //creates a sorted list of the second half of the list
		List<Task> merged = new ArrayList<Task>(); //variable to hold merged and sorted list
		
		int firstCounter = 0;
		int secondCounter = 0;
		while(firstCounter < firstHalf.size() || secondCounter < secondHalf.size()) { // sorts and merges first and second halves
			Task first = new Task(null, LocalDate.MAX);
			Task second = new Task(null, LocalDate.MAX);
			if(firstCounter < firstHalf.size()) first = firstHalf.get(firstCounter);
			if(secondCounter < secondHalf.size()) second = secondHalf.get(secondCounter);
			if(first.getDueDate().isBefore(second.getDueDate())) {
				merged.add(first);
				firstCounter++;
			}
			else {
				merged.add(second);
				secondCounter++;
			}
		}
		
		return merged;
	}
	
	/**
	 * Purpose: marks a task as completed and removes it from the list
	 * @return void
	 * @param int taskIndex
	 */
	public void completeTask(int taskIndex) {
		Task task = tasks.get(taskIndex);
		task.setCompleted();
		tasks.remove(task); //removes the task from the list of tasks
	}
	
	/**
	 * Purpose: saves updates to the specified task 
	 * @return void
	 * @param 
	 */
	public void saveTask(TaskData taskData) {
		int index = Integer.parseInt(taskData.index());
		Task task = new Task(taskData);
		tasks.set(index, task);
	}
	
}

