package MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.*;
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
 * Responsibilities of class: draws the GUI
 * 
 */

//TODO: implement and remove all TODOs

public class View {
	
	//Fields
	private JFrame frame;
	private JPanel panel;
	private Controller controller;
	
	//Constructors
	/**
	 * Purpose: construct a view  
	 * @return nothing
	 */
	public View(Controller controller) {
		frame = new JFrame();
		panel = new JPanel();
		this.controller = controller;
		new JScrollPane(panel);
	}
	
	//Methods
	/**
	 * Purpose: draws the frame
	 * @return void
	 */
	public void drawFrame() {
		frame.setSize(1200, 675);
		panel.setSize(800, 450);
		panel.setBorder(new LineBorder(Color.gray, 3));
		frame.add(panel);
		frame.setVisible(true);;
	}
	
	/**
	 * Purpose: draws the frame
	 * @return void
	 */
	public void drawTaskList(TaskData[] tasks) {
		
		panel.removeAll();
		for(TaskData task: tasks) drawTask(task, panel);
		panel.revalidate();
		panel.repaint();
		
	}
	
	/**
	 * Purpose: draws a task on a panel
	 * @return void
	 * @param Task task
	 * @param JPanel panel
	 */
	public void drawTask(TaskData taskData, JPanel panel) {
		
		TaskPanel taskPanel = new TaskPanel(taskData);
		taskPanel.setSize(800, 200);
		taskPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(taskPanel);
		
	}
	
	/**
	 * Purpose: draws a date chooser on a panel
	 * @return void
	 * @param Task task
	 * @param JPanel panel
	 */
	public JPanel drawDateChooser() {

		JPanel dateChooser = new JPanel();
		dateChooser.add(new JLabel("Day: "));
		Integer[] days = new Integer[31];
		for(int i = 0; i < 31; i++) { days[i] = i+1;}
		dateChooser.add(new JComboBox(days));
		dateChooser.add(new JLabel("Month: "));
		Integer[] months = new Integer[12];
		for(int i = 0; i < 12; i++) { months[i] = i+1;}
		dateChooser.add(new JComboBox(months));
		dateChooser.add(new JLabel("Year: "));
		Integer[] years = new Integer[100];
		for(int i = 0; i < 100; i++) { years[i] = i+2000;}
		dateChooser.add(new JComboBox(years));
		return dateChooser;
		
	}
	
	//Internal Classes
	/**
	 * Responsibilities of class: hold information about the panel the button
	 */
	private class TaskButton extends JButton{
		
		//fields
		private TaskPanel panel;
		
		//constructors
		public TaskButton(String label, TaskPanel panel) {
			super(label);
			this.panel = panel;
			switch(label) {
				case "Complete Task":
					this.addActionListener(new CompleteTaskListener());
					break;
				case "Save Changes":
					this.addActionListener(new SaveTaskListener());
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Responsibilities of class: listen for a complete task button click
	 */
	private class CompleteTaskListener implements ActionListener {
		
		/**
		 * Purpose: completes a task when a complete task button is clicked
		 * @return void
		 * @param ActionEvent: e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			TaskButton button = (TaskButton)e.getSource();
			controller.completeTask(Integer.parseInt(button.panel.data.index()));
		}
		
	}
	
	/**
	 * Responsibilities of class: listen for a complete task button click
	 */
	private class SaveTaskListener implements ActionListener {
		
		/**
		 * Purpose: completes a task when a complete task button is clicked
		 * @return void
		 * @param ActionEvent: e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			TaskButton button = (TaskButton)e.getSource();
			controller.saveTask(button.panel.getNewData());
		}
		
	}
	
	/**
	 * Responsibilities of class: hold information about a task
	 */
	private class TaskPanel extends JPanel{
		
		//fields
		private TaskData data;
		private String description;
		private String dueDate;
		private NewDatePanel newDate;
		
		//constructors
		public TaskPanel(TaskData taskData) {
			this.data = taskData;
			this.description = data.description();
			this.dueDate = data.dueDate();
			this.newDate = new NewDatePanel(data.dueDate());
			this.add(new JLabel("Description: " + description));
			this.add(new JLabel("Due Date: " + dueDate));
			this.add(newDate);
			this.add(new TaskButton("Complete Task", this));
			this.add(new TaskButton("Save Changes", this));
		}
		
		//methods
				/**
				 * Purpose: returns the updated values in the panel as a TaskData instance
				 * @return TaskData newData
				 */
				public TaskData getNewData() {
					TaskData newData = new TaskData(data.index(), description, newDate.getNewDate());
					return newData;
				}
		
	}
	
	/**
	 * Responsibilities of class: hold user input for new due date
	 */
	private class NewDatePanel extends JPanel{
		
		//fields
		private JComboBox days;
		private JComboBox months;
		private JComboBox years;
		
		//constructors
		public NewDatePanel(String dueDate) {
			String[] date = dueDate.split("-");
			this.add(new JLabel("Day: "));
			Integer[] days = new Integer[31];
			for(int i = 0; i < 31; i++) { days[i] = i+1;}
			this.days = new JComboBox(days);
			this.days.setSelectedItem(Integer.parseInt(date[2]));
			this.add(this.days);
			this.add(new JLabel("Month: "));
			Integer[] months = new Integer[12];
			for(int i = 0; i < 12; i++) { months[i] = i+1;}
			this.months = new JComboBox(months);
			this.months.setSelectedItem(Integer.parseInt(date[1]));
			this.add(this.months);
			this.add(new JLabel("Year: "));
			Integer[] years = new Integer[100];
			for(int i = 0; i < 100; i++) { years[i] = i+2000;}
			this.years = new JComboBox(years);
			this.years.setSelectedItem(Integer.parseInt(date[0]));
			this.add(this.years);
		}
		
		//methods
		/**
		 * Purpose: returns the values in the date choosers combo boxes in string format
		 * @return String newDueDate
		 */
		public String getNewDate() {
			
//			"2007-12-03", 
			int year = (Integer)years.getSelectedItem();
			int month = (Integer)months.getSelectedItem();
			int day = (Integer)days.getSelectedItem();
			LocalDate newDate = LocalDate.of(year, month, day);
			return newDate.toString();
			
		}
		
	}
	
}