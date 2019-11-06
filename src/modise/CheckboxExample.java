package modise;

import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CheckboxExample  
{  
     CheckboxExample(){  
      JFrame f= new JFrame("Checkbox Example");  
        Checkbox cb1 = new Checkbox("C++");  
        cb1.setBounds(100,50, 50,50);  
        Checkbox cb2 = new Checkbox("Java");  
        cb2.setBounds(100,80, 50,50);  
        Checkbox cb3 = new Checkbox("3");  
        cb3.setBounds(100,110, 50,50);
        Checkbox cb4 = new Checkbox("4");  
        cb4.setBounds(100,140, 50,50);
        
        JButton a = new JButton("btn");
        a.setBounds(100,170, 50,50);
        
        f.add(cb1);  
        f.add(cb2);  
        f.add(cb3);
        f.add(cb4);
        f.add(a);
        f.setSize(400,400);  
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
				 
				 if (cb1.) {
					 
				 }
				
			}
		});
        
     }  
public static void main(String args[])  
{  
    new CheckboxExample();  
}  
}  