package com.senla.library.util;

import com.danco.training.TextFileWorker;
import com.senla.library.api.IEntityConverter;
import com.senla.library.entity.Entity;

public class FileWorker {

	public static void readData(TextFileWorker textFileWorker, Entity[] entityList, IEntityConverter template){
		String[] dataFromFile = textFileWorker.readFromFile();		
		for (int i = 0; i < dataFromFile.length; i++) 	{			
			entityList[i] = template.convertEntity(dataFromFile[i].split("%%"));
		}
	}

	public static void saveData(TextFileWorker textFileWorker, Entity[] entityList) {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(entityList));
	}

}
