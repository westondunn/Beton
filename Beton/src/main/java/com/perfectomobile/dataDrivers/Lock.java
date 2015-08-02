package com.perfectomobile.dataDrivers;

public class Lock{
	private boolean isLocked;
	
	
	public Lock() {
		this.isLocked = false;
	}

	public synchronized void lock() throws InterruptedException{
		while(isLocked){
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
}
