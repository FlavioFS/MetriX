package metrix.coupling;

import java.util.HashSet;
import java.util.Set;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

/**
 * Signaling Coupling: 
 * The number of different exception types signaled by all methods and constructors of a given class.  
 * @author Flavio Freitas
 *
 */
public class SCoExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			Set<String> exceptions = new HashSet<>();
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			for (ConstructorDeclaration constructor : declaration.getChildNodesByType(ConstructorDeclaration.class)) {
				for (ReferenceType<?> exception : constructor.getThrownExceptions()) {
					exceptions.add(exception.getElementType().toString());
				}
			}
			for (MethodDeclaration method : declaration.getMethods()) {
				for (ReferenceType<?> exception : method.getThrownExceptions()) {
					exceptions.add(exception.getElementType().toString());
				}
			}
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.SCo, (double) exceptions.size()));
		}
	}
}
