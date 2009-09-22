package ufofileorganizer.core.services;

import java.util.HashMap;
import java.util.Map;

import ufofileorganizer.core.IProvider;
import ufofileorganizer.core.ISystem;

public class ServiceProvider implements ISystem, IProvider{

	private Map<String, IService> _ServiceMap = new HashMap<String, IService>();
	
	public void register(IServiceBuilder aServiceBuilder) {
		_ServiceMap.put(aServiceBuilder.getName(),aServiceBuilder.build());
	}
	
	public IService getService(String serviceName){
		if (!_ServiceMap.containsKey(serviceName)){
			return new NullService();
		}
		return _ServiceMap.get(serviceName);
	}
	
}
