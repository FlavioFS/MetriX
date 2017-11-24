package metrix.coupling;

import java.util.HashSet;
import java.util.Set;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

public class HCoExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			Set<String> exceptions = new HashSet<>();
			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();
			for (CatchClause catchClause : declaration.getChildNodesByType(CatchClause.class)) {
				Type exception = catchClause.getParameter().getType();
				exceptions.add(exception.toString());
			}
			MeasureDataset.store(qualifiedName, new Measure(MetricSuite.HCo, (double) exceptions.size()));
		}
	}
}
