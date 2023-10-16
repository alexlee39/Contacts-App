/* CSE 110 Mini Project
 * Authors: Alexander Lee, Ryan Paquila
 * 
 */

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
import java.util.stream.Stream;

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
    
    /* Create Contact Object which contains 3 text boxes(Info Objects) for Name, Email, and PhoneNum Respectively
     * Also adds ability to insert image for the respective Contact
     */
    Contacts(String name, String email, String phoneNum, Stage stage, ImageView img, FileChooser file){
        this.name = new Info(name,true);
        this.email = new Info(email,false);
        this.phoneNum = new Info(phoneNum,false);
        this.getChildren().add(this.name);
        this.getChildren().add(this.email);
        this.getChildren().add(this.phoneNum);
        this.uploadButton = new Button("Upload Image");
        primaryStage = stage;
        imageView = img;
        fileChooser = file;
        read = false;
        this.getChildren().add(uploadButton);
    }

    /* Removes All Infos and inserts the Name of Contact 
     * (Occurs after you finish editing a contact ['Create' or 'Update'])
     */
    public void removeInfo() {
        this.getChildren().clear();
        this.getChildren().add(this.name);
    }

    /* Goes back to 'Edit' mode where all textboxes and displayed 
     * with information already typed in. 
     * 
     * Allows users to edit their contact's information including image.
     * 
     */
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

    /* Sets the Contact to be in 'Read' Mode and reverts back to default State(Only Names of contacts and update and del buttons):
     * If in 'default state': Adds back in email and PhoneNumber to display information
     * Deletes buttons
     * 
     * If in 'read' mode: removes email and phoneNumber and returns to default state(adding back in buttons)
     */
    public void setReadFunction(){
        if (read == false){
            this.getName().removeButtons();
            this.getChildren().add(email);
            this.getChildren().add(phoneNum);
            read = true;
        }
        else{
            this.getChildren().remove(email);
            this.getChildren().remove(phoneNum);
            this.getName().setUnread();
            read = false;
        }
    }

    /* Uploads photo that are of image file types
     * 
     */
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
            this.image = image;
        }
    }

}

/* Custom Comparator to compare contacts in ContactList
 * 
 */
class sortContactByName implements Comparator<Contacts> {
    @Override
    public int compare(Contacts c1, Contacts c2) {
        return ((String)c1.getName().getInfoName().getText()).compareTo((String)c2.getName().getInfoName().getText());
    }
}

/* Info that is in charge of prompting and allowing user to input:
 * Name, Email, and Phone Number of Contacts: 
 * Creates Buttons beside Name textbox,
 */
class Info extends HBox {

    private TextField infoName;
    private Button doneButton;
    private Button updateButton;
    private Button deleteButton;

        /* Creates a field where you can type particular information
         * 
         * @param text: Given text: Create overlay of what should each box have
         * @param taskName: True if create a textBox for name of Contacts(Creates Buttons for the whole Contact)
         * False otherwise
         */
        Info(String text, boolean taskName) { 
        if(taskName == true){
             this.setPrefSize(500, 20); 
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
            infoName = new TextField(); 
            infoName.setPrefSize(380, 20); 
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
            infoName.setPadding(new Insets(10, 10, 10, 0)); 
            this.getChildren().add(infoName); 
            doneButton = new Button("Done");
            doneButton.setPrefSize(100, 20);
            doneButton.setPrefHeight(Double.MAX_VALUE);
            doneButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
            this.getChildren().add(doneButton);
            infoName.setPromptText(text);
        }
        else{
            this.setPrefSize(500, 20); 
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");
            infoName = new TextField();
            infoName.setPrefSize(380, 20);
            infoName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            infoName.setPadding(new Insets(10, 0, 10, 0)); 
            this.getChildren().add(infoName); 
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

    /* Remove Done Button from Info Object
     * 
     */
    public void removeDoneButton(){
        this.getChildren().remove(doneButton);
        this.infoName.setEditable(false);
    }

    /* Inserts Update and Delete Button 
     * (Occurs after 'Done' Button is pressed)
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

    /* Removes Buttons from each information textbox
     * Functions is called/used when 'Done' button pressed
     * 
     */
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

    /* If 'Read' Mode is on, all text information is displayed (ie Name, Phone Num, Email) w/o buttons 
     * and w/o being able to edit that information
     * 
     * This function allows you to edit this infromation and adds back in buttons to restore to 'Default state'
     * 
     */
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
            this.updateButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
            this.deleteButton = new Button("Delete");
            this.deleteButton.setPrefSize(100, 20);
            this.deleteButton.setPrefHeight(Double.MAX_VALUE);
            this.deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
            this.getChildren().add(this.updateButton);
            this.getChildren().add(this.deleteButton);
        }
    }

}

class ContactList extends VBox{ 

    ContactList() {
        this.setSpacing(5); 
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void clearAll(){
        this.getChildren().clear();
    }

    /* Sorts all contacts using Custom Comparator class:
     * Converts from ContactList to ArrayList to call Collections.sort then
     * converts back (ie updates) back to ContactList
     * 
     */
    public void sortContacts() { 
        List<Contacts> lst = new ArrayList<Contacts>();
        for(int i = 0; i < this.getChildren().size();i++){
            Contacts currContact = ((Contacts) this.getChildren().get(i));
            lst.add(currContact);
        }
        Collections.sort(lst,new sortContactByName());
        this.getChildren().clear();
        for(int i = 0; i < lst.size();i++){
            this.getChildren().add((Node)lst.get(i));
        }
    }
}

class Footer extends HBox {

