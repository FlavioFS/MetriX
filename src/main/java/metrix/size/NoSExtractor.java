package metrix.size;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class NoSExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double numberOfSignaling = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();

			for (ConstructorDeclaration constructor : declaration.getChildNodesByType(ConstructorDeclaration.class)) {
				numberOfSignaling += constructor.getThrownExceptions().size();
			}
			for (MethodDeclaration method : declaration.getMethods()) {
				numberOfSignaling += method.getThrownExceptions().size();
			}
			if (declaration.getMethods().size() > 0) {
				numberOfSignaling = numberOfSignaling / declaration.getMethods().size();
			}
			BigDecimal valueAsBigDecimal = new BigDecimal(numberOfSignaling).setScale(4, RoundingMode.HALF_EVEN);
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.NoS, valueAsBigDecimal.doubleValue()));
		}
	}

}
