//package TodoList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import java.io.File;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


class Contacts extends VBox{
    private Info firstName;
    private Info mail;
    private Info num;
    private Button test;
    
    Contacts(String name, String email, String phoneNum){
        firstName = new Info(name,true);
        mail = new Info(email,false);
        num = new Info(phoneNum,false);
        this.getChildren().add(firstName);
        this.getChildren().add(mail);
        this.getChildren().add(num);

        // test = new Button("Done"); // creates a button for marking the task as done
        // test.setPrefSize(100, 20);
        // test.setPrefHeight(Double.MAX_VALUE);
        // test.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

        // this.getChildren().add(test);

        // this.setSpacing(5); // sets spacing between contacts

        // this.setPrefSize(500, 560);
        // this.setStyle("-fx-background-color: #F0F8FF;");
    }

    // public void addButton(Info test){
    //     Button testing = new Button();
    //     test.getChildren().add(testing);
    // }
    
}

class Info extends HBox {

    private Label index;
    private TextField infoName;
    private TextField infoName2;
    private Button doneButton;

    private boolean markedDone;

    // Info(String text) {
    //     this.setPrefSize(500, 20); // sets size of task
    //     this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
    //     markedDone = false;

    //     // index = new Label();
    //     // index.setText(""); // create index label
    //     // index.setPrefSize(40, 20); // set size of Index label
    //     // index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
    //     // index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
    //     // this.getChildren().add(index); // add index label to task

    //     infoName = new TextField(); // create task name text field
    //     infoName.setPrefSize(380, 20); // set size of text field
    //     infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
    //     // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
    //     infoName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
    //     this.getChildren().add(infoName); // add textlabel to task
        
    //     // doneButton = new Button("Done"); // creates a button for marking the task as done
    //     // doneButton.setPrefSize(100, 20);
    //     // doneButton.setPrefHeight(Double.MAX_VALUE);
    //     // doneButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

    //     // this.getChildren().add(doneButton);
    //     infoName.setPromptText(text);
    // }

        Info(String text, boolean taskName) { // TaskName is True if its for name Info, otherwise false(for email and phone Num info)

        if(taskName == true){
             this.setPrefSize(500, 20); // sets size of task
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
            markedDone = false;

            // index = new Label();
            // index.setText(""); // create index label
            // index.setPrefSize(40, 20); // set size of Index label
            // index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
            // index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
            // this.getChildren().add(index); // add index label to task

            infoName = new TextField(); // create task name text field
            infoName.setPrefSize(380, 20); // set size of text field
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
            // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
            infoName.setPadding(new Insets(10, 10, 10, 0)); // adds some padding to the text field
            this.getChildren().add(infoName); // add textlabel to task
            
            doneButton = new Button("Done"); // creates a button for marking the task as done
            doneButton.setPrefSize(100, 20);
            doneButton.setPrefHeight(Double.MAX_VALUE);
            doneButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

            this.getChildren().add(doneButton);
            infoName.setPromptText(text);
        }
        else{
            this.setPrefSize(500, 20); // sets size of task
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
            markedDone = false;

            // index = new Label();
            // index.setText(""); // create index label
            // index.setPrefSize(40, 20); // set size of Index label
            // index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
            // index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
            // this.getChildren().add(index); // add index label to task

            infoName = new TextField(); // create task name text field
            infoName.setPrefSize(380, 20); // set size of text field
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
            // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
            infoName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
            this.getChildren().add(infoName); // add textlabel to task
            
            // doneButton = new Button("Done"); // creates a button for marking the task as done
            // doneButton.setPrefSize(100, 20);
            // doneButton.setPrefHeight(Double.MAX_VALUE);
            // doneButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

            // this.getChildren().add(doneButton);
            infoName.setPromptText(text);
        }
    }

    // public void setTaskIndex(int num) {
    //     this.index.setText(num + ""); // num to String
    //     this.taskName.setPromptText("Task " + num);
        
