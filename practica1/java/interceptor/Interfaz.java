package interceptor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Interfaz {	
	public void ejecutar(double numeroVueltas) throws IOException, URISyntaxException {
		String url = "http://localhost:8080/practica1/home.xhtml";

        if (Desktop.isDesktopSupported()) {
            // Windows
        	try {
        		Desktop.getDesktop().browse(new URI(url));
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            //Windows
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe -new-window " + url);
        }
	}
	
}