package com.src.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class textFiles {

	public static void main(String[] args) throws IOException{
		
		File file = new File(System.getProperty("user.dir")+"\\locators.loc");
		
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
		}
		
		
		file = new File(System.getProperty("user.dir")+"\\TextData.txt");
		
		scan = new Scanner(file);
		
		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
		}
		
	}

}
