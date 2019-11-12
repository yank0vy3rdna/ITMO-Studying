public class PermissionException extends RuntimeException{
	PermissionException(String trbl){
		super((trbl.length()!=0) ? trbl : "Undefined");
	}
}