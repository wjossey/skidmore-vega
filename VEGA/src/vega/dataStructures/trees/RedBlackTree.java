package vega.dataStructures.trees;

import vega.dataStructures.trees.AbstractBinarySearchTree;
import interfaces.graph.vertex.tree.RedBlackTreeNode;

/**
 * 
 * @author Weston Jossey
 *
 * @param <C>
 */
public class RedBlackTree<C extends Comparable<C>> extends AbstractBinarySearchTree<C, interfaces.graph.vertex.tree.RedBlackTreeNode<C>>{
	
	private final boolean RED = RedBlackTreeNode.RED;
	private final boolean BLACK = RedBlackTreeNode.BLACK;
	
	/**
	 * Insert a comparable into the tree
	 * @param key key to insert
	 */
	public void insert(C key) { 
		vega.graph.vertex.tree.RedBlackTreeNode<C> newNode = new vega.graph.vertex.tree.RedBlackTreeNode<C>(key, RedBlackTreeNode.RED);
		super.insertNode(newNode);
		insert_case1(newNode);
	} // end insert()
	
	/**
	 * 
	 */
	public void insertNode(RedBlackTreeNode<C> newNode){
		if(root == null){
			root = newNode;
		}else{
			RedBlackTreeNode<C> node = super.root;
			while(true){
				if(newNode.getData().compareTo(node.getData()) == 0){
					return;  //Can't insert identical key twice
				}else{
					if(newNode.getData().compareTo(node.getData()) < 0){
						if(node.getLeftChild() == null){
							node.setLeftChild(newNode);
							break;
						}else{
							node = node.getLeftChild();
						}
					}else{
						assert newNode.getData().compareTo(node.getData()) > 0;
						if(node.getRightChild() == null){
							node.setRightChild(newNode);
							break;
						}else{
							node = node.getRightChild();
						}
					}
				}
			}
			newNode.setParentNode(node);
		}
		insert_case1(newNode);
	}
	
	private void insert_case1(RedBlackTreeNode<C> node){
		if(node.getParentNode() == null){
			node.setColor(BLACK);
		}else{
			insert_case2(node);
		}
	}
	
	private void insert_case2(RedBlackTreeNode<C> node){
		if(node.getParentNode().getColor() == BLACK){
			return;
		}else{
			insert_case3(node);
		}
	}
	
	private void insert_case3(RedBlackTreeNode<C> node){
		RedBlackTreeNode<C> uncle = getUncle(node);
		RedBlackTreeNode<C> grandparent = getGrandparent(node);
		if(uncle != null && uncle.getColor() == RED){
			node.getParentNode().setColor(BLACK);
			uncle.setColor(BLACK);
			grandparent.setColor(RED);
			insert_case1(grandparent);
		}else{
			insert_case4(node);
		}
	}
	
	private void insert_case4(RedBlackTreeNode<C> node){
		
		if((node.isRightChild()) && (node.getParentNode().isLeftChild())){
			rotateLeft(node.getParentNode());
			node = node.getLeftChild();
		}else{
			if((node.isLeftChild()) && node.getParentNode().isRightChild()){
				rotateRight(node.getParentNode());
				node = node.getRightChild();
			}
		}
		
		insert_case5(node);
		
	}
	
	private void insert_case5(RedBlackTreeNode<C> node){
		RedBlackTreeNode<C> grandparent = getGrandparent(node);
		
		node.getParentNode().setColor(RedBlackTreeNode.BLACK);
		grandparent.setColor(RED);
		if((node.isLeftChild()) && (node.getParentNode().isLeftChild())){
			rotateRight(grandparent);
		}else{
			rotateLeft(grandparent);
		}
	}
	
	
	private RedBlackTreeNode<C> getSibling(RedBlackTreeNode<C> node){
		if(node.isLeftChild()){
			return node.getParentNode().getRightChild();
		}else{
			return node.getParentNode().getLeftChild();
		}
	}
	
	private RedBlackTreeNode<C> getUncle(RedBlackTreeNode<C> node){
		return getSibling(node.getParentNode());
	}
	
	private RedBlackTreeNode<C> getGrandparent(RedBlackTreeNode<C> node){
		return node.getParentNode().getParentNode();
	}
	
	private void rotateLeft(RedBlackTreeNode<C> node){
		RedBlackTreeNode<C> oldRight = node.getRightChild();
		replaceNode(node, oldRight);
		node.setRightChild(oldRight.getLeftChild());
		if(oldRight.getLeftChild() != null){
			oldRight.getLeftChild().setParentNode(node);
		}
		oldRight.setLeftChild(node);
		node.setParentNode(oldRight);
		
		
	}
	
