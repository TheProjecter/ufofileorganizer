package ufofileorganizer.core.commands;

public class DeleteObject extends ACommand {

	private Object _Object;

	public DeleteObject(Object aObject){
		_Object = aObject;
	}
	
	public boolean execute() {
		return _DBService.delete(_Object);
		
	}

	public Object result() {
		return new Object();
	}

}
