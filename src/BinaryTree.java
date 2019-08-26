import java.util.Comparator;

public class BinaryTree implements Comparator<City>
{
    public BinaryTree() {}

    @Override
    public int compare(City c1, City c2)
    {
        int value;
        value = c1.getCountryName().compareTo(c2.getCountryName());
        if (value == 0) value = c1.getCityName().compareTo(c2.getCityName());
        return value;
    }

    static class Node
    {
        Node left;
        Node right;
        City data;

        public Node(City data)
        {
            this.data = data;
        }
    }

    /** Sort cities in west/east using longtitude*/
    public void insertLong(Node root, City data) {

        //if new city longtitude is less (then west)
        if (data.getLongDecimal() < root.data.getLongDecimal())
        {
            //if left node is not empty, go down a level
            if (root.left != null)
            {
                insertLong(root.left, data);
            }
            else //else insert in the left node
            {
                System.out.println("Inserted " + data.getCityName() + data.getCountryName() + " to left of " + root.data.getCityName() + root.data.getCityName());
                root.left = new Node(data);
            }
        }
        else if (data.getLongDecimal() > root.data.getLongDecimal()) //if new city longtitude is higher (then east)
        {
            //if right node is not empty, go down a level
            if (root.right != null)
            {
                insertLong(root.right, data);
            }
            else //else insert in right node
            {
                System.out.println("Inserted " + data.getCityName() + data.getCountryName() + " to right of " + root.data.getCityName() + root.data.getCountryName());
                root.right = new Node(data);
            }
        }
    }

    /** Sort elements in countries then cities, alphabetical order*/
    public void insertAlpha(Node root, City city)
    {
        if (compare(city, root.data) < 0)
        {
            //if left node is not empty, go down a level
            if (root.left != null)
            {
                insertAlpha(root.left, city);
            }
            else //else create node and insert
            {
                System.out.println("Inserted " + city.getCityName() + " <-- " + root.data.getCityName());
                root.left = new Node(city);
            }
        }
        else if (compare(city, root.data) > 0)
        {
            //if right node is not empty, go down a level
            if (root.right != null)
            {
                insertAlpha(root.right, city);
            }
            else //else create node and insert
            {
                System.out.println("Inserted " + city.getCityName() + " --> " + root.data.getCityName());
                root.right = new Node(city);
            }
        }
    }

    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println("Traversed " + node.data.getCountryName() + " " + node.data.getCityName());
            printInOrder(node.right);
        }
    }

}