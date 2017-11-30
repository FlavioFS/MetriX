package metrix.size;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

/**
 * Number of Raising: 
 * The number of exception raised by a given class divided by a number of methods and constructors of this class.  
 * @author Flavio Freitas
 *
 */
public class NoRExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double numberOfRaising = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			numberOfRaising = declaration.getChildNodesByType(ThrowStmt.class).stream()
					.filter(f -> f.getExpression() instanceof ObjectCreationExpr).count();
			if (declaration.getMethods().size() > 0) {
				numberOfRaising = numberOfRaising / declaration.getMethods().size();
			}

			BigDecimal valueAsBigDecimal = new BigDecimal(numberOfRaising).setScale(4, RoundingMode.HALF_EVEN);
			
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.NoR, valueAsBigDecimal.doubleValue()));
		}
	}
}
