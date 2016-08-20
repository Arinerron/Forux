package com.arinerron.forux.core;

public class FClock {
	private FGame game = null;
	private int ticks = 0;
	
	public FClock(FGame game) {
		this.game = game;
	}
	
	public FGame getGame() {
		return this.game;
	}
}
