package util;

import java.io.File;

import util.DirExplorer.Filter;

/**
 * An implementation of Filter interface. Limits extraction to java source files only.
 * @author Flavio Freitas
 *
 */
public class JavaFilter implements Filter {

	@Override
	public boolean interested(int level, String path, File file) {
		return path.endsWith(".java");
	}

}
