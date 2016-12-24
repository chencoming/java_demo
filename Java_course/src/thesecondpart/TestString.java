package thesecondpart;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Welcome to Java";
		String s2 = s1;
		String s3 = new String("Welcome to Java");
		String s4 = s3.intern();
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		
		System.out.println(s1.equals(s2));
		System.out.println(s2.equals(s3));
		
		System.out.println(s1.compareTo(s2));
		System.out.println(s2.compareTo(s3));
		
		System.out.println(s1 == s4);
		
		System.out.println(s1.charAt(0));
		
		System.out.println(s1.indexOf("to"));
		System.out.println(s1.lastIndexOf('a'));	
		System.out.println(s1.lastIndexOf("o",15));
		
		System.out.println(s1.length());
		System.out.println(s1.substring(5,11));
		
		System.out.println(s1.startsWith("Wel"));
		System.out.println(s1.endsWith("Java"));
		
		System.out.println(s1.toLowerCase());
		System.out.println(s1.toUpperCase());
				
		System.out.println("    Welcome   ".trim());
		
		System.out.println(s1.replace("o", "T"));
		System.out.println(s1.replaceAll("o", "T"));
		System.out.println(s1.replaceFirst("o", "T"));
		
		System.out.println(s4.toCharArray());
		
		
		
		
		
		
	}

}
