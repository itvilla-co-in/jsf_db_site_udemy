package com.dev.user.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogIt {

	static Logger logger;
	static FileHandler fh;
	
	public static void saveLog(String filename, String msg_prefix, String msg){
		logger = Logger.getLogger("MyLog");
		
		try {
			File f = new File(filename); // check if path is writable on disc (server-production)
			if(!f.exists()){
				fh = new FileHandler(filename, true);
				logger.addHandler(fh);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
			}
			
			logger.info(msg_prefix+msg);
			
		} catch (SecurityException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
