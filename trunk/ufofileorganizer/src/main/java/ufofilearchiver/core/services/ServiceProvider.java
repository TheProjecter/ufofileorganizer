package ufofilearchiver.core.services;

import java.util.HashMap;
import java.util.Map;

import ufofilearchiver.core.IProvider;
import ufofilearchiver.core.ISystem;

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
