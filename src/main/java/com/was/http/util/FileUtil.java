package com.was.http.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtil {
	public static byte[] readToBytes(File file) throws IOException {
		return Files.readAllBytes(file.toPath());
	}

	public static String[] readToStr(File file) throws Exception {
		return Files.lines(file.toPath()).toArray(String[]::new);
	}
	
	public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

		try (FileOutputStream outputStream = new FileOutputStream(file)) {

			int read;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		}

	}
	public static void trace(Path src, String suffix, List<File> list) {
		if (src.toFile().isDirectory()) {
			for (File file : src.toFile().listFiles()) {
				trace(file.toPath(), suffix, list);
			}
		} else if(src.toFile().getName().endsWith(suffix)){
			list.add(src.toFile());
		}
	}
}
