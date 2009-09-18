package ufofilearchiver.core;

import ufofilearchiver.core.services.IService;

public interface IProvider {

	IService getService(String serviceName);
	
}
