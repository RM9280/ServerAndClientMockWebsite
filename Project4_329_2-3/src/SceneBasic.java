/**
 * Author: Yashdeep Deswal
 * Represents a basic scene in JavaFX.
 */
import javafx.scene.Scene;

public abstract class SceneBasic {

    protected Scene scene; // The JavaFX scene object.
    protected String titleText; // The title text for the scene.

    /**
     * Default constructor.
     */
    public SceneBasic() {

    }

    /**
     * Constructor to initialize the SceneBasic with a title.
     *
     * @param titleText The title text for the scene.
     */
    public SceneBasic(String titleText) {
        this.titleText = titleText;
    }

    /**
     * Get the JavaFX Scene object.
     *
     * @return The JavaFX Scene object.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Get the title text for the scene.
     *
     * @return The title text.
     */
    public String getTitleText() {
        return titleText;
    }

    public void logout(){

    }
}
