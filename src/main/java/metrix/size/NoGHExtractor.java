package metrix.size;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

/**
 * Number of General Handlers: 
 * The number of general handlers in a given class (i.e., the catch blocks that catches java.lang.Exception or java.lang.Throwable).  
 * @author Flavio Freitas
 *
 */
public class NoGHExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double numberOfGenericHandler = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();

			for (CatchClause catchClause : declaration.getChildNodesByType(CatchClause.class)) {
				Type exception = catchClause.getParameter().getType();
				
				if (exception.toString().equals("java.lang.Throwable") || exception.toString().equals("java.lang.Exception") || exception.toString().equals("Throwable") || exception.toString().equals("Exception")) {
					numberOfGenericHandler++;
				}
			}

			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.NoGH, numberOfGenericHandler));
		}
	}

}
