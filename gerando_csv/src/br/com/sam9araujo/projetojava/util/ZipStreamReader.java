package br.com.sam9araujo.projetojava.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamReader {

	public static byte[] zipStrem(InputStream inputStream, String name)
			throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(
				byteArrayOutputStream);

		ZipEntry zipEntry = new ZipEntry(name);
		zipOutputStream.putNextEntry(zipEntry);

		int bytesRead;
		while ((bytesRead = inputStream.read()) > 0) {
			zipOutputStream.write(bytesRead);
		}

		zipOutputStream.closeEntry();
		zipOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}
	
	public static InputStream filePathToInputStream(String path) throws FileNotFoundException{
		return new FileInputStream(path);
	}
	
	public static byte[] filePathToByteArray(String path) throws IOException{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FileInputStream fileInputStream = new FileInputStream(path);
		
		int bytesRead;
		while ((bytesRead = fileInputStream.read()) > 0) {
			byteArrayOutputStream.write(bytesRead);
		}
		
		fileInputStream.close();
		
		return byteArrayOutputStream.toByteArray();
		
	}
	
	
	public static void main(String[] args){
		try {
			InputStream is = filePathToInputStream("C:\\tmp\\out.html");
			byte[] streamZip = zipStrem(is, "out.html");
			byte[] stream = filePathToByteArray("C:\\tmp\\out.html");
			System.out.println("strem:" + stream.length);
			System.out.println("streamZip:" + streamZip.length);
			
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\tmp\\out.zip", false);
			fileOutputStream.write(streamZip);
			fileOutputStream.close();
			is.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
