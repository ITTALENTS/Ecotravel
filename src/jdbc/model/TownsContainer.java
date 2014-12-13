package jdbc.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.jsp.JspWriter;

import com.sun.mail.iap.Response;

public class TownsContainer {
	private static String[] towns = {"Burgas", "Blagoevgrad", 
										"Lovech", 
										"Pleven", "Plovdiv", "Sofia",
										"Varna", "Veliko Turnovo", "Vidin", "Vraca"};
	
	public TownsContainer() {
	}
	
	public static void printTownsInSelectMenu(JspWriter out) throws IOException{ 
		for(int i=0; i < towns.length; i++)
			out.print("<option>" + towns[i] + "</option>");
	}
}
