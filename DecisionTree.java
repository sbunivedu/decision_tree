import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

public class DecisionTree{
  private LinkedBinaryTree<String> tree;

  /**
   * Builds the decision tree based on the contents of the given file
   *
   * @param filename the name of the input file
   * @throws FileNotFoundException if the input file is not found
   */
  public DecisionTree(String filename) throws FileNotFoundException
  {
    File inputFile = new File(filename);
    LinkedList<String> elements = new LinkedList<String>();
    Scanner scan = new Scanner(inputFile);
    while(scan.hasNext()){
      elements.add(scan.nextLine());
    }
    tree = build_tree(elements);
  }

  private LinkedBinaryTree<String> build_tree(LinkedList<String> elements){
    String element = elements.remove();
    //System.out.println("process "+line);
    if(element.charAt(0) == 'A'){
      return new LinkedBinaryTree<String>(element);
    }else{
      LinkedBinaryTree<String> left = build_tree(elements);
      LinkedBinaryTree<String> right = build_tree(elements);
      LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>(
        element, left, right);
      return tree;
    }
  }

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to the Guessing Game");

    DecisionTree rules = null;
    boolean done = true;
    do{
      System.out.print("File name? ");
      String fileName = scan.nextLine();
      try{
        rules = new DecisionTree(fileName);
        done = true;
      }catch(FileNotFoundException e){
        done = false;
      }
    }while(!done);

    System.out.println("Please think of an object for me to guess...\n");

    LinkedBinaryTree<String> current = rules.tree;
    boolean end = false;
    while(!end){
      String element = current.getRootElement();
      if(element.charAt(0) == 'Q'){
        System.out.print(element.substring(2)+" ");
        String answer = scan.nextLine();
        //System.out.println("your anser is "+answer);
        if(answer.toLowerCase().charAt(0) == 'y'){
          current = current.getLeft();
        }else{
          current = current.getRight();
        }
      }else{
        System.out.print("Would your object happen to be "
          +current.getRootElement().substring(2)+" ");
        String answer = scan.nextLine();
        if(answer.toLowerCase().charAt(0) == 'y'){
          System.out.println("Great, I got it right!");
        }else{
          System.out.println("Sorry, I cannot guess it.");
        }
        end = true;
      }
    }
  }
}
