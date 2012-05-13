package autograph.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;

public class PrintPreview extends JDialog {
	protected int m_wPage;
	protected int m_hPage;

	protected JComboBox m_cbScale;
	protected PreviewContainer m_preview;

	// Constructor 1
	public PrintPreview(JDialog parentJDialog, Printable target) {
		this(parentJDialog, target, "Print Preview");
	}

	// Constructor 2
	public PrintPreview(JDialog parentJDialog,Printable target, String title) {
		super(parentJDialog, title);
		
		// Settings for the window
		setSize(800, 600);
		setModal(true);
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

		// Variables for window items
		JToolBar toolBar = new JToolBar();
		JButton button = new JButton();

		// Print Button
		button = new JButton("Print");
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.printWindow();
			}
		};
		button.addActionListener(listener);
		button.setAlignmentY(0.5f);
		button.setMargin(new Insets(4,6,4,6));
		toolBar.add(button);

		// Close Button
		button = new JButton("Close");
		listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		};
		button.addActionListener(listener);
		button.setAlignmentY(0.5f);
		button.setMargin(new Insets(4,6,4,6));
		toolBar.add(button);

		// Zoom Selection
		m_cbScale = new JComboBox(new String[] {"10%", "25%", "50%", "100%" });
		m_cbScale.setSelectedItem("50%");

		listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread runner = new Thread() {
					public void run() {
						String str = m_cbScale.getSelectedItem().toString();
						
						// Trim the % in the string
						if(str.endsWith("%")) {
							str = str.substring(0, str.length() - 1); 
						}
						str.trim();
						
						// Set the scale
						int scale = 0;
						try {
							scale = Integer.parseInt(str);
						}
						catch(NumberFormatException ex) {
							System.out.println("scale: " + scale);
							return;
						}
						int w = (int)(m_wPage * scale / 100);
						int h = (int)(m_hPage * scale / 100);

						// Set up the preview
						Component [] comps = m_preview.getComponents();
						for(int k = 0; k < comps.length; k++) {
							if(!(comps[k] instanceof PagePreview)) {
								continue; 
							}

							PagePreview pp = (PagePreview) comps[k];
							pp.setScaledSize(w, h);
						}
						
						// Layout everything
						m_preview.doLayout();
						m_preview.getParent().getParent().validate();

					}
				};
				// Run the thread
				runner.start();
			}
		};
		m_cbScale.addActionListener(listener);
		m_cbScale.setMaximumSize(m_cbScale.getPreferredSize());
		m_cbScale.setEditable(true);
		toolBar.addSeparator();
		toolBar.add(m_cbScale);

		// Add the toolbar
		getContentPane().add(toolBar, BorderLayout.NORTH);

		m_preview = new PreviewContainer();

		PrinterJob prnJob = PrinterJob.getPrinterJob();
		PageFormat pageFormat = prnJob.defaultPage();

		if(pageFormat.getHeight() == 0 ||pageFormat.getWidth() == 0 ) {
			// Error!
			return ;
		}
		m_wPage = (int)(pageFormat.getWidth());
		m_hPage = (int)(pageFormat.getHeight());

		int scale = 50;

		int w = (int)(m_wPage * scale / 100);
		int h = (int)(m_hPage * scale / 100);

		int pageIndex = 0;

		try {
			while(true) {
				BufferedImage img = new BufferedImage(m_wPage,m_hPage, BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, m_wPage, m_hPage);
				if(target.print(g, pageFormat, pageIndex) != Printable.PAGE_EXISTS) {
					break;
				}
				PagePreview pp = new PagePreview(w,h, img);
				m_preview.add(pp);
				pageIndex++;
			}
		}
		catch(PrinterException pe) {
			pe.printStackTrace();
		}

		JScrollPane ps = new JScrollPane(m_preview);
		getContentPane().add(ps, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

class PreviewContainer extends JPanel {
	protected int H_GAP = 16;
	protected int V_GAP = 10;

	public Dimension getPreferredSize() {
		int n = getComponentCount();

		if(n == 0) { 
			return new Dimension(H_GAP, V_GAP);
		}
		
		Component comp = getComponent(0);
		Dimension dc = comp.getPreferredSize();

		int w = dc.width;
		int h = dc.height;

		Dimension dp = getParent().getSize();
		int nCol = Math.max((dp.width - H_GAP) / (w + H_GAP), 1);
		int nRow = n / nCol;

		if(nRow * nCol < n) 
			nRow++;

		int ww = nCol * (w + H_GAP) + H_GAP;
		int hh = nRow * (h + V_GAP) + V_GAP;

		Insets ins = getInsets();
		return new Dimension(ww + ins.left + ins.right, hh + ins.top + ins.bottom);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	public Dimension getMinimumSize() {
		return getPreferredSize(); 
	}

	public void doLayout() {
		Insets ins = getInsets();

		int x = ins.left + H_GAP;
		int y = ins.top + V_GAP;

		int n = getComponentCount();

		if(n == 0) 
			return;

		Component comp = getComponent(0);
		Dimension dc = comp.getPreferredSize();
		int w = dc.width;
		int h = dc.height;

		Dimension dp = getParent().getSize();
		int nCol = Math.max((dp.width - H_GAP) / (w + V_GAP), 1);
		int nRow = n/nCol;

		if(nRow * nCol < n) nRow++;

		int index = 0;
		for(int k = 0; k < nRow; k++) {
			for(int m = 0; m < nCol; m++) {
				if(index >= n) 
					return;
				comp = getComponent(index++);
				comp.setBounds(x, y, w, h);
				x += w + H_GAP;
			}
			
			y += h + V_GAP;
			x = ins.left + H_GAP;
		}
	}
}

class PagePreview extends JPanel {
	protected int m_w;
	protected int m_h;
	protected Image m_source;
	protected Image m_img;

	public PagePreview(int w, int h, Image source) {
		m_w = w;
		m_h = h;

		m_source = source;
		m_img = m_source.getScaledInstance(m_w, m_h, Image.SCALE_SMOOTH);
		m_img.flush();
		setBackground(Color.white);
		setBorder(new MatteBorder(1, 1, 2, 2, Color.black));
	}

	public void setScaledSize(int w , int h) {
		m_w = w;
		m_h = h;
		m_img = m_source.getScaledInstance(m_w, m_h, Image.SCALE_SMOOTH);
		repaint();
	}

	public Dimension getPreferredSize() {
		Insets ins = getInsets();
		return new Dimension(m_w + ins.left + ins.right, m_h + ins.top + ins.bottom);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize(); 
	}
	
	public Dimension getMinimumSize() {
		return getPreferredSize(); 
	}

	public void paint(Graphics g) {
		g.setColor(getBackground()); 
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(m_img, 0, 0, this);
		paintBorder(g);
	}
}