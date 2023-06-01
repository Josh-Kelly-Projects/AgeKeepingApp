package kelly;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Arrays;

public class Question2 extends Application
{

    private TextField name;
    private TextField dob;
    private TextField age;

    private Label outputLbl;

    //create the arrays to store the info
    private String[] names = new String[25];
    private String[] dobs = new String[25];
    private String[] ages = new String[25];

    @Override
    public void start(Stage primaryStage)
    {

        primaryStage.setTitle("Question 2 Don't argue");

        // Create the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Create the name label
        Label nameLbl = new Label("name:");
        gridPane.add(nameLbl, 0, 0);

        // Create the dob label
        Label dobLbl = new Label("date of birth:");
        gridPane.add(dobLbl, 0, 2);

        // Create the age label
        Label ageLbl = new Label("age:");
        gridPane.add(ageLbl, 0, 4);

        // Create the output label
        outputLbl = new Label("Output");
        gridPane.add(outputLbl, 0, 8, 2, 1);

        // Create the text fields
        name = new TextField();
        name.setEditable(true);
        gridPane.add(name, 0, 1);

        dob = new TextField();
        dob.setEditable(true);
        gridPane.add(dob, 0, 3);

        age = new TextField();
        age.setEditable(true);
        gridPane.add(age, 0, 5);

        // Create the create buttons
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e -> create());
        gridPane.add(createBtn, 0, 7);

        //Create search button
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(e -> search());
        gridPane.add(searchBtn, 1, 7);

        // Create the scene and add the grid pane to it
        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static int genRand(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    private void create()//runs when the create button is pressed
    {
        int pos = genRand(0, 24);
        boolean posNotGood = true;
        boolean arrayNotFull = true;
        boolean checker = false;
        
        for(int i = 0; i < names.length; i++)//chech that the arrays are not full to stop an infinite loop 
        {
            if(names[pos] == null)
            {
                checker = true;
            }
        }
        
        if(checker == false)
        {
            arrayNotFull = false;
            outputLbl.setText("Storage is full.");
        }
        
        while (posNotGood && arrayNotFull)//loop until a good array position is found
        {
            if (names[pos] == null)
            {
                posNotGood = false;
                names[pos] = name.getText();
                dobs[pos] = dob.getText();
                ages[pos] = age.getText();
                name.setText("");
                dob.setText("");
                age.setText("");
            } else
            {
                pos = genRand(0, 25);
            }
        }

        outputLbl.setText("User " + names[pos] + " is created at position " + pos + ".");
    }

    private void search()//runs when the search button is pressed
    {
        int index = Arrays.asList(names).indexOf(name.getText());

        if (index == -1)
        {
            outputLbl.setText("User " + name.getText() + " is not registered.");
        } else
        {
            outputLbl.setText("User " + name.getText() + " is registered and is " + ages[index] + ".");
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
