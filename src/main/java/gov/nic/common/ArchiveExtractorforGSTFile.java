package gov.nic.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;

public class ArchiveExtractorforGSTFile {

	final static Logger logger = Logger.getLogger(ArchiveExtractorforGSTFile.class);

	public void TarGZExtractor(File source, File destin)throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(source);
		TarArchiveInputStream tis = new TarArchiveInputStream(fis);
		TarArchiveEntry tarEntry = null;
		// tarIn is a TarArchiveInputStream
		while ((tarEntry = tis.getNextTarEntry()) != null) {
			File outputFile = new File(destin + File.separator + tarEntry.getName());
			// File outputFile = new File(destin + tarEntry.getName());
			if (tarEntry.isDirectory()) {
				//logger.info("outputFile Directory:-"+ outputFile.getAbsolutePath());
				if (!outputFile.exists()) {
					outputFile.mkdir();
				}
			} else {
				// File outputFile = new File(destFile + File.separator +
				// tarEntry.getName());
				// System.out.println("outputFile File ---- " +
				// outputFile.getAbsolutePath());
				outputFile.getParentFile().mkdir();
				// outputFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(outputFile);
				IOUtils.copy(tis, fos);
				fos.close();
			}
		}
		tis.close();
		//return null;
	}

	public File deCompressGZipFile(File gZippedFile, File tarFile)throws IOException {
		FileInputStream fis = new FileInputStream(gZippedFile);
		GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);

		FileOutputStream fos = new FileOutputStream(tarFile);
		byte[] buffer = new byte[1024];
		int len;
		while ((len = gZIPInputStream.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		gZIPInputStream.close();
		return tarFile;

	}

	public static String getFileName(File inputFile, String outputFolder) {
		return outputFolder	+ File.separator+ inputFile.getName().substring(0,inputFile.getName().lastIndexOf('.'));
	}

}
