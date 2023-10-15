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
import java.util.Comparator;

class Contacts extends VBox {
    private Info name;
    private Info email;
    private Info phoneNum;
    private Button uploadButton;
    private ImageView imageView;
    private Image image;
    private FileChooser fileChooser;
    private Stage primaryStage;
    private boolean read;
    
    Contacts(String name, String email, String phoneNum, Stage stage, ImageView img, FileChooser file){
        this.name = new Info(name,true);
        this.email = new Info(email,false);
        this.phoneNum = new Info(phoneNum,false);
        this.getChildren().add(this.name);
        this.getChildren().add(this.email);
        this.getChildren().add(this.phoneNum);

        //Image Stuff
        this.uploadButton = new Button("Upload Image");
        primaryStage = stage;
        imageView = img;
        fileChooser = file;
        read = false;
        this.getChildren().add(uploadButton);
    }

    // Method to remove the email and phone number text boxes
    //Index 4: Image, 3: Upload Button, 2: Phone Num, 1: Email, 0: Name
    public void removeInfo() {
        this.getChildren().clear();
        this.getChildren().add(this.name);
        // if(this.getChildren().size() == 5){
        //     this.getChildren().removeAll();
        //     this.getChildren().add(this.name);
        //     return;
        // }

        // if(image == null){
        //     this.getChildren().remove(this.getChildren().size()-1);
        // }
        // if(phoneNum == null){
        //     this.getChildren().remove(this.getChildren().size()-1);
        // }
        // if(email == null){
        //     this.getChildren().remove(this.getChildren().size()-1);

        // }
    }

    public void showInfo(){
        this.getChildren().add(email);
        this.getChildren().add(phoneNum);
        this.getChildren().add(uploadButton);
        if(this.image != null){
            imageView.setImage(this.image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            this.getChildren().add(imageView);
        }
        // else{
        //     this.getChildren().add(uploadButton);
        // }

    }
    public Info getName() {
        return this.name;
    }

    public Info getEmail() {
        return this.email;
    }

    public Info getPhoneNum() {
        return this.phoneNum;
    }

    public Button getUploadButton(){
        return this.uploadButton;
    }

    public void removeUploadButton(){
        this.getChildren().remove(uploadButton);
    }

    public boolean hasImage(){
        if (this.image != null){
            return true;
        }
        else{
            return false;
        }
    }
    public void setReadFunction(){
        if (read == false){
            // if (!this.getChildren().contains(email)){
            //     this.getChildren().add(email);
            //     this.getChildren().add(phoneNum);
            // }
            this.getName().removeButtons();
            read = true;
        }
        else{
            this.getName().setUnread();
            read = false;
        }
    }


    public void uploadImage(){
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);

            if(!this.getChildren().contains(imageView)){
                this.getChildren().add(imageView);
            }
            // if(this.image!= null){
            //     this.getChildren().add(imageView);
            // }
            // else{
            //     this.getChildren().get(this.getChildren().size()-1).;
            // }
            this.image = image;
            
            // this.getChildren().add(imageView);
            // this.getChildren().remove(uploadButton);

        }
    }


    // @Override
    // public int compareTo(Contacts o) {
    //     return ((String)this.name.getInfoName().getText()).compareTo((String)o.getName().getInfoName().getText());
    // }

}

class sortContactByName implements Comparator<Contacts> {
    @Override
    public int compare(Contacts c1, Contacts c2) {
        return ((String)c1.getName().getInfoName().getText()).compareTo((String)c2.getName().getInfoName().getText());
    }
}

class Info extends HBox {

    private TextField infoName;
    private Button doneButton;
    private Button updateButton;
    private Button deleteButton;
    private boolean markedDone;

        Info(String text, boolean taskName) { // TaskName is True if its for name Info, otherwise false(for email and phone Num info)

        if(taskName == true){
             this.setPrefSize(500, 20); // sets size of task
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
            markedDone = false;

            infoName = new TextField(); // create task name text field
            infoName.setPrefSize(380, 20); // set size of text field
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
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

            infoName = new TextField(); // create task name text field
            infoName.setPrefSize(380, 20); // set size of text field
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
            infoName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
            this.getChildren().add(infoName); // add textlabel to task
            infoName.setPromptText(text);
        }
    }

    
    public TextField getInfoName() {
        return this.infoName;
    }

    public Button getDoneButton() {
        return this.doneButton;
    }

    public Button getUpdateButton(){
        return this.updateButton;
    }

