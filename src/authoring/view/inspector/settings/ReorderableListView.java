package authoring.view.inspector.settings;

import authoring.AuthoringController;
import game_object.level.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import resources.ResourceBundles;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Provides a settings view that allows a user to reorder items in a list by dragging and dropping them around a JavaFX
 * ListView object. For example, a user could use this to edit the order of levels in their game. The custom cell
 * factory was inspired by code found on StackOverflow here:
 * https://stackoverflow.com/questions/20412445/how-to-create-a-reorder-able-tableview-in-javafx
 *
 * @author Will Long
 * @version 12/11/16
 */
public class ReorderableListView<T> extends AbstractSettingsView {
    private String myTitle;
    private ObservableList<T> myObservableList;
    private ListView<T> myListView;
    private ResourceBundle myProperties;
    private T myCurrentlyCopiedItem;

    public ReorderableListView(AuthoringController controller, String title, List<T> list) {
        super(controller);
        myTitle = title;
        myObservableList = FXCollections.observableList(list);
        myProperties = ResourceBundles.inspectorProperties;
    }

    @Override
    protected void initUI() {
        super.initUI();
        myListView = new ListView<>();
        myListView.setCellFactory(cell -> new DraggableCell());
        myContent.getChildren().add(myListView);
    }


    @Override
    public void initializeSettings() {
        myLabel.setText(myTitle);
        myListView.setItems(myObservableList);
    }

    public void setList(List<T> list) {
        myObservableList = FXCollections.observableList(list);
        myListView.setItems(myObservableList);
    }

    @Override
    protected void updateLayoutSelf() {
        super.initUI();
        myListView.setPrefWidth(getWidth());
        myListView.setPrefHeight(Double.parseDouble(myProperties.getString("LIST_CELL_HEIGHT")) *
                myObservableList.size()+5);
    }

    private class DraggableCell extends ListCell<T> {
        private double myBaseOpacity;

        DraggableCell() {
            setPrefHeight(Double.parseDouble(myProperties.getString("LIST_CELL_HEIGHT")));
            setAlignment(Pos.CENTER);
            myBaseOpacity = getOpacity();

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }
                myCurrentlyCopiedItem = getItem();
                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItemString());
                dragboard.setContent(content);
                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    setOpacity(Double.parseDouble(myProperties.getString("DRAG_OVER_OPACITY")));
                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    setOpacity(myBaseOpacity);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }
                Dragboard dragboard = event.getDragboard();
                boolean success = false;
                if (dragboard.hasString()) {
                    int index = myObservableList.indexOf(getItem());

                    myObservableList.remove(myCurrentlyCopiedItem);
                    myObservableList.add(index, myCurrentlyCopiedItem);

                    success = true;
                    getController().getEnvironment().triggerUpdate();
                }
                event.setDropCompleted(success);
                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(getItemString());
            }
        }

        private String getItemString() {
            if (getItem() == null) {
                return null;
            }
            if (getItem() instanceof Level) {
                return ((Level) getItem()).getId();
            }
            return getItem().toString();
        }
    }
}