    // }
    

    public TextField getInfoName() {
        return this.infoName;
    }

    public Button getDoneButton() {
        return this.doneButton;
    }

    public boolean isMarkedDone() {
        return this.markedDone;
    }

    public void toggleDone() {
        
        if(markedDone == false){
            markedDone = true;
            this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;"); // remove border of task
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;"); // change color of task to green
            }
        }
        else{
            markedDone = false;
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // set background color of texfield
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // change color of task to green
            }
        }
    }

}

class ContactList extends VBox {

    ContactList() {
        this.setSpacing(5); // sets spacing between contacts
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    // public void updateTaskIndices() {
    //     int index = 1;
    //     for (int i = 0; i < this.getChildren().size(); i++) {
    //         if (this.getChildren().get(i) instanceof Task) {
    //             ((Task) this.getChildren().get(i)).setTaskIndex(index);
    //             index++;
    //         }
    //     }
    // }

    // public void removeCompletedTasks() {
    //     this.getChildren().removeIf(info -> info instanceof Info && ((Info) info).isMarkedDone());
    //     //this.updateTaskIndices();
    // }

    /*
     * Load tasks from a file called "tasks.txt"
     * Add the tasks to the children of tasklist component
     */
    // public void loadTasks() {
    //     BufferedReader reader;
    //     try{
    //         FileReader fileName = new FileReader("tasks.txt");
    //         reader = new BufferedReader(fileName);
    //         String line = reader.readLine();
    //         while(line != null){
    //             Info info = new Info("text");
    //             info.getInfoName().setText(line);
    //             this.getChildren().add(info);
    //             //this.updateTaskIndices();
    //             line = reader.readLine();
    //         }

    //         reader.close();
    //     }
    //     catch(IOException e){
    //         System.out.println("There is no file called 'tasks.txt' ");
    //     }
    // }

    /*
     * Save tasks to a file called "tasks.txt"
     */
    // public void saveContacts() {
    //     try{
    //         // File fd = "tasks.txt";
    //         File file = new File("tasks.txt");
    //         FileWriter output = new FileWriter(file);
    //         //Get List of tasks and their task name respectively 
    //         for(int i = 0; i < this.getChildren().size();i++){
    //             if (this.getChildren().get(i) instanceof Info) {
    //                 output.write(((Info) this.getChildren().get(i)).getInfoName().getText());
    //                 output.write("\n");
    //             }
    //         }
    //         output.close();
    //     }
    //     catch(IOException e){
    //         System.out.println("Exception is caught!");
    //     }   
    // }

    /*
     * TODO: Need to update sort Contacts (By Name)
     */
    public void sortContacts() { 
        List<String> lst = new ArrayList<String>();
        for (int i = 0; i < this.getChildren().size();i++){
            lst.add(((Info)this.getChildren().get(i)).getInfoName().getText());
        }
        Collections.sort(lst); 
        //Sorted List of task Names:

        for(int i = 0; i < this.getChildren().size();i++){
            ((Info)this.getChildren().get(i)).getInfoName().setText(lst.get(i));
        }
    }
}

class Footer extends HBox {

    private Button createButton;
    private Button deleteButton;
    private Button readButton;
    private Button updateButton;
    private Button sortButton;

    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        // set a default style for buttons - background color, font size, italics
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        createButton = new Button("CREATE"); // text displayed on add button
        createButton.setStyle(defaultButtonStyle); // styling the button
        // clearButton = new Button("DELETE"); // text displayed on clear tasks button
        // clearButton.setStyle(defaultButtonStyle);

        // Create readButton, updateButton and sortButton to the footer
        readButton = new Button("READ");
        readButton.setStyle(defaultButtonStyle);
        updateButton = new Button("UPDATE");
        updateButton.setStyle(defaultButtonStyle);
        sortButton = new Button("Sort Tasks (By Name)");
        sortButton.setStyle(defaultButtonStyle);

