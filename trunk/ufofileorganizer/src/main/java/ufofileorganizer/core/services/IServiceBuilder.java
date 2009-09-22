package ufofileorganizer.core.services;

public interface IServiceBuilder {
	
	String getName();
	IService build();
	
}
