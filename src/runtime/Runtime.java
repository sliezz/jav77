package runtime;

import java.io.InputStream;
import DbObject.Factory;
import DbObject.Compiled.FactoryCompiled;
import Exec.Root.*;
import SQL.DB;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.stage.Stage;
import lang.Language;
import UI.UI;
import UI.UI_Command;
import UI.UI_ToolbarButton;
import UI.Compiled.UICompiled;
import enums.DataType;
import enums.Lang;
import enums.RootHandlers;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 

public class Runtime extends Application {
	
	private static Runtime thisApp;
	public static Stage primaryStage;
	
	public static Session session = new Session();
	public static Permissions permissions = new Permissions();
	public static UI ui = new UICompiled();

	public static BackgroundFill bgFillWhite = new BackgroundFill(Color.WHITE, null, null);
	public static BackgroundFill bgFillLtGrey = new BackgroundFill(Color.GHOSTWHITE, null, null);
	public static BackgroundImage[] bgImage = new BackgroundImage[1];
	public static BackgroundFill[] bgFill = new BackgroundFill[1];

	public static MenuBar menuBar = new MenuBar();
	public static ToolBar toolBar = new ToolBar();
	public static TabPane tabPane = new TabPane();
	public static ListView<String> logView = new ListView<String>();
	public static SplitPane splitPane = new SplitPane();
	public static Label status = new Label("Ready");

	public static DB mem_db;
	
	public static Root root;

	public static Factory factory = new FactoryCompiled();

	public static Integer StatusCode = 0;
	
	public static Language lang = new Language();
	public static Lang baseLanguage = Lang.Ru;

	public static String LastErrorMessage;
	
	public void open(String obj) {
		Node node = tabPane.lookup("#"+obj);
		if (node == null) {
			Tab tab = new Tab("tab_"+obj , new Label("new pane for "+obj));
			tab.setId(obj);
			tabPane.getTabs().add(tab);
		}

		node = tabPane.lookup("#"+obj);
		if (node != null) {
			tabPane.getTabs().forEach((tabx) -> { 
			    if (tabx.getId() == obj) {
			    	tabPane.getSelectionModel().select(tabx);
			    }
			});
			return;
		}
	}

	private long last_timestamp=System.currentTimeMillis();

	public void DebugLog(String remark) {
		System.out.println(remark);
	}

	public void Benchmark() {
		last_timestamp=System.currentTimeMillis();
	}

	public void Benchmark(String remark) {
		long timestamp=System.currentTimeMillis();
		DebugLog(remark+" ["+String.valueOf((timestamp-last_timestamp)/1000.)+" sec"+"]");
		last_timestamp=timestamp;
	}

	public static DataType parseDataType(String datatype) {
		if (datatype.compareToIgnoreCase("Число")==0 || datatype.compareToIgnoreCase("Number")==0)
			return DataType.Numeric;

		if (datatype.compareToIgnoreCase("Строка")==0 || datatype.compareToIgnoreCase("String")==0)
			return DataType.String;

		if (datatype.compareToIgnoreCase("Дата")==0 || datatype.compareToIgnoreCase("Date")==0)
			return DataType.Date;

		if (datatype.compareToIgnoreCase("Дата")==0 || datatype.compareToIgnoreCase("Date")==0)
			return DataType.Date;

		return DataType.Any;
	}

	public void AddMenuItem(Menu menu, String item_path, int command_id, String command, int image_index) {
		String delimeter = "\n";
		if (!item_path.endsWith(delimeter)) {
			item_path += delimeter;
		}
		int pos = item_path.indexOf(delimeter);
		String item = item_path.substring(0, pos);
		item_path = item_path.substring(pos+1);
		
		if (item_path == delimeter) item_path="";

		EventHandler<ActionEvent> event_handler = new EventHandler<ActionEvent>() { 
	        public void handle(ActionEvent e) 
	        {
	        	MenuItem item = ((MenuItem)e.getSource()); 
	        	String id = item.getId();
//	        	open(id);

	        	String txt = item.getText();
	        	((RootCode)(root.code)).outputString(id);
	        };
		};

		MenuItem menuitem = menu.getItems().stream().
				filter(some_element -> some_element.getText().compareToIgnoreCase(item)==0).
				findFirst().orElse(null);
		if (menuitem == null) {
			if (item_path.isEmpty()) {
				menuitem = new MenuItem(item);
				menuitem.setId(command);
				menuitem.setOnAction(event_handler);
				
				if (image_index >= 0) {
					ImageView iv = new ImageView(ui.images);
					iv.setCache(true);
					iv.setViewport(new Rectangle2D(ui.images.getHeight()*image_index, 0, ui.images.getHeight(), ui.images.getHeight()));
					menuitem.setGraphic(iv);
				}

				menu.getItems().add(menuitem);
			} else {
				Menu submenu = new Menu(item);
				menu.getItems().add(submenu);
				menuitem = (MenuItem)submenu;
			}
		}
		if (!item_path.isEmpty()) {
			Menu submenu = (Menu)menuitem;
			AddMenuItem(submenu, item_path, command_id, command, image_index);
		}
	}

	public void AddMenu(MenuBar menuBar, String item_path, int command_id, String command, int image_index) {
		String delimeter = "\n";
		if (!item_path.endsWith(delimeter)) {
			item_path += delimeter;
		}
		int pos = item_path.indexOf(delimeter);
		String item = item_path.substring(0, pos);
		item_path = item_path.substring(pos+1);

		Menu menu = menuBar.getMenus().stream().
			filter(some_element -> some_element.getText().compareToIgnoreCase(item)==0).
			findFirst().orElse(null);
		if (menu == null) {
			menu = new Menu(item);
			menuBar.getMenus().add(menu);
		}

		if (!item_path.isEmpty()) {
			AddMenuItem(menu, item_path, command_id, command, image_index);
		}
	}

