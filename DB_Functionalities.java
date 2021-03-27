/*
* DataBase Functionalities with Java
* Developer: Muhammad Sikander Iqbal
* Contact: taimoorahamed95959@gmail.com | www.fiverr.com/phoenixhub947 | Phone no: +923346145098 |
* CopyRight @ Phoenix Hub You are not allowed to use any section of this program. This code is just for learning purpose.
* Version: 1.0.1
* Updated on 27-02-2021 @ 10:22 PM PKT
*/

package jdbc;
/*--------------------------------------------------------------------------------------*/
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*--------------------------------------------------------------------------------------*/
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/*--------------------------------------------------------------------------------------*/
import java.util.Scanner;
import java.util.Date;
/*--------------------------------------------------------------------------------------*/
public class DB_Functionalities {

    static final String DB_URL = "jdbc:mysql://localhost/your_database_name";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String username = "root";
    static final String password = "admin";

    static Scanner input = new Scanner(System.in);

    static Connection connection = null;
    static Statement statement = null;
    static ResultSet result;

    public static void main(String[] args){

        try {
            connection();

            while (true) {

                //Get standard date and time
                Date Date = new Date();
                System.out.println("\nDate&Time: " + Date.toString() + "\n");

                menu();

                int opt = input.nextInt();

                switch (opt) {
                    case 1:
                        printValues();
                        break;
                    case 2:
                        int age1;
                        String skill1, edu_level1;

                        System.out.println("Enter the Values:\n");
                        System.out.print("Enter Age:");
                        age1 = input.nextInt();

                        System.out.print("Enter Skill:");
                        input.nextLine();
                        skill1 = input.nextLine();

                        System.out.print("Enter Education Level:");
                        edu_level1 = input.nextLine();

                        System.out.println("Inserting Values in the DataBase.....\n");
                        Thread.sleep(2000); //Delay for 2 seconds

                        String SQL1 = "INSERT INTO my_bio VALUES (" + age1 + ",'" + skill1 + "','" + edu_level1 + "')";
                        statement.executeUpdate(SQL1);

                        System.out.println("After Inserting new Values:\n");
                        Thread.sleep(2000); //Delay for 2 seconds

                        printValues();
                        break;
                    case 3:
                        System.out.print("Enter the name of the DataBase:");
                        input.nextLine();
                        String new_DB = input.nextLine();

                        String SQL3 = "CREATE DATABASE " + new_DB;
                        statement.executeUpdate(SQL3);

                        System.out.println("Creating DataBase.....\n");
                        Thread.sleep(3000); //Delay for 3 seconds
                        System.out.println("DataBase created Successfully\n");
                        break;
                    case 4:
                        System.out.println("Enter which age's entry you want to delete:\n");
                        System.out.println("Available ages are: \n");
                        Thread.sleep(2000); //Delay for 2 seconds

                        String SQL4_1 = "SELECT age FROM my_bio";
                        result = statement.executeQuery(SQL4_1);

                        while (result.next()) {
                            int age = result.getInt("age");
                            System.out.println("Age: " + age + "\n");
                        }

                        System.out.print("Choose: ");
                        int age_del = input.nextInt();

                        String SQL4_2 = "DELETE FROM my_bio WHERE age = " + age_del;
                        statement.executeUpdate(SQL4_2);

                        System.out.println("Deleting Values from the table.....\n");
                        Thread.sleep(3000); //Delay for 2 seconds
                        System.out.println("Values Deleted!\n");
                        Thread.sleep(1500); //Delay for 1.5 seconds

                        System.out.println("Values after deletion:\n");
                        Thread.sleep(500); //Delay for .5 seconds

                        printValues();
                        break;
                    case 5:
                        System.out.println("Thanks for using.\nExiting the System.....\n");
                        System.exit(1);
                        result.close();
                        statement.close();
                        connection.close();
                        break;
                    default:
                        System.out.println("Invalid Input!! Try Again\n");
                }
            }
        } catch(SQLException ex){
                System.out.println("error: failed to create a connection object.\n");
                ex.printStackTrace();
        } catch(InterruptedException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void menu() {
        System.out.println("Choose from the following menu:\n " +
                "1. See DataBase Values\n 2. Insert DataBase Values\n " +
                "3. Create a new DataBase\n 4. Delete Values from a table\n " +
                "5. Exit System\n ");
        System.out.print("Choose:");
    }

    public static void printValues() throws SQLException {
        String SQL = "SELECT * from my_bio";
        result = statement.executeQuery(SQL);
        res(result);
    }

    static void res(ResultSet result) throws SQLException {
        while (result.next()) {
            int age = result.getInt("age");
            String skills = result.getString("skills");
            String edu_level = result.getString("edu_level");

            //Display values
            System.out.print(" \nAge: " + age);
            System.out.print(", Skills: " + skills);
            System.out.println(", Education Level: " + edu_level);
        }
    }

    public static void connection() throws InterruptedException, SQLException, ClassNotFoundException {
        System.out.println("Registering the DataBase Driver.....\n");
        Thread.sleep(3000); //delay for 3 seconds
        Class.forName(JDBC_DRIVER);
        System.out.println("DataBase Registered!\n");
        Thread.sleep(1000); //delay for 1 seconds

        System.out.println("Attempting to connect to the server.....\n");
        connection = (Connection) DriverManager.getConnection(DB_URL, username, password);
        System.out.println("Connected to the Server!\n");
        Thread.sleep(1000); //delay for 1 seconds

        System.out.println("Making statement.....\n");
        Thread.sleep(2000); //delay for 2 seconds
        statement = (Statement) connection.createStatement();
        System.out.println("Statement Created!\n");
        Thread.sleep(1000); //delay for 1 seconds

        System.out.println("Fetching the required queries.....");
        Thread.sleep(2000); //delay for 2 seconds
    }
}
