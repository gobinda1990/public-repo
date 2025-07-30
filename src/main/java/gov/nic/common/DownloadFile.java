package gov.nic.common;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

public class DownloadFile {

	final static Logger logger = Logger.getLogger(DownloadFile.class);

	public static void retryDownload(String downloadurl, String path) throws Exception {
		HttpURLConnection httpConn = null;
		try {
			URL url = new URL(downloadurl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(1000);
			int responseCode = httpConn.getResponseCode();
			logger.info("Download Connection response status:-" + responseCode);
			try (FileOutputStream fos = new FileOutputStream(path+ "\\a.tar.gz")) {
			//	logger.info("Enter retryDownload method():--");
				URL url_download_ret_r1 = new URL(downloadurl);
				BufferedInputStream bis1 = new BufferedInputStream(url_download_ret_r1.openStream());
				byte data[] = new byte[1024];
				int count;
				while ((count = bis1.read(data, 0, 1024)) != -1) {
					fos.write(data, 0, count);
					fos.flush();
				}
			}
		} catch (Exception ex) {
			logger.error("Exception into retryDownload method():-"+ ex.getMessage());
		} finally {
         if(httpConn!=null){
        	 httpConn.disconnect();
         }
	   }

	}

}
