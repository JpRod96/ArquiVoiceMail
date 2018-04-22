package main;

import observers.StateWatcher;
public interface Subject {
	void addObserver(StateWatcher observer);
	void notifyObservers(String updateString);
	void recibeData(String key);
}
