package boundary;

import java.io.IOException;
import java.util.ArrayList;

import control.ReaderController;
import control.UserController;
import entity.Book;
import entity.ConstantsAndGlobalVars;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The user page, it opens windows for different operations the user can do.
 */
public class UserPageController {
	/**
	 * instance variables: userController - an instance of UserController used for
	 * different operations the user can do. readerController - an instance of
	 * ReaderController used for the search functionality. currentUser - the
	 * currently logged in user
	 */
	protected UserController userController;
	protected ReaderController readerController;
	protected User currentUser;

	@FXML
	protected ImageView userImg;

	@FXML
	protected ImageView myreservesImg;

	@FXML
	protected ImageView myborrowsImg;

	@FXML
	protected ImageView historyImg;

	@FXML
	protected ImageView settingsImg;

	@FXML
	protected Button logoutBtn;

	@FXML
	protected ComboBox searchTypeComboBox;
	protected ObservableList<String> searchTypeList;

	@FXML
	protected TextField searchTF;

	@FXML
	protected Button searchBtn;

	@FXML
	protected Label usernameLabel;

	@FXML
	protected Button myBorrowsBtn;

	@FXML
	protected Button reservesBtn;

	@FXML
	protected Button historyBtn;

	@FXML
	protected Button settingsBtn;

	/**
	 * logs the current user out of the system, and opens the main page.
	 * 
	 * @param event - auto-generated by SceneBuilder.
	 * @throws InterruptedException - {@link control.UserController#logout(User)}
	 *                              uses semaphores (@see
	 *                              control.UserController#logout(User)) and throws
	 *                              an InterruptedException should acquiring the
	 *                              semaphore encounter a problem.
	 * @throws IOException          - thrown should loading the FXML file encounter
	 *                              a problem.
	 */
	@FXML
	protected void logoutHandler(ActionEvent event) throws InterruptedException, IOException {
		if (userController.logout(currentUser)) {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(getClass().getResource("/boundary/MainPageGUI.fxml").openStream());
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("User Page");
			primaryStage.setOnCloseRequest(e -> close());
			((Stage) logoutBtn.getScene().getWindow()).close();
			primaryStage.show();
		}
	}

