package autograph.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class ButtonTabComponent extends JPanel {
	private final JTabbedPane pane;
    private final JLabel label;
    private final JButton button = new TabButton();

    public ButtonTabComponent(String title, JTabbedPane pane) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        setOpaque(false);
        label = new JLabel(title);
        
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(button);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Making nice rollover effect
            //we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            addActionListener(this);            
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            if (i != -1) {
                pane.remove(i);
            }
        }

        //we don't want to update UI for this button
        public void updateUI() {
        }

        //paint the "x" and update the title
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Stroke stroke = g2.getStroke();
         
            //shift the image for pressed buttons
            //changed by DG
            //if(!getModel().isPressed()) {
            //    g2.translate(-1, -1);
            //}
         
            g2.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            if(getModel().isRollover()) {
                Color highlight = new Color(0, 51, 153); //dark blue - changed by DG
                g.setColor(highlight);
            }         
            int delta = 5; //changed by DG
            g.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
         
            //NOTE: only needed if button pressed in effect is used above
            //changed by DG
            //leave the graphics unchanged
            //if(!getModel().isPressed()) {
            //    g.translate(1, 1);
            //}
            g2.setStroke(stroke);
         
            //add the title here - added by DG
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            pane.setTitleAt(i, label.getText());
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}
