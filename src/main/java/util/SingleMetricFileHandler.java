package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.MemoryTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import util.DirExplorer.FileHandler;

/**
 * A node handler that instantiates and applies a single metric to each source file.
 * @author Flavio Freitas
 *
 */
public class SingleMetricFileHandler implements FileHandler {

	File projectDir;
	VoidVisitorAdapter<JavaParserFacade> metric;
	
	/**
	 * Default constructor.
	 * @param projectDir Root directory as a File object.
	 */
	/**
	 * Default constructor.
	 * @param projectDir Root directory as a File object.
	 * @param metric Aplly this metric only.
	 */
	public SingleMetricFileHandler (File projectDir, VoidVisitorAdapter<JavaParserFacade> metric) {
		this.projectDir = projectDir;
		this.metric = metric;
	}
	
	@Override
	public void handle(int level, String path, File file) {
		
		try {
			
			TypeSolver typeSolver = new CombinedTypeSolver(
					new JavaParserTypeSolver(this.projectDir),
					new ReflectionTypeSolver(),
					new MemoryTypeSolver()
			);
			
			CompilationUnit compilationUnit = JavaParser.parse(new FileInputStream(file));
			compilationUnit.accept(metric, JavaParserFacade.get(typeSolver));

		} catch (IOException e) {
			new RuntimeException(e);
		}
		
	}

}
