package com.arinerron.forux.core;

public class FClock {
	private FGame game = null;
	
	public FClock(FGame game) {
		this.game = game;
	}
	
	public FGame getGame() {
		return this.game;
	}
}
