package util;

import com.github.javaparser.ast.Node;

/**
 * An iterator that implements input project's directory exploration (recursive tree traversal).
 * @author Flavio Freitas
 *
 */
public class NodeIterator {
	public interface NodeHandler {
		boolean handle(Node node);
	}

	private NodeHandler nodeHandler;

	public NodeIterator(NodeHandler nodeHandler) {
		this.nodeHandler = nodeHandler;
	}

	public void explore(Node node) {
		if (nodeHandler.handle(node)) {
			for (Node child : node.getChildNodes()) {
				explore(child);
			}
		}
	}
}