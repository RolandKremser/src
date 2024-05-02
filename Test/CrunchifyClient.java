package Test;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
 
public class CrunchifyClient {
	public static void main(String[] args) {
		String s = "";
		try {
 
			// Step1: Let's 1st read file from fileSystem
			//InputStream demoStream = new FileInputStream("/tmp/saveDemo.txt");
			//InputStream demoStream = new FileInputStream("/tmp/saveTest.txt");
			//InputStream demoStream = new FileInputStream("/tmp/abfTest.txt");
//			InputStream demoStream = new FileInputStream("/tmp/calcTest.txt");
//			InputStreamReader demoReader = new InputStreamReader(demoStream);
//			BufferedReader br = new BufferedReader(demoReader);
//			String line;
//			while ((line = br.readLine()) != null) {
//				s += line + "\n";
//			}
 
			// Step2: Now pass JSON File Data to REST Service
			try {
				//URL url = new URL("http://188.20.96.198:8080/ALL/UNLIMITED/webSave/saveBew/1x76859");
				//URL url = new URL("http://127.0.0.1:8080/CrunchifyREST/UNLIMITED/webSave/saveBew/1x76886");
				URL url = new URL("http://127.0.0.1:8080/CrunchifyREST/UNLIMITED/webAbf/get3/1x76920");
				//URL url = new URL("http://127.0.0.1:8080/CrunchifyREST/UNLIMITED/webCalc/berechne/1x76905");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestProperty("abfrage", "TestWeb2");
				connection.setRequestProperty("pagZeilen", "10");
				connection.setRequestProperty("pagAb", "40");
				connection.setRequestProperty("pagNr", "1");
				
//				HttpMethod get = new GetMethod(url);
//			    get.setRequestHeader("Referer", requestUrl);
//			    
//				System.out.println(" vor Head setzen");
//				List<String> ls= List0("\"TestWeb2\"");
//				System.out.println(" List="+ls);
//				//connection.getHeaderFields().putIfAbsent("abfrage",ls);
//				//connection.getHeaderFields().put("abfrage", List0("TestWeb2"));
//				System.out.println(" pag setzen");
//				System.out.println("header="+connection.getHeaderFields());
//				connection.getHeaderFields().put("pagZeilen", null);//List0("10"));
//				connection.getHeaderFields().put("pagAb", null);//List0("10"));
//				System.out.println(" nach Head setzen");
				
//				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//				out.write(s);//jsonObject.toString());
//				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String sIn=in.readLine();
				while (sIn != null) {
					System.out.println(sIn);
					sIn=in.readLine();
				}
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}
 
//			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> List0(String s) {
	    List<String> list = new ArrayList<String>();
	    list.add(s);
	    return list;
	}
}