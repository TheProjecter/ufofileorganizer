package ufofileorganizer.core;

import ufofileorganizer.core.services.IService;

public interface IProvider {

	IService getService(String serviceName);
	
}