    public Button getDeleteButton(){
        return this.deleteButton;
    }

    public boolean isMarkedDone() {
        return this.markedDone;
    }

    /* Remove Done Button from Info Object
     * 
     */
    public void removeDoneButton(){
        this.getChildren().remove(doneButton);
        this.infoName.setEditable(false);
    }

    /* Adds Update and Delete Button 
     * 
     */
    public void addUpdAndDelButton(){
        this.updateButton = new Button("Update");
        this.updateButton.setPrefSize(100, 20);
        this.updateButton.setPrefHeight(Double.MAX_VALUE);
        this.updateButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

        this.deleteButton = new Button("Delete");
        this.deleteButton.setPrefSize(100, 20);
        this.deleteButton.setPrefHeight(Double.MAX_VALUE);
        this.deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

        this.getChildren().add(this.updateButton);
        this.getChildren().add(this.deleteButton);

    }

    /* Removes Update and Delete Button and 
     * replaces it with Done Button: (Invoked
     * when Update Button is created)
     * 
     */
    public void replaceWithDone(){
        infoName.setEditable(true);
        this.getChildren().remove(deleteButton);
        this.getChildren().remove(updateButton);
        this.getChildren().add(doneButton);
    }

    public void removeButtons(){
        infoName.setEditable(false);
        if (doneButton != null){
            this.getChildren().remove(doneButton);
        }

        if (deleteButton != null){
            this.getChildren().remove(deleteButton);
        }

        if (updateButton != null){
            this.getChildren().remove(updateButton);
        }
    }

