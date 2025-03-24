package com.vkl;

import com.vkl.domain.Car;
import com.vkl.domain.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller extends Component implements Initializable {

    @FXML
    private ChoiceBox<String> fiatCBox;
    private String[] fiatOpcoes = {"Uno Padrão","Uno Economico","Uno Esportivo"};

    @FXML
    private ChoiceBox<String> renaultCBox;
    private String[] renaultOpcoes = {"Twingo Padrão","Twingo Economico","Twingo Esportivo"};

    @FXML
    private ListView<String> garagemList;

    @FXML
    private TabPane tabPane;

    Garagem garagem = new Garagem();
    List<ChoiceBox> marcaList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        marcaList.add(renaultCBox);
        marcaList.add(fiatCBox);
        fiatCBox.getItems().addAll(fiatOpcoes);
        renaultCBox.getItems().addAll(renaultOpcoes);



        tabPane.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldIndex, newIndex) -> {
                    if (newIndex.intValue() != 0){
                        UiInfo.getInstance().setMontadoraIndex(newIndex.intValue());
                        System.out.println(UiInfo.getInstance().getMontadoraIndex());
                    }
                }
        );

        garagemList.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldIndex, newIndex) -> {
                    if (tabPane.getSelectionModel().getSelectedIndex() == 0){
                        UiInfo.getInstance().setCarroSelecionado(newIndex.intValue());
                        System.out.println("Garagem, carro selecionado: ");
                        System.out.println(newIndex);
                    }
                }
        );
    }

    public void comprar(){
        int montadoraIndex = UiInfo.getInstance().getMontadoraIndex() - 1;
        ChoiceBox marca = marcaList.get(montadoraIndex);
        int modeloIndex = marca.getSelectionModel().getSelectedIndex() + 1;


        Car carro = Factory.getFactory((montadoraIndex + 1)).create(modeloIndex);

        garagem.adicionarCarros(carro);
        garagemList.getItems().add(carro.getModelo());

    }

    public void vender(){
        int result = JOptionPane.showConfirmDialog(this,"Tem certeza que quer vender este veiculo?", "Venda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
            garagem.venderCarro(UiInfo.getInstance().getCarroSelecionado());
            garagemList.getItems().remove(UiInfo.getInstance().getCarroSelecionado());
        }
    }


}