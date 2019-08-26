import java.util.Comparator;

public class BinaryTree implements Comparator<City>
{
    public BinaryTree()
    {
    }

    @Override
    public int compare(City c1, City c2)
    {
        int value = c1.getCountryName().compareTo(c2.getCountryName());
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

    /**
     * Sort cities into west or east by given longitude
     *
     * @param root
     * @param data
     */
    public void insertLong(Node root, City data)
    {

        // if longtitude of new city is less (i.e. west)
        if (data.getLongDecimal() < root.data.getLongDecimal())
        {
            // if left node is not empty, go down a level
            if (root.left != null)
            {
                insertLong(root.left, data);
            }
            else // else insert in the left node
            {
                System.out.println("Inserted " + data.getCityName() + data.getCountryName() + " to left of " + root.data.getCityName() + root.data.getCityName());
                root.left = new Node(data);
            }
        }
        else if (data.getLongDecimal() > root.data.getLongDecimal()) // if longtitude of new city is higher (i.e. east)
        {
            // if right node is not empty, go down a level
            if (root.right != null)
            {
                insertLong(root.right, data);
            }
            else // else insert in the right node
            {
                System.out.println("Inserted " + data.getCityName() + data.getCountryName() + " to right of " + root.data.getCityName() + root.data.getCountryName());
                root.right = new Node(data);
            }
        }
    }

    /**
     * Sort elements by countries then by cities in alphabetical order
     *
     * @param root
     * @param city
     */
    public void insertAlpha(Node root, City city)
    {
        if (compare(city, root.data) < 0)
        {
            if (root.left != null)
            {
                insertAlpha(root.left, city);
            }
            else
            {
                System.out.println("Inserted " + city.getCityName() + " <-- " + root.data.getCityName());
                root.left = new Node(city);
            }
        }
        else if (compare(city, root.data) > 0)
        {
            if (root.right != null)
            {
                insertAlpha(root.right, city);
            }
            else
            {
                System.out.println("Inserted " + city.getCityName() + " --> " + root.data.getCityName());
                root.right = new Node(city);
            }
        }
    }

    /**
     * Traverse binary tree and print to console
     *
     * @param node
     */
    public void printInOrder(Node node)
    {
        if (node != null)
        {
            printInOrder(node.left);
            System.out.println("Traversed " + node.data.getCountryName() + " " + node.data.getCityName());
            printInOrder(node.right);
        }
    }

}