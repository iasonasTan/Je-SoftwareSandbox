package app.avcalc.main;

import java.util.ArrayList;
import java.util.List;

import com.jjfx.context.Context;
import com.jjfx.message.Message;
import com.jjfx.receiver.MessageReceiver;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements Context {
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage mStage;
	
	private final List<MessageReceiver> mReceivers = new ArrayList<>();
	
	@Override
	public void start(final Stage stage) throws Exception {
		mStage = stage;
		registerReceiver(m -> {
			if(m.getAction().equals("switch_page")) {
				String pageName = m.getBundle().getString("page_name");
				Parent s = Context.loadFXML(
						this,
						getClass().getResource("/layout/"+pageName+".fxml"),
						getClass().getResource("/style/style.css")
				);
				stage.setScene(new Scene(s));
			}
		});
		
		Message message = Message.newBuilder()
				.putExtra("page_name", "home")
				.setAction("switch_page")
				.build();
		broadcastMessage(message);
		
		stage.setTitle("Average Calculator");
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void broadcastMessage(Message msg) {
		mReceivers.forEach(receiver -> receiver.onReceive(msg));
		
	}

	@Override
	public void registerReceiver(MessageReceiver receiver) {
		mReceivers.add(receiver);
	}

	@Override
	public Stage getRootStage() {
		return mStage;
	}
}
