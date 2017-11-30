package metrix.concern;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;

import model.Measure;
import model.MeasureDataset;
import model.MetricSuite;

/**
 * Handling Density of Scattering: 
 * The density of scattering of handling code across methods of a given class. 
 * Formula reference: http://www.cs.columbia.edu/~eaddy/publications/tse.online.pdf  
 * @author Flavio Freitas
 *
 */
public class HDoSExtractor extends VoidVisitorAdapter<JavaParserFacade> {

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, JavaParserFacade javaParserFacade) {
		super.visit(declaration, javaParserFacade);
		if (!declaration.isInterface() && !declaration.isLocalClassDeclaration() && !declaration.isNestedType()) {
			Double handlingLoCOfClass = 0.0;

			Vector<Double> handlingLoCByMethod = new Vector<Double>();

			BigDecimal handlingDegreeOfScatteringAsBigDecimal;

			BigDecimal summationAsBigDecimal;

			Double handlingDegreeOfScattering = 1.0;

			Double summation = 0.0;

			String qualifiedName = javaParserFacade.getTypeDeclaration(declaration).getQualifiedName();

			if (!declaration.isInterface() && declaration.getMethods().size() > 0
					&& !declaration.getChildNodesByType(CatchClause.class).isEmpty()) {

				for (CatchClause catchClause : declaration.getChildNodesByType(CatchClause.class)) {
					handlingLoCOfClass += catchClause.getEnd().get().line - catchClause.getBegin().get().line;
				}

				handlingLoCOfClass = (handlingLoCOfClass == 0.0) ? (1) : (handlingLoCOfClass);

				for (MethodDeclaration method : declaration.getMethods()) {
					if (!method.isAbstract()) {
						if (!method.getChildNodesByType(CatchClause.class).isEmpty()) {
							double catchLoC = 0;
							for (CatchClause clause : method.getChildNodesByType(CatchClause.class)) {
								catchLoC += (double) (clause.getEnd().get().line - clause.getBegin().get().line);
							}
							handlingLoCByMethod.add(catchLoC);
						} else {
							handlingLoCByMethod.add(0.0);
						}
					}
				}

				for (Double handlingLoCOfMethod : handlingLoCByMethod) {
					summation += Math
							.pow(((handlingLoCOfMethod / handlingLoCOfClass) - (1.0 / handlingLoCByMethod.size())), 2);
				}

				summationAsBigDecimal = new BigDecimal(summation).setScale(4, RoundingMode.HALF_EVEN);

				if (handlingLoCByMethod.size() > 1) {
					handlingDegreeOfScattering = 1.0000
							- ((handlingLoCByMethod.size() * summationAsBigDecimal.doubleValue())
									/ (handlingLoCByMethod.size() - 1.0));
				} else {
					handlingDegreeOfScattering = 0.0000;
				}
			}

			handlingDegreeOfScatteringAsBigDecimal = new BigDecimal(handlingDegreeOfScattering).setScale(4,
					RoundingMode.HALF_EVEN);
			MeasureDataset.store(qualifiedName,
					new Measure(MetricSuite.HDoS, handlingDegreeOfScatteringAsBigDecimal.doubleValue()));
		}
	}

}