    public void setUnread(){
        infoName.setEditable(true);
        if (updateButton != null && deleteButton != null){
            this.getChildren().add(updateButton);
            this.getChildren().add(deleteButton);
        }
        else{
            this.updateButton = new Button("Update");
            this.updateButton.setPrefSize(100, 20);
            this.updateButton.setPrefHeight(Double.MAX_VALUE);
            this.updateButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

            this.deleteButton = new Button("Delete");
            this.deleteButton.setPrefSize(100, 20);
            this.deleteButton.setPrefHeight(Double.MAX_VALUE);
            this.deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

            this.getChildren().add(this.updateButton);
            this.getChildren().add(this.deleteButton);
        }


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

class ContactList extends VBox{ // implements Comparable<Contacts>

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

    public void clearAll(){
        this.getChildren().clear();
    }
    /*
     * TODO: Need to update sort Contacts (By Name)
     */
    public void sortContacts() { 
        // Collections.sort((Contacts)this.getChildren(),new sortContactByName());
        List<Contacts> lst = new ArrayList<Contacts>();
        for(int i = 0; i < this.getChildren().size();i++){
            Contacts currContact = ((Contacts) this.getChildren().get(i));
            lst.add(currContact);
        }

        Collections.sort(lst,new sortContactByName());
        // this.getChildren() = lst;

        this.getChildren().clear();
        for(int i = 0; i < lst.size();i++){
            this.getChildren().add((Node)lst.get(i));
        }
        // print()
        // for (int i = 0; i < this.getChildren().size();i++){
        //     lst.add(((Info)this.getChildren().get(i)).getInfoName().getText());
        // }
        // Collections.sort(lst); 
        // //Sorted List of task Names:

        // for(int i = 0; i < this.getChildren().size();i++){
        //     ((Info)this.getChildren().get(i)).getInfoName().setText(lst.get(i));
        // }
    }

    // @Override
    // public int compareTo(Contacts x1) {
    //     // return x1.getName() > ;
    //     // return 0;
    // }
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
        // updateButton = new Button("UPDATE");
        // updateButton.setStyle(defaultButtonStyle);
        sortButton = new Button("Sort Tasks (By Name)");
        sortButton.setStyle(defaultButtonStyle);

        deleteButton = new Button("DELETE (ALL)");
        deleteButton.setStyle(defaultButtonStyle);
        this.getChildren().addAll(createButton,readButton,sortButton,deleteButton); // adding buttons to footer
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
    private Button sortButton;
    private Stage primaryStage;
    private ImageView imageView;
    private FileChooser fileChooser;

    AppFrame(Stage primaryStage, ImageView imageView, FileChooser fileChooser)
    {
        this.primaryStage = primaryStage;
        this.imageView = imageView;
        this.fileChooser = fileChooser;
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
        // updateButton = footer.getUpdateButton();
        sortButton = footer.getSortButton();
        // updateButton = null;
        deleteButton = footer.getDeleteButton();

        // Call Event Listeners for the Buttons
        addListeners();
    }

    public void addListeners()
    {
        // // Add button functionality
        // for(int i = 0; i < contactList.getChildren().size();i++){
        //     if(((Contacts)contactList.getChildren().get(i)).getName().getDoneButton()!= null){
        //         create = false;
        //     }
        // }
        // if(create == true){

            createButton.setOnAction(e -> {
                createButton.setDisable(true);
                readButton.setDisable(true);
                for(int i = 0; i < contactList.getChildren().size();i++){
                    Contacts curr = ((Contacts)contactList.getChildren().get(i));
                    if(contactList != null && curr.getName().getUpdateButton() != null){
                        curr.getName().getUpdateButton().setDisable(true);
                    }
                }


                Contacts contact = new Contacts("Name", "Email", "Phone Number", primaryStage, imageView, fileChooser);
                contactList.getChildren().add(contact);
                Button doneButton = contact.getName().getDoneButton();
                Button imageButton = contact.getUploadButton();

                doneButton.setOnAction(e1 -> {
                    if(contact.hasImage()==true){
                        contact.getUploadButton().setText("Change Image");
                    }
                    createButton.setDisable(false);
                    readButton.setDisable(false);
                    for(int i = 0; i < contactList.getChildren().size();i++){
                        Contacts curr = ((Contacts)contactList.getChildren().get(i));
                        if (curr.getName().getUpdateButton() != null){
                            curr.getName().getUpdateButton().setDisable(false);
                        }
                    }
                    contact.removeInfo();
                    contact.getName().removeDoneButton();
                    contact.getName().addUpdAndDelButton();
                    Button updateButton = contact.getName().getUpdateButton();
                    Button deleteButton = contact.getName().getDeleteButton();  

                    updateButton.setOnAction(e2 -> {
                        createButton.setDisable(true);
                        readButton.setDisable(true);
                        //Disable ALL Update Buttons:
                        for(int i = 0; i < contactList.getChildren().size();i++){
                            Contacts curr = ((Contacts)contactList.getChildren().get(i));
                            curr.getName().getUpdateButton().setDisable(true);
                        }
                        contact.showInfo();
                        contact.getName().replaceWithDone();
                    });

                    deleteButton.setOnAction(e3 -> {
                        contactList.getChildren().remove(contact);
                    });
                });

                imageButton.setOnAction(e4 -> {
                    contact.uploadImage();
                });
            });
        // }


        /* We can only click 'Read' when we aren't editing any contacts currently --> Finished
         * Fix Functionality of Read: (ie.) Add extra Info when we disable buttons --> Debug? I(Alex) think it's linked to first part of disabling 'Read'
         * Add Sorting (ContactList), 
         * TODO: Export data to CSV File
         * TODO:(Both of us: Later Stages) Clean up code???
         */
        readButton.setOnAction(e -> {
            for(int i = 0; i < contactList.getChildren().size();i++){
                Contacts curr = ((Contacts)contactList.getChildren().get(i));
                curr.setReadFunction();

                // Contacts contact = ((Contacts) contactList.getChildren().get(i));
                // Button updateButton = contact.getName().getUpdateButton();
                // Button deleteButton = contact.getName().getDeleteButton();   

                // updateButton.setOnAction(e2 -> {
                //     contact.showInfo();
                //     contact.getName().replaceWithDone();
                // });

                // deleteButton.setOnAction(e3 -> {
                //     contactList.getChildren().remove(contact);
                // });
            }


        });

        sortButton.setOnAction(e ->{
            contactList.sortContacts();
        });

        deleteButton.setOnAction(e ->{
            contactList.clearAll();
        });
        //Image Stuff
        //     fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        //     File selectedFile = fileChooser.showOpenDialog();


        //     if (selectedFile != null) {
        //         Image image = new Image(selectedFile.toURI().toString());

        //         /*
        //          * TODO6: Set the selected image in imageView i.e. display the image.
        //          * Hint: To implement this, you can use the setImage() method of ImageView and pass the selected image as an argument.
        //          */
        //         imageView.setImage(image);
        //         // Resize the window to fit the image
        //         primaryStage.setWidth(image.getWidth() + 100);
        //         primaryStage.setHeight(image.getHeight() + 100);
        //     }




















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
        AppFrame root = new AppFrame(primaryStage, imageView, fileChooser);

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