/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class MainMenuController implements Initializable {

    @FXML
    TextField numM;
    @FXML
    TextField numD;
    @FXML
    Pane mealP;
    @FXML 
    Pane drinkP;
    @FXML
    ComboBox comM;
    @FXML
    ComboBox comD;
     @FXML
     TextField txtNumberm;
     @FXML
     TextField txtNamem;
     @FXML
     TextField txtPricem;
     @FXML
     Label doneM;
      @FXML
     TextField txtNumberD;
     @FXML
     TextField txtNameD;
     @FXML
     TextField txtPriceD;
     @FXML
     Label doneD;
    @FXML
    TableView <Meal>tableM;
    @FXML
    TableColumn <Meal , Integer> Mealnumber;
    @FXML
    TableColumn <Meal , String> Mealname;
    @FXML
    TableColumn <Meal , String> Mealtype;
    @FXML
    TableColumn <Meal , Integer> Mealprice;
    @FXML
    TableView<Drinks> tableD;
    @FXML
    TableColumn<Drinks,Integer>numDrinks;
    @FXML
    TableColumn<Drinks,String>nameDrinks;
    @FXML
    TableColumn<Drinks,String>typeDrinks;
    @FXML
    TableColumn<Drinks,Integer>priceDrinks;    
    ObservableList<Meal> listM;
    ObservableList<Drinks>listD;
    @FXML
    TextField searchM;
    @FXML
    TextField searchD;    
    int indexM = -1;
    
     public void enterd(Event e){
        ((Button) e.getSource()).setScaleX(1.1);
        ((Button) e.getSource()).setScaleY(1.1);
        ((Button) e.getSource()).setTextFill(Color.BLUE);
    }
      public void exited(Event e){
        ((Button) e.getSource()).setScaleX(1);
        ((Button) e.getSource()).setScaleY(1);
        ((Button) e.getSource()).setTextFill(Color.BLACK);
        
          if (((Button) e.getSource()).getText().equals("Sign out")) {
            ((Button) e.getSource()).setTextFill(Color.RED);

          }

    }
      public void drinks(){
          drinkP.setVisible(true);
          mealP.setVisible(false);
      }
      public void meals(){
           drinkP.setVisible(false);
          mealP.setVisible(true);
      }
      public void insertM(){
          int num = Integer.parseInt(txtNumberm.getText());
          String name = txtNamem.getText();
          String type = comM.getSelectionModel().getSelectedItem().toString();
          int price = Integer.parseInt(txtPricem.getText());
          if (!DB.insertM("Meal", num, name, type, price)) {
              listM.add(new Meal(num, name, type, price));
              doneM.setText("Order is Done");
              doneM.setVisible(true);
              numM.setText(Integer.parseInt(numM.getText()) + 1+"");
          } else {
          }
      }
      public void insertD(){
          int num = Integer.parseInt(txtNumberD.getText());
          String name = txtNameD.getText();
          String type = comD.getSelectionModel().getSelectedItem().toString();
          int price = Integer.parseInt(txtPriceD.getText());
          if (!DB.insertD("Drinks", num, name, type, price)) {
                listD.add(new Drinks(num, name, type, price));
                doneD.setText("Order is Done");
              doneD.setVisible(true);
              numD.setText(Integer.parseInt(numD.getText()) + 1+"");
          } else {
          
          }
          
      }
      public void getSelectedMeal(){
       
        indexM = tableM.getSelectionModel().getSelectedIndex();
          if (indexM <= -1) {
              return;
          }
          try {
          TableColumn<Meal,Integer> Mealnumber = new TableColumn<>("id");
        TableColumn<Meal,String> Mealname = new TableColumn<>("name");
        TableColumn<Meal,String> Mealtype = new TableColumn<>("type");
        TableColumn<Meal,Integer> Mealprice = new TableColumn<>("price");

       Mealnumber.setCellValueFactory(new PropertyValueFactory<Meal , Integer>("id"));
        Mealname.setCellValueFactory(new PropertyValueFactory<Meal , String>("name"));
        Mealtype.setCellValueFactory(new PropertyValueFactory<Meal,String>("type"));
        Mealprice.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("price"));
        tableM.getColumns().addAll(Mealnumber,Mealname,Mealtype,Mealprice);


        txtNumberm.setText(Mealnumber.getCellData(indexM).toString());
          txtNamem.setText(Mealname.getCellData(indexM).toString());
          comM.getSelectionModel().select(Mealtype.getCellData(indexM));
          txtPricem.setText(Mealprice.getCellData(indexM).toString());
          tableM.getColumns().removeAll(Mealnumber,Mealname,Mealtype,Mealprice);
          System.out.println("ok");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
    }
      public void getSelectedDrinks(){
       
        indexM = tableD.getSelectionModel().getSelectedIndex();
          if (indexM <= -1) {
              return;
          }
          try {
              TableColumn<Drinks,Integer> numDrinks = new TableColumn<>("id");
        TableColumn<Drinks,String> nameDrinks = new TableColumn<>("name");
        TableColumn<Drinks,String> typeDrinks = new TableColumn<>("type");
        TableColumn<Drinks,Integer> priceDrinks = new TableColumn<>("price");
        numDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , Integer>("id"));
        nameDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , String>("name"));
        typeDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,String>("type"));
        priceDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,Integer>("price"));
        listD = DB.getDrinks();
        tableD.getColumns().addAll(numDrinks,nameDrinks,typeDrinks,priceDrinks);
        tableD.setItems(listD);

        txtNumberD.setText(numDrinks.getCellData(indexM).toString());
          txtNameD.setText(nameDrinks.getCellData(indexM).toString());
          comD.getSelectionModel().select(typeDrinks.getCellData(indexM));
          txtPriceD.setText(priceDrinks.getCellData(indexM).toString());
          tableD.getColumns().removeAll(numDrinks,nameDrinks,typeDrinks,priceDrinks);
          System.out.println("ok");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
    }
      public void updateMeal(){
          try {
           int num = Integer.parseInt(txtNumberm.getText());
          String name = txtNamem.getText();
          String type = comM.getSelectionModel().getSelectedItem().toString();
          int price = Integer.parseInt(txtPricem.getText());
                 if (DB.updateD("Where num = " + num , name, type, price)) {
                System.out.println(2);
                listM.set(indexM, new Meal(num, name, type, price));
                doneM.setText("Update is done ");
                doneM.setVisible(true);
                clearM();
                 }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null,e);
          }
      }
      
      public void clearM(){
          txtNumberm.clear();
          txtNamem.clear();
          comM.getSelectionModel().select(-1);
          txtPricem.clear();
      }
           public void updateDrinks(){
          try {
            int num = Integer.parseInt(txtNumberD.getText());
          String name = txtNameD.getText();
          String type = comD.getSelectionModel().getSelectedItem().toString();
          int price = Integer.parseInt(txtPriceD.getText());
                 if (DB.updateD("Where num = " + num , name, type, price)) {
                System.out.println(2);
                listD.set(indexM, new Drinks(num, name, type, price));
                doneD.setText("Update is done ");
                doneD.setVisible(true);
                clearD();
                 }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null,e);
          }
      }
      
      public void clearD(){
          txtNumberD.clear();
          txtNameD.clear();
          comD.getSelectionModel().select(-1);
          txtPriceD.clear();
      }
      public void deleteMeal(){
          if (indexM == -1) {
              return;
          }
          try {
        TableColumn<Meal,Integer> Mealnumber = new TableColumn<>("id");
        TableColumn<Meal,String> Mealname = new TableColumn<>("name");
        TableColumn<Meal,String> Mealtype = new TableColumn<>("type");
        TableColumn<Meal,Integer> Mealprice = new TableColumn<>("price");

       Mealnumber.setCellValueFactory(new PropertyValueFactory<Meal , Integer>("id"));
        Mealname.setCellValueFactory(new PropertyValueFactory<Meal , String>("name"));
        Mealtype.setCellValueFactory(new PropertyValueFactory<Meal,String>("type"));
        Mealprice.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("price"));
        tableM.getColumns().removeAll(Mealnumber,Mealname,Mealtype,Mealprice);

        tableM.getColumns().addAll(Mealnumber,Mealname,Mealtype,Mealprice);
        if (!DB.delete("Meal","num = "+Mealnumber.getCellData(indexM))) {
              
              listM.remove(indexM);
              doneM.setText("Delete is done");
              doneM.setVisible(true);
              numM.setText(Integer.parseInt(numM.getText())-1 + "");
              indexM=-1;
              clearM();
            tableM.getColumns().removeAll(Mealnumber,Mealname,Mealtype,Mealprice);

              
          }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
    
      }
      
      public void deleteDrink(){
          
          if (indexM == -1) {
              return;
          }
          try {
               TableColumn<Drinks,Integer> numDrinks = new TableColumn<>("id");
                TableColumn<Drinks,String> nameDrinks = new TableColumn<>("name");
                TableColumn<Drinks,String> typeDrinks = new TableColumn<>("type");
                TableColumn<Drinks,Integer> priceDrinks = new TableColumn<>("price");
                numDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , Integer>("id"));
                nameDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , String>("name"));
                typeDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,String>("type"));
                priceDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,Integer>("price"));
                tableD.getColumns().addAll(numDrinks,nameDrinks,typeDrinks,priceDrinks);
                if (!DB.delete("Drink","num = "+numDrinks.getCellData(indexM))) {
              
              listD.remove(indexM);
              doneD.setText("Delete is done");
              doneD.setVisible(true);
              numD.setText(Integer.parseInt(numM.getText())-1 + "");
              indexM=-1;
              clearD();
            tableD.getColumns().removeAll(numDrinks,nameDrinks,typeDrinks,priceDrinks);

              
          }
        
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
      }
      
      public void searchM(){
          searchM.textProperty().addListener(new InvalidationListener(){

              @Override
              public void invalidated(Observable o) {
                  if (searchM.textProperty().get().isEmpty()) {
                      tableM.setItems(listM);
                      return;
                  }
                  ObservableList<Meal> tableItems = FXCollections.observableArrayList();
                  ObservableList<TableColumn<Meal,?>> cols = tableM.getColumns();
                  for (int i = 0; i<listM.size();i++) {
                      for (int j = 0; j < cols.size(); j++) {
                          TableColumn col = cols.get(j);
                          String cellValue = col.getCellData(listM.get(i)).toString();
                          cellValue = cellValue.toLowerCase();
                          if (cellValue.contains(searchM.getText().toLowerCase()) && cellValue.startsWith(searchM.getText().toLowerCase())) {
                              tableItems.add(listM.get(i));
                              break;
                          }
                      }
                      
                  }
                  tableM.setItems(tableItems);
              }
          
          });
      }
      public void searchD(){
                   searchD.textProperty().addListener(new InvalidationListener(){

              @Override
              public void invalidated(Observable o) {
                  if (searchD.textProperty().get().isEmpty()) {
                      tableD.setItems(listD);
                      return;
                  }
                  ObservableList<Drinks> tableItems = FXCollections.observableArrayList();
                  ObservableList<TableColumn<Drinks,?>> cols = tableD.getColumns();
                  for (int i = 0; i<listD.size();i++) {
                      for (int j = 0; j < cols.size(); j++) {
                          TableColumn col = cols.get(j);
                          String cellValue = col.getCellData(listD.get(i)).toString();
                          cellValue = cellValue.toLowerCase();
                          if (cellValue.contains(searchD.getText().toLowerCase()) && cellValue.startsWith(searchD.getText().toLowerCase())) {
                              tableItems.add(listD.get(i));
                              break;
                          }
                      }
                      
                  }
                  tableD.setItems(tableItems);
              }
          
          }); 
      }
      public void logout(Event e){
                  try {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Main");
        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
        stage.setX((rectangle2D.getWidth() - stage.getWidth()) / 2);
        stage.setY((rectangle2D.getHeight()- stage.getHeight()) / 2);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TableColumn<Meal,Integer> Mealnumber = new TableColumn<>("id");
        TableColumn<Meal,String> Mealname = new TableColumn<>("name");
        TableColumn<Meal,String> Mealtype = new TableColumn<>("type");
        TableColumn<Meal,Integer> Mealprice = new TableColumn<>("price");

       Mealnumber.setCellValueFactory(new PropertyValueFactory<Meal , Integer>("id"));
        Mealname.setCellValueFactory(new PropertyValueFactory<Meal , String>("name"));
        Mealtype.setCellValueFactory(new PropertyValueFactory<Meal,String>("type"));
        Mealprice.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("price"));
        Mealnumber.setPrefWidth(300);
        Mealname.setPrefWidth(300);
        Mealtype.setPrefWidth(300);
        Mealprice.setPrefWidth(300);

         listM = DB.getMeal();
        tableM.getColumns().addAll(Mealnumber,Mealname,Mealtype,Mealprice);
        tableM.setItems(listM);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        TableColumn<Drinks,Integer> numDrinks = new TableColumn<>("id");
        TableColumn<Drinks,String> nameDrinks = new TableColumn<>("name");
        TableColumn<Drinks,String> typeDrinks = new TableColumn<>("type");
        TableColumn<Drinks,Integer> priceDrinks = new TableColumn<>("price");
        numDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , Integer>("id"));
        nameDrinks.setCellValueFactory(new PropertyValueFactory<Drinks , String>("name"));
        typeDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,String>("type"));
        priceDrinks.setCellValueFactory(new PropertyValueFactory<Drinks,Integer>("price"));
        numDrinks.setPrefWidth(300);
        nameDrinks.setPrefWidth(300);
        typeDrinks.setPrefWidth(300);
        priceDrinks.setPrefWidth(300);
        listD = DB.getDrinks();
        tableD.getColumns().addAll(numDrinks,nameDrinks,typeDrinks,priceDrinks);
        tableD.setItems(listD);
        
      

  
       
      
          
        
        numD.setText(DB.countD("num")+"");
        numM.setText(DB.countM("num")+"");
        
        ObservableList listM = FXCollections.observableArrayList("Meat","Fish","Chicken","Fast Food");
        comM.setItems(listM);
        ObservableList listD = FXCollections.observableArrayList("Cold Drinks","Hot Drinks");
        comD.setItems(listD);

    }    
    
}
