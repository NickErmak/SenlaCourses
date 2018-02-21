package com.senla.library.ioc;

import static com.senla.library.ioc.InjectScan.getInjectFields;
import static com.senla.library.ioc.XMLLoader.loadConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.library.api.annotation.di.Inject;
import com.senla.library.api.annotation.di.Singleton;

public class DIHandler {
	private static final String getSingleton = "getInstance";
	private static Logger logger = Logger.getLogger(DIHandler.class);	

	public static void injectDependencies() {
		try {
			Map<String, String> xmlInfo = loadConfig();
			Iterator<Field> iterator = getInjectFields().iterator();
			while (iterator.hasNext()) {
				Field injectField = iterator.next();
				injectField.setAccessible(true);
				String injectClassName = xmlInfo.get(injectField.getName());
				String filePath = injectField.getAnnotation(Inject.class).value();

				Class<?> injectClass = Class.forName(filePath + '.' + injectClassName);
				if (injectClass.isAnnotationPresent(Singleton.class)) {
					injectField.set(null, injectClass.getMethod(getSingleton).invoke(null));
				} else {
					injectField.set(null, injectClass.newInstance());
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | InvocationTargetException
				| NoSuchMethodException | SecurityException | InstantiationException e) {
			logger.error(e);
		}
	}
}
