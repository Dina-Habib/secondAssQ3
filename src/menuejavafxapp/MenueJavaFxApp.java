/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuejavafxapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author dinahabib
 */
//Q3:
public class MenueJavaFxApp extends Application {
    TextArea textAreaFileContent; 
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Worko=ing with menues");
        MenuBar menuBar = new MenuBar();
        Menu menuFile= new Menu("_File");
        MenuItem menuItemOpen=new MenuItem("_Open");
         MenuItem menuItemClose=new MenuItem("_Close");
          MenuItem menuItemExit=new MenuItem("_Exit");
         menuFile.getItems().addAll(menuItemOpen,menuItemClose,menuItemExit);
        Menu menuEdit= new Menu("_Edit");
        MenuItem menuItemFont=new MenuItem("_Font");
         MenuItem menuItemColor=new MenuItem("_Color");
          menuEdit.getItems().addAll(menuItemFont,menuItemColor);
          menuBar.getMenus().addAll(menuFile,menuEdit);
           textAreaFileContent=new TextArea("Intial Text");
          EventHandler1 ev1=new EventHandler1();
          menuItemOpen.setOnAction(ev1);
          menuItemClose.setOnAction(ev1);
          menuItemExit.setOnAction(ev1);
          menuItemFont.setOnAction(ev1);
          menuItemColor.setOnAction(ev1);
          VBox vbox=new VBox(menuBar,textAreaFileContent);
          Scene scene=new Scene(vbox,200,200);
          primaryStage.setScene(scene);
          primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private class EventHandler1 implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
             String menuItem=((MenuItem)event.getSource()).getText();
             switch(menuItem){
                 case "_Open":
                     FileChooser fc=new FileChooser();
                     fc.setTitle("File Selection");
                     fc.setInitialFileName(".");
                     //fc.setInitialDirectory(new File("."));(current file)
                     File file=fc.showOpenDialog(null);//لما يخلص من الfilechooser يرجعلي على الstage
                     textAreaFileContent.setText("");
                     textAreaFileContent.setWrapText(true);//to read next line 
                 {
                     try {
                         Scanner scanner=new Scanner(file);
                         while(scanner.hasNext()){
                             textAreaFileContent.appendText(scanner.nextLine());//حيضيف على الموجود اما setText حتلغي القديم
                         }
                         scanner.close();
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(MenueJavaFxApp.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                     break;

                 case "_Close":
                     textAreaFileContent.clear();
                     break;
                     
                 case "_Exit":
                     System.exit(0);
                     break;
                     
                 case "_Font":
                     Dialog <String> dialogFont= new //TextInputDialog();
                             ChoiceDialog<>("",FXCollections.observableArrayList("10","20","30","40"));//First parameter is intial selection
                     dialogFont.setTitle("Font selection");
                     dialogFont.setHeaderText("Select Color");
                     String font=dialogFont.showAndWait().get();
                     textAreaFileContent.setStyle("-fx-font-size:"+font+";");
                     break;
                     
                 case "_Color":
                     Dialog <String> dialogColor= new //TextInputDialog();
                             ChoiceDialog<>("",FXCollections.observableArrayList("Red","Blue","Green"));//First parameter is intial selection
                     dialogColor.setTitle("Color selection");
                     dialogColor.setHeaderText("Select Color");
                     String color=dialogColor.showAndWait().get();
                     textAreaFileContent.setStyle("-fx-text-fill:"+color+";");
                     break;
             }
        }
        
    }
    
}
