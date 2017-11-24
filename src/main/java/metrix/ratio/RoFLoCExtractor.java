package metrix.ratio;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class RoFLoCExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double finalyBlockLineOfCode = 0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			for (TryStmt tryStmt : declaration.getChildNodesByType(TryStmt.class)) {
				if (tryStmt.getFinallyBlock() != null && tryStmt.getFinallyBlock().isPresent()) {
					finalyBlockLineOfCode += tryStmt.getFinallyBlock().get().getEnd().get().line
							- tryStmt.getFinallyBlock().get().getBegin().get().line;
				}
			}
			double classLoC = declaration.getEnd().get().line - declaration.getBegin().get().line;
			if (classLoC > 0) {
				finalyBlockLineOfCode = finalyBlockLineOfCode / classLoC;
			}
			BigDecimal valueAsBigDecimal = new BigDecimal(finalyBlockLineOfCode).setScale(4, RoundingMode.HALF_EVEN);

			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.RoFLoC, valueAsBigDecimal.doubleValue()));
		}
	}
}
