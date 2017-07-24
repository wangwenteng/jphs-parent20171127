package vm;

import java.io.InputStream;

public class TemplateHelper {
	
	public static InputStream getTemplateInputStream(String name){
		return TemplateHelper.class.getResourceAsStream(name + ".vm");
	}
	
}
