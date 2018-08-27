package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

	private ArrayList<File> files;
	private String zipName;
	
	// Defualt constructor
	// arraylist of files to zip 
	// name of zipfile that will be created
	
	public Zip(ArrayList<File> files, String zipName){
		
		this.files = files;
		this.zipName = zipName;
		
		
	}
	
	public String zipFiles(){
		
		byte[] buffer = new byte[18024];
		int len;
		
		try{
			
			if(zipName.contains(".")){
				
				zipName = zipName.split(".")[0] + ".zip";
				
			}
			
			else{
				
				zipName = zipName + ".zip";
				
				
			}
			File outputFile = new File(zipName);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
			
			// this below sets the compression
			out.setLevel(Deflater.DEFAULT_COMPRESSION);
			
			// time to go thru. files
			for(int i = 0; i < files.size(); i++){
				
				// load a new file input stream
				FileInputStream in = new FileInputStream(files.get(i));
				
				// add ZIP entry to the out stream
				
				String fileName = files.get(i).getName();
				out.putNextEntry(new ZipEntry(fileName));
				
				// transfer bytes from the curr file to the new zip file
				while((len = in.read(buffer)) > 0){
					
					out.write(buffer, 0, len);
					
				}
				
				// close current entry & inout stream
				out.close();
				in.close();
				
				
				
				
			}
			
			out.close();
			
			return "Zipper has been successful";
			
		}
		
		catch(Exception e){
			
			return "An exception occured during the zip process " + e;
			
		}
		
	}
	
}
