package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sample.audioOutputs.*;

import java.io.IOException;

//controller for main javafx form
public class Controller {

    //text box for user name
    public TextField costumerNameTextField;

    //list view for devices list
    public ListView devicesListView;

    //radio fields
    public TextField radioName;
    public TextField radioPrice;
    public TextField radioPower;
    public TextField radioWeight;
    public CheckBox am;
    public CheckBox fm;
    public CheckBox cd;
    public CheckBox usb;

    //headphones fields
    public TextField headphonesName;
    public TextField headphonesPrice;
    public TextField headphonesPower;
    public TextField headphonesWeight;
    public TextField headphonesMinFrequency;
    public TextField headphonesMaxFrequency;
    public TextField headphonesLength;
    public CheckBox garniture;

    //speakers fields
    public TextField speakersName;
    public TextField speakersPrice;
    public TextField speakersPower;
    public TextField speakersWeight;
    public TextField speakersMinFrequency;
    public TextField speakersMaxFrequency;
    public CheckBox portable;

    //index of selected device
    public TextField indexTextField;

    //hi-fi system contains devices
    HiFiSystem hiFiSystem=new HiFiSystem();

    //load button click handler
    public void loadClick(ActionEvent actionEvent) {
        try {
            hiFiSystem.Load(costumerNameTextField.getText());
            updateControls();
        }
        //show exception message if it occurs
        catch (IOException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    //save button click handler
    public void saveClick(ActionEvent actionEvent) {
        try {
            hiFiSystem.CustomerName=costumerNameTextField.getText();
            hiFiSystem.Save(hiFiSystem.CustomerName);
        }
        //show exception message if it occurs
        catch (IOException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void nameChanged(ActionEvent actionEvent) {
        hiFiSystem.CustomerName=costumerNameTextField.getText();
    }

    //update list view for hi-fi system instance
    private void updateControls(){
        //costumerNameTextField.setText(hiFiSystem.CustomerName);
        devicesListView.getItems().clear();
        for(int i = 0; i<hiFiSystem.devices.size(); i++)
        {
            //add device in string format and its index in hi-fi system
            devicesListView.getItems().add(devicesListView.getItems().size()+"#"+hiFiSystem.devices.get(i).toString());
        }
    }

    //remove device from hi-fi system
    public void removeClick(ActionEvent actionEvent) {
        try {
            hiFiSystem.devices.remove(Integer.parseInt(indexTextField.getText()));
        }
        //show exception message if it occurs
        catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        //update list view
        updateControls();
    }

    //add radio button click handler
    public void addRadio(ActionEvent actionEvent) {
        Radio radio=new Radio();
        try{
            //try to read values from controls
            radio.name=radioName.getText();
            radio.setPower(Double.parseDouble(radioPower.getText()));
            radio.setPrice(Double.parseDouble(radioPrice.getText()));
            radio.setWeight(Double.parseDouble(radioWeight.getText()));
            radio.AM=am.isSelected();
            radio.CD=cd.isSelected();
            radio.FM=fm.isSelected();
            radio.USB=usb.isSelected();

            hiFiSystem.devices.add(radio);
            updateControls();
        }
        //show exception message if it occurs
        catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    //add headphones button click handler
    public void addHeadphones(ActionEvent actionEvent) {
        Headphones headphones=new Headphones();
        try{

            //try to read values from controls
            headphones.name=headphonesName.getText();
            headphones.garniture=garniture.isSelected();
            headphones.setLength(Double.parseDouble(headphonesLength.getText()));
            headphones.setMaxFrequency(Double.parseDouble(headphonesMaxFrequency.getText()));
            headphones.setMinFrequency(Double.parseDouble(headphonesMinFrequency.getText()));
            headphones.setPower(Double.parseDouble(headphonesPower.getText()));
            headphones.setPrice(Double.parseDouble(headphonesPrice.getText()));
            headphones.setWeight(Double.parseDouble(headphonesWeight.getText()));

            hiFiSystem.devices.add(headphones);
            updateControls();
        }
        //show exception message if it occurs
        catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    //add speakers button click handler
    public void addSpeakers(ActionEvent actionEvent) {
        Speakers speakers=new Speakers();
        try{

            //try to read values from controls
            speakers.name=speakersName.getText();
            speakers.portable=portable.isSelected();
            speakers.setMaxFrequency(Double.parseDouble(speakersMaxFrequency.getText()));
            speakers.setMinFrequency(Double.parseDouble(speakersMinFrequency.getText()));
            speakers.setPower(Double.parseDouble(speakersPower.getText()));
            speakers.setPrice(Double.parseDouble(speakersPrice.getText()));
            speakers.setWeight(Double.parseDouble(speakersWeight.getText()));

            hiFiSystem.devices.add(speakers);
            updateControls();
        }
        //show exception message if it occurs
        catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
