package main;

import observers.Observer;
public interface Observable {
	void addObserver(Observer observer);
	void notifyObservers(String updateString);
	void recibeData(String key);
}
