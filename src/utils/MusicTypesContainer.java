package utils;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class MusicTypesContainer {
	private static String[] musicTypes = {"Everything", "Pop folk", "Commercial", "Pop", "Rap", "Rock", "Metal", "Folk", "Techno"};

	public MusicTypesContainer() {}

	public static void printMusicTypes(JspWriter out) throws IOException{ 
		for(int i=0; i < musicTypes.length; i++)
		out.print("<option>" + musicTypes[i] + "</option>");
	}
}
