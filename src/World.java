import java.io.*;
import java.util.ArrayList;

public class World
{
    private BinaryTree binaryTree;

    public World()
    {
        this.binaryTree = new BinaryTree();
    }

    public void buildWorld(String filename)
    {
        try
        {
            String workingDir = "src/";
            File file = new File(workingDir.concat(filename.concat(".txt")));
            FileInputStream fileInputStream = new FileInputStream(file.getPath());
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader input = new BufferedReader(new InputStreamReader(dataInputStream));

            ArrayList<City> cities = new ArrayList<>();
            for (String line = input.readLine(); line != null; line = input.readLine())
            {
                String[] tokens = line.trim().split(";");
                City city = new City(tokens[0], tokens[1]);
                city.setLongitude(Integer.parseInt(tokens[5]));     // longtitude
                city.setLongMinutes(Integer.parseInt(tokens[6]));   // minutes
                city.setWE(tokens[7]);                              // west/east
                city.calculateToDecimal();
                cities.add(city);
            }

            // set root
            BinaryTree.Node root = new BinaryTree.Node(cities.get(0));

            for (int i = 1; i < cities.size(); i++)
            {
                binaryTree.insertAlpha(root, cities.get(i));
                //binaryTree.insertLong(root, cities.get(i));
            }

            System.out.println();
            binaryTree.printInOrder(root);

        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }

}
