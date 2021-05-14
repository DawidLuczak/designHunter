package sample.menu;

import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sample.Controller;
import sample.ControllerInterface;
import sample.workspace.Workspace;

public class NewFilePanel {
	private TextField title, height, width;
	private GridPane gridPane;
	private Dialog<String> dialog;
	private Button acceptButton;

	
	public NewFilePanel(Controller controller){
		menuDialogPane(controller);
		dialog.showAndWait();
	}
	
	private void menuGridPanel(){
		gridPane = new GridPane();
		
		title = new TextField();
		height = new TextField();
		width = new TextField();
		
		NewFilePaneInterface.sizeInputLimiter(height, 4);
		NewFilePaneInterface.sizeInputLimiter(width, 4);
		
		gridPane.add(new javafx.scene.control.Label("Title "), 1, 1);
		gridPane.add(title, 2, 1);
		gridPane.add(new javafx.scene.control.Label("Height "), 1, 2);
		gridPane.add(height, 2, 2);
		gridPane.add(new javafx.scene.control.Label("Width "), 1, 3);
		gridPane.add(width, 2, 3);
	}
	
	private void menuDialogPane(Controller controller){
		dialog = new Dialog<>();
		dialog.setTitle("Create new file.");
		dialog.setHeaderText("Hello artist!");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
		acceptButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.APPLY);
		
		menuGridPanel();
		dialog.getDialogPane().setContent(gridPane);
		
		dialog.getDialogPane().addEventHandler(Event.ANY,
				event -> acceptButton.setDisable(
						title.getText().equals("")
								|| !NewFilePaneInterface.checkSizeInput(height)
								|| !NewFilePaneInterface.checkSizeInput(width)));
		
		acceptButton.setOnAction(actionEvent -> createNewWorkspace(controller));
	}
	
	private void createNewWorkspace(Controller controller){
		Workspace workspace = new Workspace(title.getText(), Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
		ControllerInterface.addToWorkspaceList(controller, workspace);
		workspace.getWorkspaceContent().getActiveLayer().addContentElement(new Canvas(workspace.getWidth(), workspace.getHeight()));
	}
	
	
}
