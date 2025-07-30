package gov.nic.common;

import java.io.File;

import org.apache.log4j.Logger;

public class ParseDetailsJsonFile {

	final static Logger logger = Logger.getLogger(ParseDetailsJsonFile.class);

	public static void FileDelete(File dir) {
		if (dir.isDirectory() == false) {
			logger.info("Not a directory. Do nothing");
			return;
		}
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			// logger.info("Deleting "+file.getName());
			file.delete();
		}
	}

	public static String getFilePath(String temp_path, int returnR1num) {
		String path = null;
		//for (int i = 1; i <= returnR1num; i++) {
			for (int i = 1; i <= 1000; i++) {
			// TODO Auto-generated method stub
			File file = new File(temp_path + "/file" + i);
			// logger.info("file in getFilePath()::::::::::::::::::::::::::::::::::::::::::::::::::::"+file);
			if (file.isDirectory()) {
				path = "file" + i;
				// logger.info("path in getFilePath()::::::::::::::::::::::::::::::::::::::::::::::::::::"+path);
				break;
			} else {
				path = "Path Not Found";
			}
		}
		return path;
	}
}
