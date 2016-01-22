#include <stdio.h>
#include <stdlib.h>

#define RED 0
#define BLACK 1

// node definition
typedef struct node {
	int value;
	int color;
	struct node *left, *right, *parent;
} node_t;




/***************************/
/****     Rotation     *****/
/***************************/

// left rotate
void leftRotate (node_t ** root, node_t * x) {
	
	node_t * y = x->right;
	
	x->right = y->left;
	
	if (y->left != NULL)
		y->left->parent = x;
	
	y->parent = x->parent;
	
	if (x->parent == NULL)
		(*root) = y;
	else if (x == x->parent->left)
		x->parent->left = y;
	else
		x->parent->right = y;
	
	y->left = x;
	x->parent = y;
}


// right rotate
void rightRotate (node_t ** root, node_t * x) {
	
	node_t * y = x->left;
	
	x->left = y->right;
	
	if (y->right != NULL)
		y->right->parent = x;
	
	y->parent = x->parent;
	
	if (x->parent == NULL)
		(*root) = y;
	else if (x == x->parent->left)
		x->parent->left = y;
	else
		x->parent->right = y;
	
	y->right = x;
	x->parent = y;
	
}



/****************************/
/****     Insertion     *****/
/****************************/

// insert Fix up
void rbInsertFixUp (node_t ** root, node_t * fixupNode) {
	
	// while loop iterates until fixupNode == root or parent == Black node
	while (fixupNode != *root && fixupNode->parent->color == RED) {
		
		node_t * uncleNode;
		
		if (fixupNode->parent == fixupNode->parent->parent->left)
			uncleNode = fixupNode->parent->parent->right;
		else
			uncleNode = fixupNode->parent->parent->left;
		
		// when both parent and uncle are Red node
		if (uncleNode != NULL && uncleNode->color == RED) {
			uncleNode->color = BLACK;
			fixupNode->parent->color = BLACK;
			fixupNode->parent->parent->color = RED;
			fixupNode = fixupNode->parent->parent;
		}
		// when uncle is black
		else {
			
			// case 1 : parent == left, fixupNode == left
			if (fixupNode->parent == fixupNode->parent->parent->left &&
				fixupNode == fixupNode->parent->left) {
				
				fixupNode->parent->color = BLACK;
				fixupNode->parent->parent->color = RED;
				rightRotate(root, fixupNode->parent->parent);
			}
			// case 2 : parent == left, fixupNode == right
			else if (fixupNode->parent == fixupNode->parent->parent->left &&
					 fixupNode == fixupNode->parent->right) {
				
				fixupNode = fixupNode->parent;
				leftRotate(root, fixupNode);
				
				fixupNode->parent->color = BLACK;
				fixupNode->parent->parent->color = RED;
				rightRotate(root, fixupNode->parent->parent);
			}
			// case 3 : parent == right, fixupNode == right
			else if (fixupNode->parent == fixupNode->parent->parent->right &&
					 fixupNode == fixupNode->parent->right) {
				
				fixupNode->parent->color = BLACK;
				fixupNode->parent->parent->color = RED;
				leftRotate(root, fixupNode->parent->parent);
			}
			// case 4 : parent == right, fixupNode == left
			else if (fixupNode->parent == fixupNode->parent->parent->right &&
					 fixupNode == fixupNode->parent->left) {
				
				fixupNode = fixupNode->parent;
				rightRotate(root, fixupNode);
				
				fixupNode->parent->color = BLACK;
				fixupNode->parent->parent->color = RED;
				leftRotate(root, fixupNode->parent->parent);
			}
			
		} // end of else block (uncle == black)
		
	} // end of while loop
	
	(*root)->color = BLACK;
}


// insert
void rbInsert (node_t ** root, int key) {
	
	node_t * newNode = (node_t *) malloc(sizeof(node_t));
	
	newNode->value = key;
	newNode->left = newNode->right = newNode->parent = NULL;
	
	if ((*root) == NULL) {
		newNode->color = BLACK;
		(*root) = newNode;
	}
	else {
		node_t * parentNode = NULL;
		node_t * curNode = (*root);
		
		while (curNode != NULL) {
			parentNode = curNode;
			if (newNode->value < curNode->value)
				curNode = curNode->left;
			else
				curNode = curNode->right;
		}
		newNode->parent = parentNode;
		
		if (newNode->value < parentNode->value)
			parentNode->left = newNode;
		else
			parentNode->right = newNode;
		
		newNode->color = RED;
		
		rbInsertFixUp(root, newNode);
	}
}



