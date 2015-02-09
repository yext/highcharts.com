/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.highcharts.export.util;

import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author gert
 */
public class TempDir {

	public static Path tmpDir;
	public static Path outputDir;
	public static Path phantomJsDir;

	public TempDir() throws IOException {
		Set<PosixFilePermission> perms =
		    PosixFilePermissions.fromString("rwxr-xr--");
		tmpDir = Files.createTempDirectory("export", PosixFilePermissions.asFileAttribute(perms));

		// Delete this directory on deletion of the JVM
		tmpDir.toFile().deleteOnExit();

		outputDir = Files.createDirectory(Paths.get(tmpDir.toString(), "output"), PosixFilePermissions.asFileAttribute(perms));
		phantomJsDir = Files.createDirectory(Paths.get(tmpDir.toString(), "phantomjs"));

		System.out.println("Highcharts Export Server using " +TempDir.getTmpDir() + " as TEMP folder.");
	}

	public static Path getTmpDir() {
		return tmpDir;
	}

	public static Path getOutputDir() {
		return outputDir;
	}

	public static Path getPhantomJsDir() {
		return phantomJsDir;
	}

	public static String getDownloadLink(String filename) {
		filename = FilenameUtils.getName(filename);
		String link = "files/" + filename;
		return link;
	}



}
