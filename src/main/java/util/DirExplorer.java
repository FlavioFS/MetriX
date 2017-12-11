package util;

import java.io.File;

/**
 * Defines methods and interfaces to assist in project directory traversal.
 * @author Flavio Freitas
 *
 */
public class DirExplorer {

	/**
	 * An interface for handling source files.
	 * @author Flavio Freitas
	 *
	 */
	public interface FileHandler {
		
		/**
		 * Handles each source file with this method. Decides which metrics should apply.
		 * @param level File depth in directory tree.
		 * @param path File path.
		 * @param file File as a File object.
		 */
		void handle(int level, String path, File file);
		
	}

	/**
	 * An interface to limit handled files in project directory to source files only.
	 * @author Flavio Freitas
	 *
	 */
	public interface Filter {
		
		/**
		 * Decides if file should be handled. This method is applied to each file during directory tree traversal.
		 * @param level File depth in directory tree.
		 * @param path File path.
		 * @param file File to handle (or not).
		 * @return True if file should be handled. False if not.
		 */
		boolean interested(int level, String path, File file);
		
	}

	private FileHandler fileHandler;
	private Filter filter;

	/**
	 * Default constructor.
	 * @param filter Filters files in each folder during recursive directory tree traversal.
	 * @param fileHandler Handles each file (applies desired metrics) during traversal.
	 */
	public DirExplorer(Filter filter, FileHandler fileHandler) {
		this.filter = filter;
		this.fileHandler = fileHandler;
	}

	/**
	 * Traverses recursively project directory, handling filtered files. Default parameters for explore(0, "", root).
	 * @param root Starting directory or file.
	 */
	public void explore(File root) {
		explore(0, "", root);
	}

	/**
	 * Traverses recursively project directory, handling filtered files.
	 * @param level File depth in directory tree.
	 * @param path File path.
	 * @param file File as a File object.
	 */
	private void explore(int level, String path, File file) {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				explore(level + 1, path + "/" + child.getName(), child);
			}
		} else {
			if (filter.interested(level, path, file)) {
				fileHandler.handle(level, path, file);
			}
		}
	}
}