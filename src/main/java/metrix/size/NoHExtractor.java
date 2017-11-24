package metrix.size;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class NoHExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double numberOfHandling = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			numberOfHandling = declaration.getChildNodesByType(CatchClause.class).size();
			if (declaration.getMethods().size() > 0) {
				numberOfHandling = numberOfHandling / declaration.getMethods().size();
			}
			BigDecimal valueAsBigDecimal = new BigDecimal(numberOfHandling).setScale(4, RoundingMode.HALF_EVEN);

			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.NoH, valueAsBigDecimal.doubleValue()));
		}
	}

}
