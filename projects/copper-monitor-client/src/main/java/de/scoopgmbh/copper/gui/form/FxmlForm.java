package de.scoopgmbh.copper.gui.form;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import de.scoopgmbh.copper.gui.util.MessageProvider;


public class FxmlForm<T extends Node> extends Form<T> {

	String fxmlPath;
	Object controler;

	
//	public FxmlForm(TabPane mainTabPane, String menueItemtext, String fxmlPath, Object controler, MessageProvider messageProvider) {
//		super(mainTabPane, menueItemtext, messageProvider);
//		this.fxmlPath = fxmlPath;
//		this.controler = controler;
//	}

	public FxmlForm(T displayTarget, String menueItemtextKey, String fxmlPath, Object controler, MessageProvider messageProvider, ShowFormStrategy<T> showFormStrategie) {
		super(displayTarget, menueItemtextKey, messageProvider, showFormStrategie);
		this.fxmlPath = fxmlPath;
		this.controler = controler;
	}

	@Override
	public Node createContent() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
		fxmlLoader.setController(controler);
		fxmlLoader.setResources(messageProvider.getBundle());
		try {
			return (Parent) fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	
	
}
