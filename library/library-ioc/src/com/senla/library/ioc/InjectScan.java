package com.senla.library.ioc;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import com.senla.library.api.annotation.di.Inject;

public class InjectScan {
	
	static Set<Field> getInjectFields() {
		URLClassLoader urlClassLoader = (URLClassLoader) DIHandler.class.getClassLoader();
		URL[] urls = urlClassLoader.getURLs();
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder().setUrls(urls)
				.setScanners(new FieldAnnotationsScanner());
		Reflections reflections = new Reflections(configurationBuilder);
		return reflections.getFieldsAnnotatedWith(Inject.class);
	}
}
