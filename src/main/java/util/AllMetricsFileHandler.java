package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.MemoryTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import metrix.concern.HDoSExtractor;
import metrix.coupling.HCoExtractor;
import metrix.coupling.RCoExtractor;
import metrix.coupling.SCoExtractor;
import metrix.ratio.RoCLoCExtractor;
import metrix.ratio.RoFLoCExtractor;
import metrix.ratio.RoTLoCExtractor;
import metrix.size.NoEHExtractor;
import metrix.size.NoGHExtractor;
import metrix.size.NoHExtractor;
import metrix.size.NoRExtractor;
import metrix.size.NoSExtractor;
import metrix.usage.EHMUExtractor;
import util.DirExplorer.FileHandler;

/**
 * A node handler that instantiates and applies ALL metrics to each source file.
 * @author Flavio Freitas
 *
 */
public class AllMetricsFileHandler implements FileHandler {

	File projectDir;
	
	/**
	 * Default constructor.
	 * @param projectDir Root directory as a File object.
	 */
	public AllMetricsFileHandler (File projectDir) {
		this.projectDir = projectDir;
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
			compilationUnit.accept(new RCoExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new HCoExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new SCoExtractor(), JavaParserFacade.get(typeSolver));

			compilationUnit.accept(new NoRExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new NoHExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new NoSExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new NoGHExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new NoEHExtractor(), JavaParserFacade.get(typeSolver));

			compilationUnit.accept(new RoTLoCExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new RoCLoCExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new RoFLoCExtractor(), JavaParserFacade.get(typeSolver));

			compilationUnit.accept(new HDoSExtractor(), JavaParserFacade.get(typeSolver));
			compilationUnit.accept(new EHMUExtractor(), JavaParserFacade.get(typeSolver));

		} catch (IOException e) {
			new RuntimeException(e);
		}
	}

}
