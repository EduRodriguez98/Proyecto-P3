package modise;

import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class CheckboxExample {
	CheckboxExample() {
		JFrame f = new JFrame("Checkbox Example");
		JCheckBox cb1 = new JCheckBox("C++");
		cb1.setBounds(100, 50, 50, 50);
		JCheckBox cb2 = new JCheckBox("Java");
		cb2.setBounds(100, 80, 50, 50);
		JCheckBox cb3 = new JCheckBox("3");
		cb3.setBounds(100, 110, 50, 50);
		JCheckBox cb4 = new JCheckBox("4");
		cb4.setBounds(100, 140, 50, 50);

		JButton a = new JButton("btn");
		a.setBounds(100, 170, 50, 50);

		f.add(cb1);
		f.add(cb2);
		f.add(cb3);
		f.add(cb4);
		f.add(a);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String a = "";
				String b = "";
				String c = "";
				String d = "";

				if (cb1.isSelected()) {
					a = "C++";
				}
				if (cb2.isSelected()) {
					b = "Java";
				}
				if (cb3.isSelected()) {
					c = "3";
				}
				if (cb4.isSelected()) {
					d = "4";
				}
				System.out.println(a + b + c + d);
			}
		});

	}

	public static void main(String args[]) {
		new CheckboxExample();
	}
}