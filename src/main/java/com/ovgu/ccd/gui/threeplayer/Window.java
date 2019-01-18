package com.ovgu.ccd.gui.threeplayer;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window
{
	private static final int MIN_WINDOW_WIDTH = 512;
	private static final int MIN_WINDOW_HEIGHT = 512;
	private static final int MAX_WINDOW_WIDTH = 1280;
	private static final int MAX_WINDOW_HEIGHT = 1280;
	
	private JFrame frame = null;
	private String windowName = "Default Name";
	private int windowHeight = 100;
	private int windowWidth = 100;

	
	// constructor
	// create a default window
	public Window()
	{
		createWindow();
	}
	
	
	// constructor
	// create a user-defined window
	public Window(String name, int width, int height)
	{
		setWindowName(name);
		setWindowWidth(width);
		setWindowHeight(height);
		createWindow();
	}
	
	
	// constructor
	// create a user-defined window
	public Window(String name, int width, int height, JPanel panel)
	{
		setWindowName(name);
		setWindowWidth(width);
		setWindowHeight(height);
		createWindow();
		addPanel(panel);
	}
	
	
	// creates a window with the given parameters
	private void createWindow()
	{
		frame = new JFrame(this.windowName);
		frame.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
		frame.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
		frame.setMaximumSize(new Dimension(MAX_WINDOW_WIDTH, MAX_WINDOW_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

	// add a JPanel to the window frame
	public void addPanel(JPanel panel)
	{
		if (panel != null)
			frame.add(panel);
	}
	
	
	// set the width of the window
	public void setWindowWidth(int width)
	{
		if (width > 0)
			this.windowWidth = width;
	}
	
	
	// set the height of the window
	public void setWindowHeight(int height)
	{
		if (height > 0)
			this.windowHeight = height;
	}
	
	
	// set the name of the window
	public void setWindowName(String name)
	{
		this.windowName = name;
	}
	
	
	// when enabled the whole app will be close when the close button is clicked
	public void setExitOnClose(boolean en)
	{
		if (en)
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	// @return: name of the window
	public String getWindowName()
	{
		return this.windowName;
	}
	
	
	// @return: width of the window
	public int getWindowWidth()
	{
		return this.windowWidth;
	}
	
	
	// @return: height of the window
	public int getWindowHeight()
	{
		return this.windowHeight;
	}
	
	
	// @return: JFrame object
	public JFrame getFrame()
	{
		return frame;
	}
}
