package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class EducationSystem {

	private JFrame frame;
	private JTextField txt_student_num;
	private JLabel lbal_password_input;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EducationSystem window = new EducationSystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EducationSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_num_input = new JLabel("请输入你的学号");
		lbl_num_input.setBounds(48, 12, 149, 15);
		frame.getContentPane().add(lbl_num_input);
		
		txt_student_num = new JTextField();
		txt_student_num.setBounds(48, 39, 271, 19);
		frame.getContentPane().add(txt_student_num);
		txt_student_num.setColumns(10);
		
		lbal_password_input = new JLabel("请输入你的密码");
		lbal_password_input.setBounds(48, 73, 107, 15);
		frame.getContentPane().add(lbal_password_input);
		
		textField = new JTextField();
		textField.setBounds(48, 100, 271, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("登录");
		button.setBounds(136, 168, 117, 25);
		frame.getContentPane().add(button);
	}
}
