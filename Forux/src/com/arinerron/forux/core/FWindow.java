package com.arinerron.forux.core;

import javax.swing.JFrame;

public class FWindow {
	private FGame game = null;
	protected JFrame frame = null;
	private List<>
	
	public FWindow(FGame game) {
		this.game = game;
		
		this.frame = new JFrame(this.getGame().getName());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public FGame getGame() {
		return this.game;
	}
	
	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}
	
	public void setSize(int width, int height) {
		this.frame.setSize(width, height);
	}
	
	public void setFullscreen(boolean fullscreen) {
		if(fullscreen)
			this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		else
			this.frame.setExtendedState(JFrame.NORMAL);
	}
}