	/**
	 * @see boundary.MainPageController#searchHandler(ActionEvent).
	 */
	@FXML
	protected void searchHandler(ActionEvent event) {
		try {
			String selectedCombo = searchTypeList.get(searchTypeComboBox.getSelectionModel().getSelectedIndex());
			if (searchTF.getText().isEmpty()) {
				searchTF.setPromptText("enter keyword !!!");
				searchTF.setStyle("-fx-prompt-text-fill: red;" + "-fx-font-weight: bold;");
			} else {
				ArrayList<Book> searchResult = readerController.searchForBook(selectedCombo, searchTF.getText());
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Parent root = loader.load(getClass().getResource("/boundary/UserSearchResultsGUI.fxml").openStream());
				primaryStage.setScene(new Scene(root));
				primaryStage.setTitle("Search Results");
				UserSearchResultsController usrc = loader.getController();
				usrc.setResults(searchResult);
				usrc.loadUser(currentUser);
				primaryStage.show();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			searchTF.clear();
			searchTF.setPromptText("choose search type !!!");
			searchTF.setStyle("-fx-prompt-text-fill: red;" + "-fx-font-weight: bold;");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * opens the {@link boundary.UserBorrowsController} window.
	 * 
	 * @param event - auto-generated by SceneBuilder.
	 * @throws IOException - thrown should loading the FXML file encounter a
	 *                     problem.
	 */
	@FXML
	protected void showBorrowsHandler(ActionEvent event) throws IOException {
		// Stage primaryStage = (Stage)
		// ((Node)event.getSource()).getScene().getWindow();
		Stage primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/boundary/UserBorrowsGUI.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/boundary/TableDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Active Borrows");
		
		UserBorrowsController bc = loader.getController();
		primaryStage.setOnCloseRequest(e ->bc.closeBorrowWindow());
		
		bc.loadUser(userController.getCurrentUser());
		primaryStage.show();
	}

	/**
	 * opens the {@link boundary.UserHistoryController} window.
	 * 
	 * @param event - auto-generated by SceneBuilder.
	 * @throws IOException - thrown should loading the FXML file encounter a
	 *                     problem.
	 */
	@FXML
	void showHistoryHandler(ActionEvent event) throws IOException {
		// Stage primaryStage = (Stage)
		// ((Node)event.getSource()).getScene().getWindow();
		Stage primaryStage = new Stage();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/boundary/HistoryGUI.fxml").openStream());
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("History");
		UserHistoryController hc = loader.getController();
		primaryStage.setOnCloseRequest(e -> hc.closeHistoryWindow());
		hc.loadUser(userController.getCurrentUser());
		primaryStage.show();
	}

	/**
	 * opens the {@link boundary.UserReservationsController} window.
	 * 
	 * @param event - auto-generated by SceneBuilder.
	 * @throws IOException - thrown should loading the FXML file encounter a
	 *                     problem.
	 */
	@FXML
	protected void showReservesHandler(ActionEvent event) throws IOException {
		// Stage primaryStage = (Stage) ((Node)
		// event.getSource()).getScene().getWindow();
		Stage primaryStage = new Stage();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/boundary/ReservationsGUI.fxml").openStream());
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Reservations");
		UserReservationsController ur = loader.getController();
		primaryStage.setOnCloseRequest(e -> ur.closeReservationWindow());
		ur.loadUser(userController.getCurrentUser());
		primaryStage.show();
	}

	/**
	 * opens the {@link boundary.SettingController} window.
	 * 
	 * @param event - auto-generated by SceneBuilder.
	 * @throws IOException - thrown should loading the FXML file encounter a
	 *                     problem.
	 */
	@FXML
	protected void showSettingsHandler(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/boundary/SettingGUI.fxml").openStream());
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("My setting");
		SettingController settingController = loader.getController();
		primaryStage.setOnCloseRequest(e -> settingController.closeSettingWindow());
		settingController.loadUser(currentUser);
		primaryStage.show();
	}

	/**
	 * called upon loading the FXML file, initializes some GUI elements and instance
	 * variables.
	 */
	@FXML
	void initialize() {

		userController = UserController.getInstance(ConstantsAndGlobalVars.ipAddress,
				ConstantsAndGlobalVars.DEFAULT_PORT);
		readerController = ReaderController.getInstance(ConstantsAndGlobalVars.ipAddress,
				ConstantsAndGlobalVars.DEFAULT_PORT);
		setSearchTypeComboBox();
		String img = "/images/search.png";
		searchBtn.setStyle("-fx-background-image: url('" + img + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size: 100% 100%;");
		Image imgrborrow = new Image("/images/borrow.png");

		myborrowsImg.setImage(imgrborrow);

		Image imgres = new Image("/images/reserves.png");
		myreservesImg.setImage(imgres);

		Image imghistory = new Image("/images/history.png");
		historyImg.setImage(imghistory);

		Image imgsitting = new Image("/images/setting.png");
		settingsImg.setImage(imgsitting);

		img = "/images/logout.png";
		logoutBtn.setStyle("-fx-background-image: url('" + img + "'); " + "-fx-min-height: 50px; "
				+ "-fx-min-width: 70px;" + "-fx-background-size: 20px 20px;" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-position: center 8px;");

	}

	/**
	 * loads the data of the currently logged in user into an instance variable and
	 * into some GUI elements.
	 * 
	 * @param u - the currently logged in user.
	 */
	public void loadUser(User u) {
		this.currentUser = u;
		usernameLabel.setText("      " + currentUser.getFirstName() + " " + currentUser.getLastName());
		Image img = new Image("/images/user.png");
		userImg.setImage(img);
	}

	/**
	 * fills the search type comboBox
	 */
	protected void setSearchTypeComboBox() {
		ArrayList<String> arr = new ArrayList<>();
		for (String s : ConstantsAndGlobalVars.searchType)
			arr.add(s);
		searchTypeList = FXCollections.observableArrayList(arr);
		searchTypeComboBox.setItems(searchTypeList);
	}

	/**
	 * the method to be called should the user close the window(either by the X
	 * button on the top left corner of the window or by pressing alt-F4) it
	 * disconnects all clients from the server and exits.
	 */

	protected void close() {
		UserController uc = UserController.getInstance(ConstantsAndGlobalVars.ipAddress,
				ConstantsAndGlobalVars.DEFAULT_PORT);
		uc.disconnectClient();
		ReaderController rc = ReaderController.getInstance(ConstantsAndGlobalVars.ipAddress,
				ConstantsAndGlobalVars.DEFAULT_PORT);
		rc.disconnectClient();
		System.exit(0);
	}
}