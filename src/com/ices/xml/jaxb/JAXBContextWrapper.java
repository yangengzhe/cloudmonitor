package com.ices.xml.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;

@Component
public class JAXBContextWrapper {
	private static String DEFAULT_CONTEXT_PATH = "com.ices.xml.xml";

	public <T> T unmarshal(String xml, Class<T> declaredType) throws JAXBException {
		return unmarshal(new StringReader(xml), declaredType);
	}

	public <T> T unmarshal(Reader xml, Class<T> declaredType) throws JAXBException {
		if (xml == null) {
			throw new IllegalArgumentException("Input source xml Reader can't be null.");
		}
		Unmarshaller unmarshaller = ContextHolder.context.createUnmarshaller();
		// ContextHolder.context=javax.xml.bind.JAXBContext
		// .newInstance(Company.class);
		JAXBElement<T> element = unmarshaller.unmarshal(new StreamSource(xml), declaredType);
		return element.getValue();
	}

	public <T> T unmarshal(InputStream xml, Class<T> declaredType) throws JAXBException {
		if (xml == null) {
			throw new IllegalArgumentException("Input source xml Inputstream can't be null.");
		}
		Unmarshaller unmarshaller = ContextHolder.context.createUnmarshaller();
		JAXBElement<T> element = unmarshaller.unmarshal(new StreamSource(xml), declaredType);// 啥问题
		return element.getValue();
	}

	public <T> T unmarshal(URL xml, Class<T> declaredType) throws JAXBException {
		if (xml == null) {
			throw new IllegalArgumentException("Input source xml URL can't be null.");
		}
		InputStream is;
		try {
			is = xml.openStream();
		} catch (IOException e) {
			throw new JAXBException(e);
		}
		return unmarshal(is, declaredType);
	}
	
	public  <T> T readString(String context,Class<T> clazz) throws JAXBException {
        try {
            Unmarshaller unmarshaller = ContextHolder.context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(context));
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }
	
	public  <T> T readString(File file,Class<T> clazz) throws JAXBException {
        try {
            Unmarshaller unmarshaller = ContextHolder.context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }

	/**
	 * 单例
	 */
	private static class ContextHolder {
		private static JAXBContext context;// 线程安全 可使用单例 减少开销
		static {
			if (context == null) {
				try {
					context = JAXBContext.newInstance(DEFAULT_CONTEXT_PATH);
				} catch (Exception e) {
					System.out.println(e);
					throw new IllegalStateException("Can't construct JAXBContext with path [" + DEFAULT_CONTEXT_PATH + "]", e);
				}
			}
		}
	}
}
