package fr.a2artal.a2camp.webapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * 
 * @author A2 Artal Innovation
 *
 */
public class FileDataStore{

	private String dataStoreRootPath = "";
	
	public void store(String customerId, String platformId, String cloudTimestamp, String data) {
		// Check if the folder structure for customerId/platformId already exists
		File customerDirectory = new File(dataStoreRootPath+customerId);
		if(!customerDirectory.isDirectory()){
			customerDirectory.mkdir();
		}
		if(!customerDirectory.canWrite()){
			throw new RuntimeException("Can not write to directory "+customerDirectory.getAbsolutePath());
		}
		
		File cloudPlatformDirectory = new File(customerDirectory.getAbsolutePath()+File.separator+platformId);
		if(!cloudPlatformDirectory.isDirectory()){
			cloudPlatformDirectory.mkdir();
		}
		if(!cloudPlatformDirectory.canWrite()){
			throw new RuntimeException("Can not write to directory "+cloudPlatformDirectory.getAbsolutePath());
		}
		
		//list old files
		String[] files  = cloudPlatformDirectory.list();
		
		//create the file
		File cloudTimestampFile = new File(cloudPlatformDirectory.getAbsolutePath()+File.separator+cloudTimestamp);
		if(!cloudTimestampFile.exists()){
			try {
				cloudTimestampFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		if(!cloudTimestampFile.canWrite()){
			throw new RuntimeException();
		}
		// Store the data
		FileOutputStream fo = null;
		OutputStreamWriter ow = null;
		try {
			fo = new FileOutputStream(cloudTimestampFile);
			ow = new OutputStreamWriter(fo);
			ow.write(data);
			System.out.println("File : " + cloudTimestampFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			for(String file : files){
				File f = new File(cloudPlatformDirectory.getAbsolutePath()+File.separator+file);
				f.delete();
			}
			try {
				ow.close();
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	
	//
	// Singleton
	//
	
	private static FileDataStore __instance = new FileDataStore();
	
	/**
	 * Singleton implementation
	 * @return
	 */
	public static FileDataStore getInstance() {
		return __instance;
	}
	
	/**
	 * Initialize the datastore
	 */
	private FileDataStore() {
	}

}
