package metrix.usage;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class EHMUExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			double ehmu = 0.0;
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			ehmu += declaration.getChildNodesByType(TryStmt.class).size();
			ehmu += declaration.getChildNodesByType(CatchClause.class).size();
			ehmu += declaration.getChildNodesByType(ThrowStmt.class).size();
			ehmu += declaration.getChildNodesByType(ConstructorDeclaration.class).stream()
					.filter(f -> f.getThrownExceptions().size() > 0).count();
			ehmu += declaration.getChildNodesByType(MethodDeclaration.class).stream()
					.filter(f -> f.getThrownExceptions().size() > 0).count();
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.EHMU, ((ehmu > 0.0) ? (1.0) : (0.0))));
		}
	}
}