        this.getChildren().addAll(createButton,readButton,updateButton,sortButton); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center
    }

    public Button getCreateButton() { // Formerly "getAddButton"
        return createButton; // Formerly "addButton"
    }

    public Button getReadButton() { // Formerly "getLoadButton"
        return readButton; // Formerly "loadButton"
    }

    public Button getUpdateButton() { // Formerly "getSave Button"
        return updateButton; // Formerly "save Button"
    }

    public Button getDeleteButton() { // Formerly "getClearButton"
        return deleteButton; // Formerly "clearbutton"
    }

    public Button getSortButton() {
        return sortButton;
    }
}

class Header extends HBox {

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F0F8FF;");

        Text titleText = new Text("CONTACTS"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class AppFrame extends BorderPane{

    private Header header;
    private Footer footer;
    private ContactList contactList;
    private ScrollPane scrollBar;

    private Button createButton;
    private Button deleteButton;
    private Button readButton;
    private Button updateButton;
    private Button sortButton;

    AppFrame()
    {
        // Initialise the header Object
        header = new Header();

        // Create a tasklist Object to hold the tasks
        contactList = new ContactList();
        
        // Initialise the Footer Object
        footer = new Footer();

        // Adds a Scroller to the Task List
        scrollBar = new ScrollPane(contactList);
        if(contactList.getChildren().size() < 11){
            scrollBar.setFitToHeight(true);
        }
        scrollBar.setFitToWidth(true);
        // scrollBar.setFitToHeight();


        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(scrollBar); //TaskList
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        createButton = footer.getCreateButton();
        // clearButton = footer.getDeleteButton();
        readButton = footer.getReadButton();
        updateButton = footer.getUpdateButton();
        sortButton = footer.getSortButton();
        // Call Event Listeners for the Buttons
        addListeners();
    }

    public void addListeners()
    {

        // Add button functionality
        createButton.setOnAction(e -> {
            // Create a new task
            // Info info = new Info("Name");
            Contacts contact= new Contacts("Name", "Email", "Phone Number");
            // Add task to tasklist
            contactList.getChildren().add(contact);

            /* Contacts Testing for Button
             * contact.getNameInfo()
             * 
             */
            // contact.



            // Info info2 = new Info("Email");
            // // Add task to tasklist
            // contactList.getChildren().add(info2);

            // Info info3 = new Info("Phone Number");
            // // Add task to tasklist
            // contactList.getChildren().add(info3);

            // // Add doneButtonToggle to the Done button
            // Button doneButton = info.getDoneButton();
            
            // doneButton.setOnAction(e1 -> {
            //     // Call toggleDone on click
            //     info.toggleDone();
            //     info2.toggleDone();
            //     info3.toggleDone();                
            
            // });
            // Update task indices
            //taskList.updateTaskIndices();
        });
        
        // Clear finished tasks
        // clearButton.setOnAction(e -> {
        //     taskList.removeCompletedTasks();
        // });
        // readButton.setOnAction(e -> {
        //     taskList.loadTasks();
        //     //Get ArrayList<Task> to 
        //     for(int i = 0; i < taskList.getChildren().size();i++){
        //         Task task = ((Task)taskList.getChildren().get(i));
        //         Button doneButton = task.getDoneButton();
        //         doneButton.setOnAction(e1 -> {
        //         // Call toggleDone on click
        //         task.toggleDone();
        //         });
        //         //taskList.updateTaskIndices();
        //     }
        // });
        
    //  updateButton.setOnAction(e -> {
    //         contactList.saveTasks();
    //     });
    //     sortButton.setOnAction(e -> {
    //         contactList.sortTasks();
    //     });
    // }
    }
}

public class Main extends Application {

    // To display images
    private ImageView imageView = new ImageView();

    // To open a file dialog for selecting images
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the TaskList
        AppFrame root = new AppFrame();

        // Set the title of the app
        primaryStage.setTitle("Contacts");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}