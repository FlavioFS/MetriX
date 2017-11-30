package metrix.size;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

/**
 * Number of Empty Handlers: 
 * The number of empty handlers (i.e., catch blocs) in a given class.  
 * @author Flavio Freitas
 *
 */
public class NoEHExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double numberOfEmptyHandlers = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			for (CatchClause catchClause : declaration.getChildNodesByType(CatchClause.class)) {
				if(catchClause.getBody().isEmpty()) {
					numberOfEmptyHandlers++;
				}
			}
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.NoEH, numberOfEmptyHandlers));
		}
	}

}