    private Button createButton;
    private Button deleteAllButton;
    private Button readButton;
    private Button updateButton;
    private Button sortButton;
    private Button exportButton;


    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        createButton = new Button("CREATE");
        createButton.setStyle(defaultButtonStyle); 
        readButton = new Button("READ");
        readButton.setStyle(defaultButtonStyle);
        sortButton = new Button("Sort Contacts (By Name)");
        sortButton.setStyle(defaultButtonStyle);
        deleteAllButton = new Button("DELETE (ALL)");
        deleteAllButton.setStyle(defaultButtonStyle);
        exportButton = new Button("Export to CSV");
        exportButton.setStyle(defaultButtonStyle);
        this.getChildren().addAll(createButton,readButton,sortButton,exportButton,deleteAllButton);
        this.setAlignment(Pos.CENTER);
    }

    public Button getCreateButton() { 
        return createButton; 
    }

    public Button getReadButton() { 
        return readButton; 
    }

    public Button getUpdateButton() { 
        return updateButton; 
    }

    public Button getDeleteAllButton() {
        return deleteAllButton;
    }

    public Button getSortButton() {
        return sortButton;
    }

    public Button getExportButton(){
        return exportButton;
    }
}

class Header extends HBox {

    Header() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        Text titleText = new Text("CONTACTS"); 
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); 
    }
}

class AppFrame extends BorderPane{

    private Header header;
    private Footer footer;
    private ContactList contactList;
    private ScrollPane scrollBar;

    private Button createButton;
    private Button deleteAllButton;
    private Button readButton;
    private Button sortButton;
    private Button exportButton;
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


        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(scrollBar); 
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        createButton = footer.getCreateButton();
        readButton = footer.getReadButton();
        sortButton = footer.getSortButton();
        deleteAllButton = footer.getDeleteAllButton();
        exportButton = footer.getExportButton();

        addListeners();
    }

    public void addListeners()
    {
            /* When Create button is pressed, create, read, and all current Contact's update button 
             * are disabled
             * Creates Contact: which prompts user to enter info about the contact including: name, email, phoneNumber, and Image
             */
            createButton.setOnAction(e -> {
                createButton.setDisable(true);
                readButton.setDisable(true);
                deleteAllButton.setDisable(true);
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

                /* Upon pressing Done button, basically means finished editing a contact:
                 *
                 * Functionalities:
                 * If image has been uploaded, change Upload Button text to 'Change Image'
                 * Also re-enables create, read, and all current Contact's update buttons
                 * Deletes Done button and replaces with 'Update' and 'Delete' buttons
                 * 
                 */
                doneButton.setOnAction(e1 -> {
                    if(contact.hasImage()==true){
                        contact.getUploadButton().setText("Change Image");
                    }
                    createButton.setDisable(false);
                    readButton.setDisable(false);
                    deleteAllButton.setDisable(false);
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

                    /* Allows to change information and image of a particular contact
                     * Also locks the create,read and update buttons:
                     * When finished editing: click done to restore to normal state of contact with just name showing
                     */
                    updateButton.setOnAction(e2 -> {
                        createButton.setDisable(true);
                        readButton.setDisable(true);
                        deleteAllButton.setDisable(true);
                        for(int i = 0; i < contactList.getChildren().size();i++){
                            Contacts curr = ((Contacts)contactList.getChildren().get(i));
                            curr.getName().getUpdateButton().setDisable(true);
                        }
                        contact.showInfo();
                        contact.getName().replaceWithDone();
                    });

                    /* Upon pressing delete button (of Contact) removes the contact entirely from list/app
                     * 
                     */
                    deleteButton.setOnAction(e3 -> {
                            contactList.getChildren().remove(contact);
                        });
                    });


                    /* Upon pressing Upload(image) button: uploads image
                    * 
                    */
                    imageButton.setOnAction(e4 -> {
                        contact.uploadImage();
                    });
            });

        /* Upon pressing read button:
         * Restricts all contacts to only
         */
        readButton.setOnAction(e -> {
            for(int i = 0; i < contactList.getChildren().size();i++){
                Contacts curr = ((Contacts)contactList.getChildren().get(i));
                curr.setReadFunction();
            }
        });

        /* Sorts all contacts lexographically based on Name
         * 
         */
        sortButton.setOnAction(e ->{
            contactList.sortContacts();
        });

        /* Deletes all contacts from contactList
         * 
         */
        deleteAllButton.setOnAction(e ->{
            contactList.clearAll();
        });

        /* Creates new csv file called 'contacts.csv'
         * Imports data of each contact of Name, Email, Phone Number
         * 
         */
        exportButton.setOnAction(e ->{
            try{
                File file = new File("contacts.csv");
                PrintWriter output = new PrintWriter(file);
                output.println("Name, Email, Phone Number");
                for(int i = 0; i < contactList.getChildren().size();i++){
                    Contacts currContact = (Contacts)contactList.getChildren().get(i);
                    String name = (String)currContact.getName().getInfoName().getText();
                    String email = (String)currContact.getEmail().getInfoName().getText();
                    String phoneNum = (String)currContact.getPhoneNum().getInfoName().getText();
                    output.println(name + ", " + email + ", " + phoneNum);
                }
                output.close();
            }
            catch(IOException error){
                System.out.println("Exception is caught!");
            }   
        });

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