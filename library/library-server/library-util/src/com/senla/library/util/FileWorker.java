package com.senla.library.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.library.api.bean.IEntity;

public class FileWorker {	
	private static Logger logger = Logger.getLogger(FileWorker.class);

	public static Object read(String filePath) {
		Object fileData = null;
		try (FileInputStream fileInputStream = new FileInputStream(filePath);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			fileData =  objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e);			
		}		
		return fileData;
	}

	public static void save(List<? extends IEntity> entities, String filePath) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
			objectOutputStream.writeObject(entities);
			objectOutputStream.flush();
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