	private void rotateRight(RedBlackTreeNode<C> node){
		RedBlackTreeNode<C> oldLeft = node.getLeftChild();
		replaceNode(node, oldLeft);
		node.setLeftChild(oldLeft.getRightChild());
		if(oldLeft.getRightChild() != null){
			oldLeft.getRightChild().setParentNode(node);
		}
		oldLeft.setRightChild(node);
		node.setParentNode(oldLeft);
	}
	
	private void replaceNode(RedBlackTreeNode<C> oldNode, RedBlackTreeNode<C> newNode) {
	    if (oldNode.getParentNode() == null) {
	        root = newNode;
	    } else {
	        if (oldNode.isLeftChild()){
	        	oldNode.getParentNode().setLeftChild(newNode);	
	        }   
	        else{
	        	oldNode.getParentNode().setRightChild(newNode);
	        }
	    }    
	    
	    if (newNode != null) {
	        newNode.setParentNode(oldNode.getParentNode());
	    }
	}
	
	/**
	 * 
	 */
    public boolean delete(C arg) {
        boolean result = false;
        C key = arg;
        RedBlackTreeNode<C> node = search(getRoot(), key);
        if (node != null) {
            deleteNode(node);
            numElements--;
            result = true;
        }
        return result;
    }
   
    private RedBlackTreeNode<C> deleteNode(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> child;
        if(isLeaf(node.getLeftChild())){
            child = node.getLeftChild();
        }else{
            child = node.getRightChild();
        }
        
        node.setData(child.getData());
        if(node.getColor() == BLACK){
            if(child.getColor() == RED){
                child.setColor(BLACK);
            }else{
                delete_case1(child);
            }
        }
        
        return node;
        
    }
    
    private boolean isLeaf(RedBlackTreeNode<C> node) {
		if(node.getLeftChild() == null && node.getRightChild() == null){
			return true;
		}else{
			return false;
		}
	}

	private void delete_case1(RedBlackTreeNode<C> node){
        if(node.getParentNode() != null){
            delete_case2(node);
        }
    }
    
    private void delete_case2(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> sibling = getSibling(node);
        
        if(sibling.getColor() == RED){
            node.getParentNode().setColor(RED);
            sibling.setColor(BLACK);
            if(node == node.getParentNode().getLeftChild()){
                rotateWithLeftChild(node.getParentNode());
            }else{
                rotateWithRightChild(node.getParentNode());
            }
        }
        
        delete_case3(node);
    }
    
    private void delete_case3(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> sibling = getSibling(node);
        
        if((node.getParentNode().getColor() == BLACK) && 
                (sibling.getColor() == BLACK) &&
                (sibling.getLeftChild().getColor() == BLACK) &&
                (sibling.getRightChild().getColor() == RED)){
            sibling.setColor(RED);
            delete_case1(node.getParentNode());
        }else{
            delete_case4(node);
        }
                
    }
    
    private void delete_case4(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> sibling = getSibling(node);
        
        if((node.getParentNode().getColor() == RED) &&
                (sibling.getColor() == BLACK) && 
                (sibling.getLeftChild().getColor() == BLACK) &&
                (sibling.getRightChild().getColor() == BLACK)){
            sibling.setColor(RED);
            node.getParentNode().setColor(BLACK);
        }else{
            delete_case5(node);
        }
    }
    
    private void delete_case5(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> sibling = getSibling(node);
        
        if((node == node.getParentNode().getLeftChild()) &&
                (sibling.getColor() == BLACK) && 
                (sibling.getLeftChild().getColor() == RED) &&
                (sibling.getRightChild().getColor() == BLACK)){
            sibling.setColor(RED);
            sibling.getLeftChild().setColor(BLACK);
            rotateWithRightChild(sibling);
        }else{
            if((node == node.getParentNode().getRightChild()) &&
                    (sibling.getColor() == BLACK) && 
                    (sibling.getRightChild().getColor() == RED) &&
                    (sibling.getLeftChild().getColor() == BLACK)){
                sibling.setColor(RED);
                sibling.getRightChild().setColor(BLACK);
                rotateWithLeftChild(sibling);
            }
        }
        
        delete_case6(node);
    }
    
    private void delete_case6(RedBlackTreeNode<C> node){
    	RedBlackTreeNode<C> sibling = getSibling(node);
        
        sibling.setColor(node.getParentNode().getColor());
        node.getParentNode().setColor(BLACK);
        if(node == node.getParentNode().getLeftChild()){
            sibling.getRightChild().setColor(BLACK);
            rotateWithLeftChild(node.getParentNode());
        }else{
            sibling.getLeftChild().setColor(BLACK);
            rotateWithRightChild(node.getParentNode());
        }
    }
}










