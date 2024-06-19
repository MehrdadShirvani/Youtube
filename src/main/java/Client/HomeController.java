package Client;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class HomeController {
    @FXML
    ImageView logoImage;
    @FXML
    Button searchButton;
    @FXML
    TextField searchTextField;
    @FXML
    ToggleButton homeMenuButton;
    @FXML
    ToggleButton shortsMenuButton;
    @FXML
    ToggleButton subsMenuButton;
    @FXML
    ToggleButton channelMenuButton;
    @FXML
    ToggleButton historyMenuButton;
    @FXML
    VBox leftVBox;
    @FXML
    SVGPath homeIcon;
    @FXML
    SVGPath shortsIcon;
    @FXML
    SVGPath subsIcon;
    @FXML
    SVGPath channelIcon;
    @FXML
    SVGPath historyIcon;
    Boolean isMinimized;

    public void initialize() {
        isMinimized = false;
        //Search transition
        ScaleTransition st = new ScaleTransition(Duration.millis(200), searchTextField);
        st.setToX(1.2);
        searchTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                st.playFromStart();
            } else {
                st.stop();
                searchTextField.setScaleX(1.0);
            }
        });
        searchTextField.scaleXProperty().addListener((obs, oldScale, newScale) -> {
            if (searchTextField.getScaleX() == 1) {
                searchButton.setStyle("-fx-border-color: #6C6C6C");
                searchButton.setScaleX(1);
                searchButton.setScaleY(1);
            } else {
                searchButton.setStyle("-fx-border-color: #065fd4");
            }
        });
        //Menu SVG
        homeMenuButton.setSelected(true);
        homeMenuButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                homeIcon.setContent("M0 7V18H6V12H10V18H16V7L8 0L0 7Z");
            } else {
                homeIcon.setContent("M12 4.33L19 10.45V20H15V14H9V20H5V10.45L12 4.33ZM12 3L4 10V21H10V15H14V21H20V10L12 3Z");
            }
        });
        shortsMenuButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                shortsIcon.setContent("m17.77 10.32-1.2-.5L18 9.06c1.84-.96 2.53-3.23 1.56-5.06s-3.24-2.53-5.07-1.56L6 6.94c-1.29.68-2.07 2.04-2 3.49.07 1.42.93 2.67 2.22 3.25.03.01 1.2.5 1.2.5L6 14.93c-1.83.97-2.53 3.24-1.56 5.07.97 1.83 3.24 2.53 5.07 1.56l8.5-4.5c1.29-.68 2.06-2.04 1.99-3.49-.07-1.42-.94-2.68-2.23-3.25zM10 14.65v-5.3L15 12l-5 2.65z");
            } else {
                shortsIcon.setContent("M10 14.65v-5.3L15 12l-5 2.65zm7.77-4.33-1.2-.5L18 9.06c1.84-.96 2.53-3.23 1.56-5.06s-3.24-2.53-5.07-1.56L6 6.94c-1.29.68-2.07 2.04-2 3.49.07 1.42.93 2.67 2.22 3.25.03.01 1.2.5 1.2.5L6 14.93c-1.83.97-2.53 3.24-1.56 5.07.97 1.83 3.24 2.53 5.07 1.56l8.5-4.5c1.29-.68 2.06-2.04 1.99-3.49-.07-1.42-.94-2.68-2.23-3.25zm-.23 5.86-8.5 4.5c-1.34.71-3.01.2-3.72-1.14-.71-1.34-.2-3.01 1.14-3.72l2.04-1.08v-1.21l-.69-.28-1.11-.46c-.99-.41-1.65-1.35-1.7-2.41-.05-1.06.52-2.06 1.46-2.56l8.5-4.5c1.34-.71 3.01-.2 3.72 1.14.71 1.34.2 3.01-1.14 3.72L15.5 9.26v1.21l1.8.74c.99.41 1.65 1.35 1.7 2.41.05 1.06-.52 2.06-1.46 2.56z");
            }
        });
        subsMenuButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                subsIcon.setContent("M20 7H4V6h16v1zm2 2v12H2V9h20zm-7 6-5-3v6l5-3zm2-12H7v1h10V3z");
            } else {
                subsIcon.setContent("M10 18v-6l5 3-5 3zm7-15H7v1h10V3zm3 3H4v1h16V6zm2 3H2v12h20V9zM3 10h18v10H3V10z");
            }
        });
        historyMenuButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                historyIcon.setContent("M14.97 16.95 10 13.87V7h2v5.76l4.03 2.49-1.06 1.7zM12 2C8.73 2 5.8 3.44 4 5.83V3.02H2V9h6V7H5.62C7.08 5.09 9.36 4 12 4c4.41 0 8 3.59 8 8s-3.59 8-8 8-8-3.59-8-8H2c0 5.51 4.49 10 10 10s10-4.49 10-10S17.51 2 12 2z");
            } else {
                historyIcon.setContent("M14.97 16.95 10 13.87V7h2v5.76l4.03 2.49-1.06 1.7zM22 12c0 5.51-4.49 10-10 10S2 17.51 2 12h1c0 4.96 4.04 9 9 9s9-4.04 9-9-4.04-9-9-9C8.81 3 5.92 4.64 4.28 7.38c-.11.18-.22.37-.31.56L3.94 8H8v1H1.96V3h1v4.74c.04-.09.07-.17.11-.25.11-.22.23-.42.35-.63C5.22 3.86 8.51 2 12 2c5.51 0 10 4.49 10 10z");
            }
        });
    }

    public void menuSwip(MouseEvent mouseEvent) {

        if (!isMinimized) {
            Transition transition = new Transition() {
                {
                    setCycleDuration(Duration.seconds(0.7));
                }

                @Override
                protected void interpolate(double frac) {
                    double maxWidth = 100 + ((5 - 100) * frac);
                    leftVBox.setMaxWidth(maxWidth);
                }
            };
            transition.play();
        } else {
            Transition transition = new Transition() {
                {
                    setCycleDuration(Duration.seconds(0.7));
                }

                @Override
                protected void interpolate(double frac) {
                    double maxWidth = 5 + ((historyMenuButton.getPrefWidth() - 5) * frac);
                    leftVBox.setMaxWidth(maxWidth);
                }
            };
            transition.play();
        }
        isMinimized = !isMinimized;
    }

    public void checkLetter(KeyEvent keyEvent) {
        // slash for search
        if(keyEvent.getCode() == KeyCode.SLASH)
        {
            searchTextField.requestFocus();
        }
    }

    public void checkLetterSearch(KeyEvent keyEvent) {
        // esc for search canceling
        if(keyEvent.getCode() == KeyCode.ESCAPE)
        {
            searchTextField.setText("");
            searchButton.requestFocus();
        }
    }
}