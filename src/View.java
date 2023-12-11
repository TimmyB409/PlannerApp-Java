
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

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
	public void drawTaskList(String[] tasks) {
		
		panel.removeAll();
		for(String task: tasks) drawTask(task, panel);
		panel.revalidate();
		panel.repaint();
		
	}
	/**
	 * Purpose: draws a task on a panel
	 * @return void
	 * @param Task task
	 * @param JPanel panel
	 */
	public void drawTask(String taskData, JPanel panel) {
		
		TaskPanel taskPanel = new TaskPanel(taskData);
		taskPanel.setSize(800, 200);
		taskPanel.setBackground(Color.LIGHT_GRAY);
		taskPanel.add(new JLabel("Due Date: " + taskPanel.dueDate));
		taskPanel.add(new TaskButton("CompleteTask", taskPanel.index));
		panel.add(taskPanel);
		
	}
	
	
	//Internal Classes
	/**
	 * Responsibilities of class: hold information about the panel the button
	 */
	private class TaskButton extends JButton{
		
		//fields
		private String index;
		
		//constructors
		public TaskButton(String label, String index) {
			super(label);
			this.index = index;
			this.addActionListener(new CompleteTaskListener());
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
			controller.completeTask(Integer.parseInt(button.index));
		}
		
	}
	
	/**
	 * Responsibilities of class: hold information about a task
	 */
	private class TaskPanel extends JPanel{
		
		//fields
		private String index;
		private String dueDate;
		
		//constructors
		public TaskPanel(String taskData) {
			this.dueDate = taskData.split("`")[0];
			this.index = taskData.split("`")[1];
		}
	}
	
	
}