package modules;

import java.lang.reflect.Constructor;

public class Module {
	private String name;
	private String ip;
	private Integer port;
	private Object parser;
	
	
	public Module(String name, String ip, Integer port, boolean setParser) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		if (setParser) this.setParser(name);
	}
	
	public Module(String name, String ip, boolean setParser) {
		this.name = name;
		this.ip = ip;
		if (setParser) this.setParser(name);
	}
	
	public Module(String name, Integer port, boolean setParser) {
		this.name = name;
		this.port = port;
		if (setParser) this.setParser(name);
	}
	
	public String getName() {
		return name;
	}
	public boolean setName(String name) {
		this.name = name;
		return true;
	}
	public String getIp() {
		return ip;
	}
	public boolean setIp(String ip) {
		this.ip = ip;
		return true;
	}
	public Integer getPort() {
		return port;
	}
	public boolean setPort(Integer port) {
		this.port = port;
		return true;
	}

	public Object getParser() {
		return parser;
	}

	public boolean setParser(String moduleName) {
		try {
			Class<?> parserClass = Class.forName("modules.parser." + moduleName);
			Constructor<?> parserClassConstructor = parserClass.getConstructors()[0];
			this.parser = parserClassConstructor.newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Setting " + name + " parser by name failed");
			return false;
		}
	}

	public String toString() {
		return name + "@" + ip + ":" + port;
	}
}