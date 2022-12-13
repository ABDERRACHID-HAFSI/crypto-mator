package org.cryptomator.ui.cipherpathgetter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import org.cryptomator.ui.common.DefaultSceneFactory;
import org.cryptomator.ui.common.FxController;
import org.cryptomator.ui.common.FxControllerKey;
import org.cryptomator.ui.common.FxmlFile;
import org.cryptomator.ui.common.FxmlLoaderFactory;
import org.cryptomator.ui.common.FxmlScene;
import org.cryptomator.ui.common.StageFactory;

import javax.inject.Named;
import javax.inject.Provider;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Map;
import java.util.ResourceBundle;

@Module
abstract class CipherPathGetterModule {

	@Provides
	@CipherPathGetterWindow
	@CipherPathGetterScoped
	static FxmlLoaderFactory provideFxmlLoaderFactory(Map<Class<? extends FxController>, Provider<FxController>> factories, DefaultSceneFactory sceneFactory, ResourceBundle resourceBundle) {
		return new FxmlLoaderFactory(factories, sceneFactory, resourceBundle);
	}

	@Provides
	@CipherPathGetterWindow
	@CipherPathGetterScoped
	static Stage provideWindow(StageFactory factory, @Named("CipherPathWindowOwner") Stage owner, ResourceBundle resourceBundle) {
		Stage stage = factory.create();
		//stage.setTitle(resourceBundle.getString("quit.title"));
		stage.setTitle("Get Ciphertext");
		stage.setMinWidth(300);
		stage.setMinHeight(100);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);
		return stage;
	}


	@Provides
	@FxmlScene(FxmlFile.CIPHERPATHGETTER)
	@CipherPathGetterScoped
	static Scene provideQuitForcedScene(@CipherPathGetterWindow FxmlLoaderFactory fxmlLoaders) {
		return fxmlLoaders.createScene(FxmlFile.CIPHERPATHGETTER);
	}


	// ------------------

	@Binds
	@IntoMap
	@FxControllerKey(CipherPathGetterController.class)
	abstract FxController bindController(CipherPathGetterController controller);
}
