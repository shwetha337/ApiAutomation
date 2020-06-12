package Practice;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;



public class TestSample2 {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException{
		dataDriven driven=new dataDriven(); 
		ArrayList<String> data=driven.getData("CheckRunState");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
	}

}
