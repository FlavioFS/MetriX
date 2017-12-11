package util;

import java.io.File;

import util.DirExplorer.Filter;

public class JavaFilter implements Filter {

	@Override
	public boolean interested(int level, String path, File file) {
		return path.endsWith(".java");
	}

}
