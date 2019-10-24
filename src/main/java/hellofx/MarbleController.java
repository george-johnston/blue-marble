package hellofx;

import java.time.DateTimeException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MarbleController {
	
	BlueMarble blueMarble = new BlueMarble();

    @FXML
    private ImageView marbleImage;

    @FXML
    private Button marbleButton;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private CheckBox enhanceCheckBox;
    
    @FXML
    private Text instructionText;

    
    @FXML
    void dateSelected(ActionEvent event) throws DateTimeException {
    	LocalDate dateSelected = datePicker.getValue();
    	
	    	if(dateSelected.isAfter(LocalDate.of(2019, 6, 27))) {	    		
	    		marbleButton.setDisable(true);
	    		instructionText.setEffect(new Glow(.7));
	    		
	    		//throw new DateTimeException("Please pick a date before June 27, 2019");
	    	} else {
	    		marbleButton.setDisable(false);
	    		instructionText.setEffect(null);
	    	}
	    	
	    	
	    	//marbleButton.setDisable(dateSelected.isAfter(LocalDate.of(2019, 6, 27)));
	    	
    		if(dateSelected.isBefore(LocalDate.of(2018, 6, 1))) {
	    		enhanceCheckBox.setVisible(true);
	    	} else {
	    		enhanceCheckBox.setVisible(false);
	    	}
    }

    @FXML
    void marbleButtonClicked(ActionEvent event) {
    	String datePick = datePicker.getValue().toString();
    	System.out.println("Date picked: " + datePick);
    	
    	blueMarble.setDate(datePick);
    	blueMarble.setEnhanced(enhanceCheckBox.isSelected());
    	marbleImage.setImage(new Image(blueMarble.getImage()));
    	//marbleImage.setImage(new Image(BlueMarble.getMostRecentImage()));

    }

}
