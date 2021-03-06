package client;

import javafx.application.Application;
import javafx.stage.Stage;
import shared.packet.NiceLogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class ClockSyncClient extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	private Stage stage;

	private ConnectScene connectScene;
	private ClockScene clockScene;

	private ClientConnection connection;
	private PacketInterpreter interpreter;
	private Logger logger;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
	    logger = Logger.getLogger("Client");
	    logger.setUseParentHandlers(false);

	    ConsoleHandler consoleHandler = new ConsoleHandler()
	    {
		    {
			    setOutputStream(System.out);
		    }
	    };

	    consoleHandler.setFormatter(new NiceLogFormatter());

	    logger.addHandler(consoleHandler);

	    this.stage = primaryStage;


	    interpreter = new PacketInterpreter(this);
	    connection = new ClientConnection(interpreter, logger);

        primaryStage.setTitle("ClockSync");

	    connectScene = new ConnectScene(this);
	    clockScene = new ClockScene(this);

	    primaryStage.setMinWidth(300);
	    primaryStage.setMinHeight(275);

        primaryStage.setScene(connectScene);
		primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }



	public Stage getStage()
	{
		return stage;
	}

	public ConnectScene getConnectScene()
	{
		return connectScene;
	}

	public ClockScene getClockScene()
	{
		return clockScene;
	}

	public Logger getLogger()
	{
		return logger;
	}

	public ClientConnection getConnection()
	{
		return connection;
	}

	public PacketInterpreter getInterpreter()
	{
		return interpreter;
	}
}
