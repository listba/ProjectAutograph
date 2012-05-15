package autograph.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class ColorPickerButton extends JButton implements ActionListener {
   private Color vColor;
   public ColorPickerButton() {
       int size = 17;
       setPreferredSize(new Dimension(size, size));
       setToolTipText("select color");
       //Make the button looks the same for all Laf's
       setUI(new BasicButtonUI());
       //Make it transparent
       setContentAreaFilled(true);
       //No need to be focusable
       setFocusable(true);
       setBorder(BorderFactory.createLineBorder(Color.BLACK));
       setBorderPainted(true);
       //Making nice rollover effect
       //we use the same listener for all buttons
       //addMouseListener(buttonMouseListener);
       setRolloverEnabled(true);
       //Close the proper tab by clicking the button
       addActionListener(this);            
   }

   public void actionPerformed(ActionEvent e) {
       
   }

   //we don't want to update UI for this button
   public void updateUI() {
   }

   public void setColor(Color color){
      vColor = color;
   }
   //paint the "x" and update the title
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
       Stroke stroke = g2.getStroke();
    
       g2.setStroke(new BasicStroke(2));
       g.setColor(vColor);

       
       g.fillRect(2, 2, getWidth()-1, getHeight()-1);

   }
}