	public void AddToobarButton(ToolBar toolBar, String item, int command_id, String command, int image_index) {
		Button button = new Button(item);
		
		if (image_index >= 0) {
			ImageView iv = new ImageView(ui.images);
			iv.setCache(true);
			iv.setViewport(new Rectangle2D(ui.images.getHeight()*image_index, 0, ui.images.getHeight(), ui.images.getHeight()));
			button.setGraphic(iv);
		}
		
		button.getStyleClass().add("toolbar-button1");

		EventHandler<ActionEvent> event_handler = new EventHandler<ActionEvent>() { 
	        public void handle(ActionEvent e) 
	        {
	        	Button item = ((Button)e.getSource()); 
	        	String id = item.getId();
//	        	open(id);

	        	String txt = item.getText();
	        	((RootCode)(root.code)).outputString(id);
	        };
		};

		button.setId(command);
		button.setOnAction(event_handler); 

		toolBar.getItems().add(button);
	}

	public void UILoad(String uiName, MenuBar menuBar, ToolBar toolBar) {
		if (!ui.Load(uiName)) {
			return;
		}

		ui.menu.Items.forEach(item -> {
			UI_Command cmd = ui.commands.Commands.get(item.id);
			if (item.text.indexOf("Сервис")!=0 && item.text.indexOf("Справка")!=0)
			{
				int image_index = -1;
				UI_ToolbarButton btn = ui.toolbar.Items.stream().
					filter(some_element -> some_element.id==item.id).
					findFirst().orElse(null);
				if (btn != null) image_index=btn.image_index;
	
				if (cmd != null) {
					AddMenu(menuBar, item.text, item.id, cmd.command+"\n"+cmd.param, image_index);
				}
			}
		});

		ui.menu.Items.forEach(item -> {
			UI_Command cmd = ui.commands.Commands.get(item.id);
			if (item.text.indexOf("Сервис")==0 || item.text.indexOf("Справка")==0)
			{
				int image_index = -1;
				UI_ToolbarButton btn = ui.toolbar.Items.stream().
					filter(some_element -> some_element.id==item.id).
					findFirst().orElse(null);
				if (btn != null) image_index=btn.image_index;
	
				if (cmd != null) {
					AddMenu(menuBar, item.text, item.id, cmd.command+"\n"+cmd.param, image_index);
				}
			}
		});

		ui.toolbar.Items.forEach(item -> {
			if (item.id == 0) {
				toolBar.getItems().add(new Separator());
			} else {
				UI_Command cmd = ui.commands.Commands.get(item.id);
				if (cmd != null) {
					AddToobarButton(toolBar, item.text, item.id, cmd.command+"\n"+cmd.param, item.image_index);
				}
			}
		});

		toolBar.setPadding(new Insets(2));
	}
	
	public static Runtime getRuntime() {
		return thisApp;
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static int invokeHandler(RootHandlers handler) {
		switch (handler) {
		case OnStart:
		case OnQuit:
		case OnExtEvent:
//TODO		StatusCode=1;
		}
		return StatusCode;
	}
	
	@Override
	public void start(Stage _primaryStage) {
		thisApp = this;
		primaryStage = _primaryStage;

		DebugLog("Init common objects");

		// init in-memory database
		try {
			mem_db = new DB("");
		} catch (Exception e) {
			DebugLog("Failed to init in-memory database");
			e.printStackTrace();
			return;
		}
		
		DebugLog("Creating main window");
		try {
//			URL url = getClass().getResource("MainFrame.fxml");
//	        Parent root = FXMLLoader.load(url);
//			Scene scene = new Scene(root,400,400);

		
			UILoad("Админ", menuBar, toolBar);
			Benchmark("UI loaded");

			// background
			{
				InputStream input = Runtime.class.getResourceAsStream("/Resources/__Picture-100000ed.png");
		        bgImage[0] = new BackgroundImage(new Image(input), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		        bgFill[0] = bgFillWhite;
		        
		        tabPane.setBackground(new Background(bgFill, bgImage));
		        //tabPane.setBackground(new Background(bgFillLtGrey));
			}
			Benchmark("Background loaded");
			
			tabPane.setSide(Side.BOTTOM);
			tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

			tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
		            if (newTab == null) {
				        tabPane.setBackground(new Background(bgFill, bgImage));
		            } else {
				        tabPane.setBackground(new Background(bgFillLtGrey));
		            }
		        });

	        splitPane.getItems().addAll(tabPane, logView);
	        splitPane.setDividerPositions(1.0f, 0.0f);


		    BorderPane root = new BorderPane();
		    root.setTop(new VBox(menuBar,toolBar));
		    root.setCenter(splitPane);
		    root.setBottom(status);

			Scene scene = new Scene (root, 999, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Benchmark("Scene is ready");

			primaryStage.setScene(scene);
			primaryStage.setTitle("Application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Benchmark("Main window created");

		// init root
		root = new Root();
		root.Init();
		Benchmark("Root initiated");

		// init session
		session.Signin();
		
		// run tests
		Tests.run();
		Benchmark("Tests finished");
		
		// ready
		status.setText(lang.Ready());
	}
	
	@Override
	public void stop() {
		// Release root
		if (root != null) {
			root.Release();
		}
		root = null;

		// clear refs
		thisApp=null;
		primaryStage=null;
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
}