/*************************/
/****     Search     *****/
/*************************/

// search
node_t * rbSearchNode(node_t * node, int key) {

	node_t * searchNode = NULL;
	
	if (node == NULL) return searchNode;
	
	if (node->value == key)
		searchNode = node;
	else if (node->value < key)
		searchNode = rbSearchNode(node->right, key);
	else
		searchNode = rbSearchNode(node->left, key);

	return searchNode;
}



/***************************/
/****     Deletion     *****/
/***************************/

// find Minimum value node greater than given node
node_t * rbFindMinNode(node_t * node) {
	
	node_t * curNode = node;
	
	while (curNode->left != NULL) {
		curNode = curNode->left;
	}
	return curNode;
}


// transplant
void rbTransplant(node_t ** root, node_t * oldNode, node_t * plantNode) {
	
	if (oldNode->parent == NULL)
		(*root) = plantNode;
	else if (oldNode == oldNode->parent->left)
		oldNode->parent->left = plantNode;
	else
		oldNode->parent->right = plantNode;

	if (plantNode != NULL)
		plantNode->parent = oldNode->parent;
}


// delete Fix up
void rbDeleteFixup (node_t ** root, node_t * fixupNode) {
	
	node_t * sibling;
	
	// while loop iterates until fixupNode == rootNode or fixupNode's color is RED
	while (fixupNode != NULL && fixupNode != (*root) && fixupNode->color == BLACK) {
		
		// if fixupNode is LEFT of parent
		if (fixupNode == fixupNode->parent->left) {
		
			sibling = fixupNode->parent->right;
			
			// case 4 : sibling == red
			// at the end of IF block, case 4 changes to one of cases 1 through 3
			if (sibling != NULL && sibling->color == RED) {
				sibling->color = BLACK;
				fixupNode->parent->color = RED;
				leftRotate(root, fixupNode->parent);
				sibling = fixupNode->parent->right;
			}
			
			if (sibling->left != NULL && sibling->right != NULL) {
			
				// case 1 : sibling's left == black, sibling's right == black
				if (sibling->left->color == BLACK && sibling->right->color == BLACK) {
					sibling->color = RED;
					fixupNode = fixupNode->parent;
				}
				else {
					// case 3 : sibling's right == black, sibling's left == red
					if (sibling->right->color == BLACK) {
						sibling->left->color = BLACK;
						sibling->color = RED;
						rightRotate(root, sibling);
						sibling = fixupNode->parent->right;
					}
					// case 2 : sibling's right == red
					sibling->color = fixupNode->parent->color;
					fixupNode->parent->color = BLACK;
					sibling->right->color = BLACK;
					leftRotate(root, fixupNode->parent);
					
					fixupNode = (*root); // break out of while loop
				}
			}
		}
		// if fixupNode is RIGHT of parent
		else {
			
			sibling = fixupNode->parent->left;
			
			// case 4' : sibling == red
			// at the end of IF block, case 4' changes to one of cases 1' through 3'
			if (sibling != NULL && sibling->color == RED) {
				sibling->color = BLACK;
				fixupNode->parent->color = RED;
				rightRotate(root, fixupNode->parent);
				sibling = fixupNode->parent->left;
			}
			
			if (sibling->left != NULL && sibling->right != NULL) {
				
				// case 1' : sibling's left == black, sibling's right == black
				if (sibling->left->color == BLACK && sibling->right->color == BLACK) {
					sibling->color = RED;
					fixupNode = fixupNode->parent;
				}
				else {
					// case 3' : sibling's left == black, sibling's right == red
					if (sibling->left->color == BLACK) {
						sibling->right->color = BLACK;
						sibling->color = RED;
						leftRotate(root, sibling);
						sibling = fixupNode->parent->left;
					}
					
					// case 2' : sibling's left == red
					sibling->color = fixupNode->parent->color;
					fixupNode->parent->color = BLACK;
					sibling->left->color = BLACK;
					rightRotate(root, fixupNode->parent);
					fixupNode = (*root);
				}
			}
		} // end of else block (fixupNode == right of parent)
		
	} // end of while loop
	
	if (fixupNode != NULL)
		fixupNode->color = BLACK;
}


