import java.util.ArrayList;
import java.util.List;

public class MadisonBuffet
{
    HashTableMap<String, Double> restaurants;
    List<String> names;

    public MadisonBuffet()
    {
        restaurants = new HashTableMap<>();
        names = new ArrayList<>();
    }

    public void showAll()
    {
        System.out.println("There are currently " + restaurants.size() + " restaurants in the dataset: ");
        for(String name: names)
        {
            peek(name);
        }
    }

    public void addRestaurant(String name, double rating)
    {
        if(restaurants.put(name,rating))
        {
            names.add(name);
        };
    }

    public double removeRestaurant(String name)
    {
        double rating = restaurants.get(name);
        restaurants.remove(name);
        names.remove(name);
        return rating;
    }

    public void clear()
    {
        restaurants.clear();
    }

    public int size()
    {
        return restaurants.size();
    }

    public void peek(String name)
    {
        double rating = restaurants.get(name);
        System.out.println("Restaurant: " + name + " rating: "+rating);
    }
}
