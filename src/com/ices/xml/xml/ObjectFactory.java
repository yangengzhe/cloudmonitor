package com.ices.xml.xml;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * 
 * @author zywei
 * 
 */
@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}


	public PlatformInit createPlatformInit() {
		return new PlatformInit();
	}
}
