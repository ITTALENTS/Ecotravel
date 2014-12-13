package jdbc.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.jsp.JspWriter;

import com.sun.mail.iap.Response;

public class TownsContainer {
	private static String[] towns = {"Sofia", "Lovech", "Varna", "Blagoevgrad", "Burgas"};
	
	public TownsContainer() {
	}
	
	public static void printTownsInSelectMenu(JspWriter out) throws IOException{ 
		sortArray(towns);
		for(int i=0; i < towns.length; i++)
			out.print("<option>" + towns[i] + "</option>");
	}
	
	private static void sortArray(String[] towns){
		Arrays.sort(towns);
	}
}
