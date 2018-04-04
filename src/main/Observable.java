package main;

import observers.Observer;
public interface Observable {
	void addObserver(Observer observer);
	
	void deleteObserver(Observer observer);
}
