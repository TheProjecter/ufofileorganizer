package ufofilearchiver.core.services;

public interface IServiceBuilder {
	
	String getName();
	IService build();
	
}
