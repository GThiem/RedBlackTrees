package frame;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import lab.RedBlackTree;
import lab.CompletelyFairScheduler;

/**
 * Do NOT change anything in this class!
 * 
 * The test cases defined by this class are used to test if the implementation
 * works correctly. This class is also responsible for outputting to the
 * console.
 * 
 */

@DisplayName("Exercise 4 - Red Black Trees")
class PublicTests {
	
	protected int correct = 0;
	protected Duration timeout = Duration.ofSeconds(30);
	
	protected static int checkRedBlack(RedBlackTree tree, TreeNode node) {
		if( node == tree.nil() ) {
			return 1;
		}
		int blackDepth = 0;
		if( node.color == TreeNode.NodeColor.RED ) {
			if( node.left.color == TreeNode.NodeColor.RED || node.right.color == TreeNode.NodeColor.RED ) {
				return -1;
			}
		} else {
			blackDepth += 1;
		}
		int blackDepthLeft = checkRedBlack(tree, node.left);
		int blackDepthRight = checkRedBlack(tree, node.right);
		if( blackDepthLeft != blackDepthRight || blackDepthLeft < 0 ) {
			return -1;
		}
		blackDepth += blackDepthLeft;
		return blackDepth;
	}
	
	protected int countElements(RedBlackTree tree, TreeNode node) {
		if( node == tree.nil() ) {
			return 0;
		}
		return countElements(tree, node.left) + countElements(tree, node.right) + 1;
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("RedBlackTree tests")
	class RedBlackTreeTests {
		
		@DisplayName("RBTrees_Test_1")
		@Test
		public void test1() {
			System.out.println("Starting RBTrees_Test_1...");
			TreeNodeTestfileReader reader = new TreeNodeTestfileReader("tests/public/Testfile_Tree_1");
			ArrayList<TreeNode> treeNodes = reader.readFile();
			
			assertTimeoutPreemptively(timeout, () -> {
				try {
					RedBlackTree tree = new RedBlackTree();
					for( int i=0; i<treeNodes.size(); i++ ) {
						tree.insert(treeNodes.get(i));
					}
					
					System.out.println("Starting tests after insertion...");
					assertEquals(treeNodes.size(), countElements(tree, tree.root()), "Wrong number of elements in the tree!");
					assertEquals(TreeNode.NodeColor.BLACK,tree.root().color, "Root is not a black node!");
					assertTrue(checkRedBlack(tree, tree.root()) >= 0, "Red-black properties not satisfied!");
					
					for( int j=0; j<1024; j++ ) {
						TreeNode node = tree.search(j);
						assertNotEquals(tree.nil(), node, "Node not found in the tree!");
						tree.delete(node);	
					}
					
					System.out.println("Starting tests after deletion...");
					assertEquals(treeNodes.size()-1024, countElements(tree, tree.root()), "Wrong number of elements in the tree!");
					assertEquals(TreeNode.NodeColor.BLACK,tree.root().color, "Root is not a black node!");
					assertTrue(checkRedBlack(tree, tree.root()) >= 0, "Red-black properties not satisfied!");
					for( int k=-1024; k<0; k++ ) {
						assertNotEquals(tree.nil(), tree.search(k), "Node not found in the tree!");
					}
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			}, () -> {
				System.out.println("RBTRees_Test_1: Execution timed out after: " + timeout.getSeconds()
									+ " seconds");
				return "Test failed!";
			});
			
			System.out.println("RBTrees_Test_1 successful!");
		}
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("CompletelyFairScheduler tests")
	class CompletelyFairSchedulerTests {
		
		@DisplayName("Scheduler_Test_1")
		@Test
		public void test1() {
			System.out.println("Starting Scheduler_Test_1");
			CompletelyFairScheduler scheduler = new CompletelyFairScheduler(10000);
			
			assertTimeoutPreemptively(timeout, () -> {
				try {
					ProcessTestfileReader reader = new ProcessTestfileReader("tests/public/Testfile_Process_1");
					ArrayList<Process> processes = reader.readFile();
					for( int i=0; i<processes.size(); i++ ) {
						scheduler.add(processes.get(i));
						scheduler.run(1);
					}
					
					assertEquals(processes.size(), countElements(scheduler.getTree(), scheduler.getTree().root()), "Wrong size of Tree!");
					scheduler.run(5*30*200);
					assertTrue(scheduler.getTree().root() == scheduler.getTree().nil(), "All Processes should be finished!");
					for( int i=0; i<processes.size(); i++ ) {
						assertTrue(processes.get(i).finished());
					}
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			}, () -> {
				System.out.println("Scheduler_Test_1: Execution timed out after: " + timeout.getSeconds()
									+ " seconds");
				return "Test failed!";
			});
			
			System.out.println("Scheduler_Test_1 successful!");
		}
		
		@DisplayName("Scheduler_Test_2")
		@Test
		public void test2() {
			System.out.println("Starting Scheduler_Test_2");
			CompletelyFairScheduler scheduler = new CompletelyFairScheduler(10000);
			
			assertTimeoutPreemptively(timeout, () -> {
				try {
					ProcessTestfileReader reader = new ProcessTestfileReader("tests/public/Testfile_Process_2");
					ArrayList<Process> processes = reader.readFile();
					for( int i=0; i<processes.size(); i++ ) {
						scheduler.add(processes.get(i));
						scheduler.run(1);
					}
					
					assertEquals(processes.size(), countElements(scheduler.getTree(), scheduler.getTree().root()), "Wrong size of Tree!");
					scheduler.run(60);
					assertTrue(processes.get(0).finished(), "Processes with less execution time should be preferred!");
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			}, () -> {
				System.out.println("Scheduler_Test_2: Execution timed out after: " + timeout.getSeconds()
									+ " seconds");
				return "Test failed!";
			});
			System.out.println("Scheduler_Test_2 successful!");
		}
		
		@DisplayName("Scheduler_Test_3")
		@Test
		public void test3() {
			System.out.println("Starting Scheduler_Test_3");
			CompletelyFairScheduler scheduler = new CompletelyFairScheduler(10000);
			
			assertTimeoutPreemptively(timeout, () -> {
				try {
					ProcessTestfileReader reader = new ProcessTestfileReader("tests/public/Testfile_Process_2");
					ArrayList<Process> processes = reader.readFile();
					for( int i=0; i<processes.size(); i++ ) {
						scheduler.add(processes.get(i));
					}
					
					assertEquals(processes.size(), countElements(scheduler.getTree(), scheduler.getTree().root()), "Wrong size of Tree!");
					for( int i=0; i<processes.size(); i++ ) {
						assertEquals(processes.get(i).name(), scheduler.getTree().search(i).value.name(), "Process not found at the right position!");
						assertEquals(0, processes.get(0).executionTime(), "Not run yet, so execution time should be 0!");
					}
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			}, () -> {
				System.out.println("Scheduler_Test_3: Execution timed out after: " + timeout.getSeconds()
									+ " seconds");
				return "Test failed!";
			});
			System.out.println("Scheduler_Test_3 successful!");
		}
	}

}