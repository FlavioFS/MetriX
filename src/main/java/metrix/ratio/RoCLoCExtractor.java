package metrix.ratio;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class RoCLoCExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double cacthBlockLineOfCode = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			for (CatchClause catchClause : declaration.getChildNodesByType(CatchClause.class)) {
				cacthBlockLineOfCode += catchClause.getEnd().get().line - catchClause.getBegin().get().line;
			}
			double classLoC = declaration.getEnd().get().line - declaration.getBegin().get().line;
			if (classLoC > 0) {
				cacthBlockLineOfCode = cacthBlockLineOfCode / classLoC;
			}
			BigDecimal valueAsBigDecimal = new BigDecimal(cacthBlockLineOfCode).setScale(4, RoundingMode.HALF_EVEN);
			
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.RoCLoC, valueAsBigDecimal.doubleValue()));
		}
	}
}
