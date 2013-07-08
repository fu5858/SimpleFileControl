package au.com.aussie.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyTest {
	private static final String SPACES = "    ";

	public static void main(String[] args) {
		FileCopyTest fct = new FileCopyTest();
		
		if (args.length != 1) {
			System.err.println("Usage: RecursiveDirectoryTreePrinter <dir>");
			System.exit(4);
		}
		
		String targetDir = "D:\\copyTemp";

//		fct.fileSearch(new File(args[0]), "");
		
		
		fct.fileSearch(new File(args[0]), targetDir, "");
	}
	
	/**
	 * To find files from a directory
	 * @param file
	 * @param targetDir
	 */
	public void fileSearch(File file, String targetDir){
		
		File targetFilePath = new File(targetDir);
		
		if(targetFilePath.mkdir()){
			System.out.println("Create a target directory.");
		}
		
		String fileName = file.getName();
		
		if(file.isDirectory()){
			targetDir += "\\" + fileName;

			targetFilePath = new File(targetDir);
			targetFilePath.mkdir();
			
			fileSearch(file.listFiles(), targetDir);
		}else{
			copyFile(file, targetDir, fileName);
		}
	}

	public void fileSearch(File file, String targetDir, String indent){
		
		File targetFilePath = new File(targetDir);
		
		if(targetFilePath.mkdir()){
			System.out.println("Create a target directory.");
		}
		
		String fileName = file.getName();
		
		System.out.print(indent);
		System.out.println(fileName);
		
		if(file.isDirectory()){
			targetDir += "\\" + fileName;
			
			targetFilePath = new File(targetDir);
			targetFilePath.mkdir();
			
			fileSearch(file.listFiles(), targetDir, indent + SPACES);
		}else{
			copyFile(file, targetDir, fileName);
		}
	}
	
	public void fileSearch(File[] listFiles, String targetDir) {
		
		for(File file : listFiles){
			fileSearch(file, targetDir);
		}
	}
	
	public void fileSearch(File[] listFiles, String targetDir, String indent) {
		
		for(File file : listFiles){
			fileSearch(file, targetDir, indent);
		}
	}
	
//	public void print(File file, String indent){
//		String targetDir = "D:\\copyTemp";
//		
//		File targetFilePath = new File(targetDir);
//		
//		if(targetFilePath.mkdir()){
//			System.out.println("Create a target directory.");
//		}
//		
//		String fileName = file.getName();
//		
//		System.out.print(indent);
//		System.out.println(fileName);
////		System.out.print(fileName);
////		System.out.print("\t");
////		System.out.println(targetDir);
//		
//		if(file.isDirectory()){
//			targetDir += "\\" + fileName;
//			
//			targetFilePath = new File(targetDir);
//			targetFilePath.mkdir();
//			
////			indent += SPACES;
//			
////			print(file.listFiles(), indent + SPACES);
//		}
//		
//		System.out.println(targetDir);
//		
//		if(file.isFile()){
//			System.out.println(targetDir);
//		}
//		
//	}
//	
//	public void print(File[] listFiles, String indent) {
//
//		for(File file : listFiles){
//			print(file, indent);
//		}
//	}
	
	/**
	 * To copy a file with an original file to a target file
	 * @param fromFile
	 * @param toFile
	 */
	public void copyFile(File fromFile, File toFile){
		InputStream fromFileInputStream = null;
		OutputStream toFileOutputStream = null;
		
		try{
			fromFileInputStream = new FileInputStream(fromFile);
			toFileOutputStream = new FileOutputStream(toFile);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = fromFileInputStream.read()) > 0){
				toFileOutputStream.write(buffer, 0, length);
			}
			
			toFileOutputStream.close();
			fromFileInputStream.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * To copy a file with original file to a target path and file name
	 * @param fromFile
	 * @param targetDir
	 * @param targetFileName
	 */
	public void copyFile(File fromFile, String targetDir, String targetFileName){
		InputStream fromFileInputStream = null;
		OutputStream toFileOutputStream = null;
		
		try{
			File targetFile = new File(targetDir, targetFileName);
			
			fromFileInputStream = new FileInputStream(fromFile);
			toFileOutputStream = new FileOutputStream(targetFile);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = fromFileInputStream.read()) > 0){
				toFileOutputStream.write(buffer, 0, length);
			}
			
			toFileOutputStream.close();
			fromFileInputStream.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * To copy a file with original file path and file name 
	 * to a target file path and file name
	 * @param baseDir
	 * @param baseFileName
	 * @param targetDir
	 * @param targetFileName
	 */
	public void copyFile(String baseDir, String baseFileName, String targetDir, String targetFileName) {
		InputStream fromFileInputStream = null;
		OutputStream toFileOutputStream = null;
		
		try{
			File baseFile = new File(baseDir, baseFileName);
			File targetFile = new File(targetDir, targetFileName);
			
			fromFileInputStream = new FileInputStream(baseFile);
			toFileOutputStream = new FileOutputStream(targetFile);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = fromFileInputStream.read()) > 0) {
				toFileOutputStream.write(buffer, 0, length);
			}
			
			toFileOutputStream.close();
			fromFileInputStream.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
