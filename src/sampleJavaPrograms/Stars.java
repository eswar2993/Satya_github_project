package sampleJavaPrograms;

public class Stars {

	public static void main(String[] args) {
		
		stars(5);
	}
	
	public static void stars(int count){
		for(int i=1;i<=count;i++){
			for(int j=0;j<i;j++){
				System.out.print("*");
			}
			System.out.println();
		}	
	}
	
	
	
	
	

}
