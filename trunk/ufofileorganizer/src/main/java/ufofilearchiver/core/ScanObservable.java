package ufofilearchiver.core;

public interface ScanObservable {
	void attach(ScanObserver observer);
	
	void detach(ScanObserver obverver);
	
	void inform();
}