// delete
void rbDelete (node_t ** root, int key) {
	
	int eraseColor;
	node_t * fixupNode;
	node_t * succesor;
	node_t * eraseNode = rbSearchNode(*root, key);
	
	if (eraseNode == NULL) return;
	
	eraseColor = eraseNode->color;
	
	// case A : if eraseNode does not have left child
	if (eraseNode->left == NULL) {
		fixupNode = eraseNode->right;
		rbTransplant(root, eraseNode, fixupNode);
	}
	// case B : if eraseNode does not have right child
	else if (eraseNode->right == NULL) {
		fixupNode = eraseNode->left;
		rbTransplant(root, eraseNode, fixupNode);
	}
	// case C : if eraseNode has both left child and right child
	else {
		succesor = rbFindMinNode(eraseNode->right);
		eraseColor = succesor->color;
		fixupNode = succesor->right;
		
		// when successor is child of eraseNode
		if (succesor->parent == eraseNode) {
			if (fixupNode != NULL) {
				fixupNode->parent = succesor;
			}
		}
		// transplant successor's right(fixupNode) into successor's position
		else {
			rbTransplant(root, succesor, fixupNode);
			succesor->right = eraseNode->right;
			succesor->right->parent = succesor;
		}
		
		// transplant successor into eraseNode's position
		rbTransplant(root, eraseNode, succesor);
		succesor->left = eraseNode->left;
		succesor->left->parent = succesor;
		succesor->color = eraseNode->color;
	}
	
	// when erase node color == black, adjust black height balance
	if (eraseColor == BLACK)
		rbDeleteFixup(root, fixupNode);

	free(eraseNode);
}



/***************************************/
/****     Print Red Black Tree     *****/
/***************************************/


// print red black tree by inorder traversal
void inorder(node_t * root) {
	
	if (root == NULL)
		return;
	
	inorder(root->left);
	printf("%d ", root->value);
	inorder(root->right);
}



/********************************/
/****     Main Function     *****/
/********************************/

// main function
int main(int argc, const char * argv[]) {

	// Test case 1 : insert one element, and delete
	node_t * root1 = NULL;
	printf("< After Insertion >\n");
	rbInsert(&root1, 1);
	
	printf("red black tree inorder traversal : \n");
	inorder(root1);
	
	printf("\n < After Deletion> \n");
	
	rbDelete(&root1, -4);
	rbDelete(&root1, 1);
	printf("red black tree inorder traversal : \n");
	inorder(root1);
	
	printf("\n\n");

	// Test case 2 : insert elements in decreasing order, and delete
	node_t * root2 = NULL;
	printf("< After Insertion >\n");
	rbInsert(&root2, 3);
	rbInsert(&root2, 2);
	rbInsert(&root2, 1);
	rbInsert(&root2, 0);
	
	printf("red black tree inorder traversal : \n");
	inorder(root2);
	
	printf("\n < After Deletion> \n");
	
	rbDelete(&root2, -4);
	rbDelete(&root2, 1);
	rbDelete(&root2, 0);
	printf("red black tree inorder traversal : \n");
	inorder(root2);
	
	printf("\n\n");
	
	
	// Test case 3 : insert random elements, and delete
	node_t * root = NULL;
	printf("< After Insertion >\n");
	rbInsert(&root, 11);
	rbInsert(&root, -1);
	rbInsert(&root, 2);
	rbInsert(&root, -4);
	rbInsert(&root, 6);
	rbInsert(&root, 3);
	rbInsert(&root, 9);
	rbInsert(&root, 5);
	
	printf("red black tree inorder traversal : \n");
	inorder(root);
	
	printf("\n < After Deletion> \n");
	
	rbDelete(&root, -4);
	rbDelete(&root, 6);
	rbDelete(&root, 11);
	printf("red black tree inorder traversal : \n");
	inorder(root);
	
	printf("\n");
	
    return 0;
}
