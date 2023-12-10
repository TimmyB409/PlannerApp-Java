
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
	public void drawTask(String task, JPanel panel) {
		
		JPanel taskPanel = new JPanel();
		taskPanel.setSize(800, 200);
		taskPanel.setBackground(Color.LIGHT_GRAY);
		taskPanel.add(new JLabel("Due Date: " + task));
		taskPanel.add(new JButton("CompleteTask"));
		panel.add(taskPanel);
		
	}
	/**
	 * Purpose: removes a task panel from the panel
	 * @return void
	 * @param JPanel taskPanel
	 */
	public void removeTask(JPanel taskPanel) {
		panel.remove(taskPanel);
	}
	
	
	//Internal Classes
	private class TaskButton extends JButton{
		
		//fields
		private JPanel panel;
		
		//constructors
		public TaskButton(JPanel panel) {
			for(int i; i < panel.getComponents().length(); i++) {
				
			}
			this.panel = panel;
		}
	}
	
	private class CompleteTaskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TaskButton button = (TaskButton)e.getSource();
			controller.completeTask(panel.getComponents());
			removeTask(panel);
		}
		
	}
	
}