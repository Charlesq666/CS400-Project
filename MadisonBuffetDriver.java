// --== CS400 File Header Information ==--
// Name: Youlin Qu
// Email: yqu39@wisc.edu
// Team: NC
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>


import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Description: This is a class that intended to use MadisonBuffet.java and give users a
 * way to interact with the program with certain commands.
 *
 */
public class MadisonBuffetDriver
{
    private MadisonBuffet madisonBuffet; //private MadisonBuffet use to organize and save the data

    public MadisonBuffetDriver()
    {
        madisonBuffet = new MadisonBuffet();
    }
    /**
     * processing command, if command is
     * a : add restaurant with its name and ratings
     * af: add bunch of restaurant in file
     * r : remove restaurant with name
     * s : show all the restaurant/ show restaurant by a specific name.
     * b : back to menu
     * q : quit
     * @param command a string should be in lowercase and trimmed
     */
    public void processingCommand(String command)
    {
        Scanner sc = new Scanner(command);
        String [] commands = new String[3];
        int index=0;
        while(sc.hasNext())
        {
            commands[index++] = sc.next();
        }
        try
        {
            switch (commands[0])
            {
                case "a":
                    try
                    {
                        madisonBuffet.addRestaurant(commands[1],Double.parseDouble(commands[2]));
                    }
                    catch(NumberFormatException | NullPointerException npe)
                    {
                        System.out.println("Please type with the following way: <a> <name> <a number>");
                    }
                    break;

                case "af":
                    try
                    {
                        addFile(commands[1]);
                    }
                    catch(NullPointerException npe)
                    {
                        System.out.println("Please enter a file name");
                    }
                    break;

                case "r":
                    try
                    {
                        madisonBuffet.removeRestaurant(commands[1]);
                    }
                    catch(NoSuchElementException nse)
                    {
                        System.out.println("There appears to be no such restaurant named: "+ commands[1]);
                    }
                    break;

                case "s":
                    if(commands[1] == null) madisonBuffet.showAll();
                    else
                    {
                        try
                        {
                            madisonBuffet.peek(commands[1]);
                        }
                        catch(NoSuchElementException nse)
                        {
                            System.out.println("There appears to be no such restaurant named: "+ commands[1]);
                        }
                    }
                    break;

                case "b":
                case "q":
                    System.out.println("Thank you for using the program!");
                    break;
                default:
                    System.out.println("Warning - Command not recognized: " + command);
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("Unexpected exception happens: ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * private helper to implement the addFile functionality
     * @param fileName name of the file
     */
    private void addFile(String fileName)
    {
        try
        {
            Scanner sc = new Scanner(new File(fileName));
            Scanner sc2;
            while(sc.hasNext())
            {
                sc2 = new Scanner(sc.nextLine());
                String name = sc2.next();
                if(name.equals("")) break;
                double rating = Double.parseDouble(sc2.next());
                madisonBuffet.addRestaurant(name,rating);
                System.out.println("Adding restaurant: "+name);
            }
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("File doesn't exist");
        }
        catch(NoSuchElementException | NumberFormatException e)
        {
            System.out.println("Your file contents are not in the right format");
        }
    }

    /**
     * run method to start the program and keeps it running until user enter 'q';
     * @param sc Scanner object
     */
    public void run(Scanner sc)
    {
        String command = "None";
        while(!command.equals("q"))
        {
            displayMenu();
            command = sc.nextLine().trim().toLowerCase();
            processingCommand(command);
        }
    }
    /**
     * private helper method to display the menu
     */
    private void displayMenu()
    {
        System.out.println("**********************************************************************************************************\n" +
                "* Welcome to Madison Buffet, here are the commands you can do:\n" +
                "* \"a <name> <rating>\" adding a restaurant with its name int string and rating in double\n" +
                "* \"af <name>\" adding a bunch of restaurants in file, each line in file contents must be <name, rating>\n" +
                "* \"r <name>\" removing a restaurant with its name in string\n" +
                "* \"s\" show all the restaurant in the program\n" +
                "* \"s <name>\" show a specific restaurant\n" +
                "* \"b\" back to this menu\n" +
                "* \"q\" quit the program\n" +
                "* WARNING: YOU HAVE TO ENTER THE CORRECT TYPE, AND THE COMMANDS ARE NOT CASE SENSITIVE!\n" +
                "**********************************************************************************************************");
    }

    public static void main(String [] args)
    {
        new MadisonBuffetDriver().run(new Scanner(System.in));
    }
}
