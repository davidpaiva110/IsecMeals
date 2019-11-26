package vistas;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.Refeicao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaEmentaController {

    private PaneOrganizer po;
    private ArrayList<Refeicao> ementa;
    int i = 0;
    @FXML
    private HBox hBoxEmenta;


    public ConsultaEmentaController(PaneOrganizer po) {
        this.po = po;
        try {
            ementa = po.controlador.getEmenta();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ementa = new ArrayList<>();
        Date data = new Date("12/12/2019");
        ementa.add( new Refeicao(2, "creme cenoura", "vitela", "filetes", "fruta", "gelatina", 1, 1, data) );
    }

    public void initialize() {

        hBoxEmenta.getChildren().clear();
        for (Refeicao ref : ementa) {
            VBox principal = new VBox();
            principal.setMaxHeight(300.0);
            principal.setPrefHeight(100.0);
            principal.setPrefWidth(200.0);
            principal.setStyle("-fx-border-color: black;-fx-border-width: 3;-fx-padding: 10;");

            //Data
            HBox dataHB = new HBox();
            dataHB.setAlignment(Pos.CENTER);
            dataHB.setPrefWidth(68.0);
            dataHB.setPrefHeight(29.0);
            dataHB.setStyle("");
            Label dataLB = new Label();
            dataLB.setText(ref.getData().toString());
            dataHB.getChildren().add(dataLB);

            //Almoço/Jantar
            HBox tipoHB = new HBox();
            tipoHB.setAlignment(Pos.TOP_CENTER);
            tipoHB.setPrefWidth(115.0);
            tipoHB.setPrefHeight(48.0);
            Label tipoLB = new Label();
            tipoLB.setText((ref.getAlmocoJantar() == 1) ? "Almoço" : "Jantar");  //Confirmar isto depois
            tipoHB.getChildren().add(tipoLB);

            //Sopa
            Label sopaTitle = new Label();
            sopaTitle.setStyle("-fx-text-fill: #7D222B;-fx-font-weight: bold;");
            sopaTitle.setFont(new Font("Arial", 18.0));
            sopaTitle.setText("Sopa");
            Label sopaDesc = new Label();
            sopaTitle.setText(ref.getSopa());

            //Prato Carne
            HBox carneHB = new HBox();
            Label carneTitle = new Label();
            carneTitle.setText("Prato Carne");
            ImageView favIcon = new ImageView();
            File file = new File("../m6.PNG");
            Image image = new Image(file.toURI().toString());
            favIcon.setImage(image);
            carneHB.getChildren().addAll(carneTitle, favIcon);
            Label carneDesc = new Label();
            carneDesc.setText(ref.getPratoCarne());

            //Prato Peixe
            HBox peixeHB = new HBox();
            Label peixeTitle = new Label();
            carneTitle.setText("Prato Carne");
            ImageView favIcon2 = new ImageView();
            favIcon.setImage(image);
            peixeHB.getChildren().addAll(peixeTitle, favIcon2);
            Label peixeDesc = new Label();
            peixeDesc.setText(ref.getPratoPeixe());

            //Preço
            HBox precoHB = new HBox();
            Label precoTitle = new Label();
            precoTitle.setText("Preço");
            Label precoDesc = new Label();
            precoDesc.setText("" + ref.getPreco());
            precoHB.getChildren().addAll(precoTitle, precoDesc);

            //Botão Selecionar
            HBox btHB = new HBox();
            Button btnS = new Button();
            btnS.setText("Selecionar");
            btnS.setId(""+ref.getIdRefeicao());
            btnS.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(btnS.getId());  //apenas para teste
                }
            });
            btHB.getChildren().addAll(btnS);

            principal.getChildren().addAll(dataHB, tipoHB, sopaTitle, sopaDesc, carneHB, carneDesc, peixeHB, peixeDesc, precoHB, btHB);
            hBoxEmenta.getChildren().add(principal);
        }


    }
}


