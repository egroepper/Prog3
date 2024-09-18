package gui;

import automat.Verkaufsautomat;
import handlers.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import kuchenImpl.ObstkuchenImpl;
import parser.Parser;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.Duration;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Handler fuer CRUD
    private KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler;
    private KuchenAuflistenEventHandler kuchenAuflistenEventHandler;
    private KuchenInspizierenEventHandler kuchenInspizierenEventHandler;
    private KuchenEntfernenEventHandler kuchenEntfernenEventHandler;


    @FXML
    private TextField input_hinzufuegen;
    @FXML
    private TextField input_entfernen;
    @FXML
    private TextField input_update;

    @FXML
    private TableColumn<ObstkuchenImpl, Integer> fachnummer_spalte;
    @FXML
    private TableColumn<ObstkuchenImpl, Date> inspektionsdatum_spalte;
    @FXML
    private TableColumn<ObstkuchenImpl, Duration> verblHaltbarkeit_spalte;
    
    @FXML
    private TableView<ObstkuchenImpl> Kuchenliste_tabelle;

    // Buttons haben no usage hier, dafuer indirekt ueber FXML Datei (onAction)
    @FXML
    private Button button_aendern;
    @FXML
    private Button button_auflisten;
    @FXML
    private Button button_entfernen;
    @FXML
    private Button button_hinzufuegen;
    
    @FXML
    final ObservableList<ObstkuchenImpl> obstkuchenObservableList = FXCollections.observableArrayList();
    
    final Verkaufsautomat vka = new Verkaufsautomat(20);
    final Parser parser = new Parser();

    public Controller() throws Exception {} // hier Vorschlag von Intellij angenommen wegen Exception in Verkaufsautomatsignatur


   // Einfuegen erfolgt nebenlaeufig
   @FXML
   public void kuchenEinfuegen(ActionEvent actionEvent) {
        String eingabe = input_hinzufuegen.getText();
        try {
            ObstkuchenImpl obstkuchen = parser.eingabenParsen(eingabe);
            new Thread(() -> {
                try {
                    vka.kuchenEinfuegen(obstkuchen.getObstsorte(), obstkuchen.getPreis(), obstkuchen.getNaehrwert(), obstkuchen.getHaltbarkeit(), obstkuchen.getAllergene());
                    synchronized (obstkuchenObservableList) {
                        Platform.runLater(() -> obstkuchenObservableList.add(obstkuchen));  // Quelle: https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx und Vorschlag von Intellij angenommen
                         // Kuchenliste_tabelle.setItems(obstkuchenObservableList);
                         Kuchenliste_tabelle.refresh();
                        // fuegt Kuchen immmer nur an Fachnummer 0 ein, deswegen wieder auskommentiert
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        input_hinzufuegen.clear();
    }

    @FXML
    public void kuchenEntfernen(ActionEvent actionEvent) {
        String eingabe = input_entfernen.getText();
        try {
            int fach = Integer.parseInt(eingabe);
            vka.kuchenEntfernen(fach);
            synchronized (obstkuchenObservableList) {
                obstkuchenObservableList.remove(fach);
                // Kuchenliste_tabelle.setItems(obstkuchenObservableList);
                // Kuchenliste_tabelle.refresh();
            }
        } catch (NumberFormatException | NoSuchElementException e) { // hier Vorschlag von Intellij angenommen
            throw new RuntimeException(e);
        }
        input_entfernen.clear();
    }
    @FXML
    public void inspektionsdatumAendern(ActionEvent actionEvent) {
        String eingabe = input_update.getText();
        try {
            int fach = Integer.parseInt(eingabe);
            vka.inspektionsdatumAendern(fach);
            synchronized (obstkuchenObservableList) {
                ObstkuchenImpl updateObstkuchen = vka.obstkuchenListe[fach];
                for (int i = 0; i < obstkuchenObservableList.size(); i++) {
                    if (obstkuchenObservableList.get(i).getFachnummer() == fach) {
                        obstkuchenObservableList.set(i, updateObstkuchen);
                        break;
                    }
                }
                Kuchenliste_tabelle.setItems(obstkuchenObservableList);
            }
        } catch (NumberFormatException | NoSuchElementException e) { // hier Vorschlag von Intellij angenommen
            throw new RuntimeException(e);
        }
        input_update.clear();
    }
    @FXML
    public void kuchenAuflisten(ActionEvent actionEvent) {
        try {
                obstkuchenObservableList.clear();
                obstkuchenObservableList.addAll(vka.kuchenAuflisten());
                Kuchenliste_tabelle.setItems(obstkuchenObservableList);
                Kuchenliste_tabelle.refresh();
        } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  // fuer alles Folgende von "private int draggedIndex = -1; bis inkl. der Methode "private void handleDragDropped" gilt:
  // Quelle: ChatGPT s. Screenshots "Drag&Drop" Teil 1 und 2 im Ordner Quellen
  private int draggedIndex = -1;

    @FXML
    private void handleDragDetected(MouseEvent mouseEvent) {
        draggedIndex = Kuchenliste_tabelle.getSelectionModel().getSelectedIndex();
        if (draggedIndex >= 0) {
            Dragboard dragboard = Kuchenliste_tabelle.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(Integer.toString(draggedIndex));
            dragboard.setContent(clipboardContent);
        }
    }

    @FXML
    private void handleDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML
    private void handleDragDropped(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        boolean success = false;
        if (dragboard.hasString()) {
            int droppedIndex = Kuchenliste_tabelle.getSelectionModel().getSelectedIndex();
            if (droppedIndex >= 0 && droppedIndex != draggedIndex) {
                ObstkuchenImpl draggedItem = obstkuchenObservableList.get(draggedIndex);
                ObstkuchenImpl droppedItem = obstkuchenObservableList.get(droppedIndex);
                int tempFachnummer = draggedItem.getFachnummer();
                draggedItem.setFachnummer(droppedItem.getFachnummer());
                droppedItem.setFachnummer(tempFachnummer);

                success = true;
            }
        }
        draggedIndex = -1;
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }
    @Override
    // Quelle: "JavaFX and Scene Builder Course - IntelliJ #30: TableView and TableColumn" https://youtu.be/fnU1AlyuguE?feature=shared
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fachnummer_spalte.setCellValueFactory(new PropertyValueFactory<>("fachnummer"));
        inspektionsdatum_spalte.setCellValueFactory(new PropertyValueFactory<>("inspektionsdatum"));
        verblHaltbarkeit_spalte.setCellValueFactory(new PropertyValueFactory<>("verbleibendeHaltbarkeit"));
        Kuchenliste_tabelle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Für den folgenden Code gilt:
        // Quelle: ChatGPT s. Screenshot "drag&drop" Teil 3 und 4 im Ordner Quellen
        Kuchenliste_tabelle.setRowFactory(tv -> {
                    TableRow<ObstkuchenImpl> row = new TableRow<>();
                    row.setOnDragOver(event -> {
                        if (!row.isEmpty() && draggedIndex != -1 && row.getIndex() != draggedIndex) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });

                    row.setOnDragDropped(event -> {
                        Dragboard dragboard = event.getDragboard();
                        boolean success = false;
                        if (dragboard.hasString()) {
                            int droppedIndex = row.getIndex();
                            ObstkuchenImpl draggedItem = obstkuchenObservableList.get(draggedIndex);
                            if (droppedIndex >= 0 && droppedIndex < obstkuchenObservableList.size()) {
                                ObstkuchenImpl droppedItem = obstkuchenObservableList.get(droppedIndex);
                                int tempFachnummer = draggedItem.getFachnummer();
                                draggedItem.setFachnummer(droppedItem.getFachnummer());
                                droppedItem.setFachnummer(tempFachnummer);
                                success = true;
                            }
                        }
                        draggedIndex = -1;
                        event.setDropCompleted(success);
                        event.consume();
                    });
                    return row;
           });
       }

    public void setKuchenEinfuegenEventHandler(KuchenEinfuegenEventHandler einfuegenEventHandler) {
        this.kuchenEinfuegenEventHandler = einfuegenEventHandler;
    }
    public void setVerkaufsautomat(Verkaufsautomat vkaParallel) {
    }

    public void setKuchenAuflistenEventHandler(KuchenAuflistenEventHandler auflistenEventHandler) {
        this.kuchenAuflistenEventHandler = auflistenEventHandler;
    }

    public void setKuchenInspizierenEventHandler(KuchenInspizierenEventHandler inspizierenEventHandler) {
        this.kuchenInspizierenEventHandler = inspizierenEventHandler;
    }


    public void setKuchenEntfernenEventHandler(KuchenEntfernenEventHandler entfernenEventHandler) {
        this.kuchenEntfernenEventHandler = entfernenEventHandler;
    }
}
