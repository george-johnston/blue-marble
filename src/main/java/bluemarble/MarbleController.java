package bluemarble;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.json.JSONException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MarbleController {

	BlueMarble blueMarble = new BlueMarble();
	ColorAdjust colorAdjust = new ColorAdjust();

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
    private CheckBox blackWhiteCheckBox;

	@FXML
	void dateSelected(ActionEvent event) throws DateTimeException {
		LocalDate dateSelected = datePicker.getValue();

		if (dateSelected.isAfter(LocalDate.now())) {
			Alert alert = new Alert(AlertType.ERROR, "Error: Future date selected");
			alert.showAndWait();

		}

		if (dateSelected.isAfter(LocalDate.of(2019, 6, 27))) {
			marbleButton.setDisable(true);
			instructionText.setEffect(new Glow(.7));

			// throw new DateTimeException("Please pick a date before June 27, 2019");
		} else {
			marbleButton.setDisable(false);
			instructionText.setEffect(null);
			blackWhiteCheckBox.setDisable(false);
		}

		// marbleButton.setDisable(dateSelected.isAfter(LocalDate.of(2019, 6, 27)));

		if (dateSelected.isBefore(LocalDate.of(2018, 7, 1))) {
			//enhanceCheckBox.setVisible(true);
			enhanceCheckBox.setDisable(false);
		} else {
			//enhanceCheckBox.setVisible(false);
			enhanceCheckBox.setDisable(true);
			enhanceCheckBox.setSelected(false);
		}
	}

	@FXML
	void marbleButtonClicked(ActionEvent event) {
		String datePick = datePicker.getValue().toString();
		System.out.println("Date picked: " + datePick);

		blueMarble.setDate(datePick);
		blueMarble.setEnhanced(enhanceCheckBox.isSelected());
		
		Image image = null;
		try{
			image = new Image(blueMarble.getImage());
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION, "Ooops! There's no image for the date selected. Please select another date.");
			alert.showAndWait();
		}
		
		if (blackWhiteCheckBox.isSelected()) {
			colorAdjust.setHue(-1.0);		
			marbleImage.setEffect(colorAdjust);
		} else {	
			marbleImage.setEffect(null);
		}
		marbleImage.setImage(image);		

	}

}
