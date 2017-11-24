package metrix.coupling;

import java.util.HashSet;
import java.util.Set;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class RCoExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			Set<String> exceptions = new HashSet<>();
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();

			for (ThrowStmt throwStmt : declaration.getChildNodesByType(ThrowStmt.class)) {
				if (throwStmt.getExpression() instanceof ObjectCreationExpr) {
					ClassOrInterfaceType exception = ((ObjectCreationExpr) throwStmt.getExpression()).getType();
					exceptions.add(exception.getNameAsString());
				}
			}
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.RCo, (double) exceptions.size()));
		}
	}
}
