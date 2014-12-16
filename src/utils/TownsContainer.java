package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.jsp.JspWriter;

import com.sun.mail.iap.Response;

public class TownsContainer {
	private static String[] towns = {"Asenovgrad",
									"Blagoevgrad", "Burgas", 
									"Dimitrovgrad", "Dobrich", "Dupnitsa",
									"Gabrovo",
									"Haskovo",
									"Kardzhali", "Kazanlak", "Kyustendil",
									"Lom", "Lovech",
									"Montana",
									"Pernik", "Plovdiv",
									"Razgrad", "Rousse",
									"Stara-Zagora", "Svishtov",
									"Pazardzhik", "Pleven",
									"Sandanski", "Sevlievo", "Sliven", "Shumen", "Sofia",
									"Targovishte", "Troyan",						
									"Varna", "Veliko Tarnovo", "Velingrad", "Vidin", "Vratsa"};
	
	public TownsContainer() {
	}
	
	public static void printTownsInSelectMenu(JspWriter out) throws IOException{ 
		for(int i=0; i < towns.length; i++)
			out.print("<option>" + towns[i] + "</option>");
	}
}
