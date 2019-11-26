package vistas;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

import static jdk.nashorn.internal.objects.Global.Infinity;


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
            //System.out.println(ementa.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
            dataLB.setText(ref.getData());
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
            sopaDesc.setText(ref.getSopa());

            //Prato Carne
            HBox carneHB = new HBox();
            carneHB.setPrefHeight(-1.0);
            carneHB.setPrefWidth(-1.0);
            //carneHB.setPadding(new Insets(10.0));
            Label carneTitle = new Label();
            carneTitle.setAlignment(Pos.CENTER);
            carneTitle.setMaxHeight(-Infinity);
            carneTitle.setMaxWidth(-Infinity);
            carneTitle.setMinHeight(-Infinity);
            carneTitle.setMinWidth(-Infinity);
            carneTitle.setText("Prato Carne");
            carneTitle.setPrefHeight(-1.0);
            carneTitle.setPrefWidth(-1.0);
            carneTitle.setStyle("-fx-text-fill: #7D222B;-fx-font-weight: bold;");
            carneTitle.setFont(new Font("Arial", 18.0));
            ImageView favIcon = new ImageView();
            favIcon.setFitHeight(30.0);
            favIcon.setFitWidth(30.0);
            favIcon.setPickOnBounds(true);
            favIcon.setPreserveRatio(true);
            File file = new File("..\\m6.PNG");
            Image image = new Image(file.toURI().toString());
            favIcon.setImage(image);
            carneHB.getChildren().addAll(carneTitle, favIcon);
            Label carneDesc = new Label();
            carneDesc.setText(ref.getPratoCarne());

            //Prato Peixe
            HBox peixeHB = new HBox();
            peixeHB.setPrefHeight(-1.0);
            peixeHB.setPrefWidth(-1.0);
            Label peixeTitle = new Label();
            peixeTitle.setAlignment(Pos.CENTER);
            peixeTitle.setMaxHeight(-Infinity);
            peixeTitle.setMaxWidth(-Infinity);
            peixeTitle.setMinHeight(-Infinity);
            peixeTitle.setMinWidth(-Infinity);
            peixeTitle.setText("Prato Peixe");
            peixeTitle.setPrefHeight(-1.0);
            peixeTitle.setPrefWidth(-1.0);
            peixeTitle.setStyle("-fx-text-fill: #7D222B;-fx-font-weight: bold;");
            peixeTitle.setFont(new Font("Arial", 18.0));
            ImageView favIcon2 = new ImageView();
            favIcon2.setImage(image);
            favIcon.setFitHeight(30.0);
            favIcon.setFitWidth(30.0);
            favIcon.setPickOnBounds(true);
            favIcon.setPreserveRatio(true);
            peixeHB.getChildren().addAll(peixeTitle, favIcon2);
            Label peixeDesc = new Label();
            peixeDesc.setText(ref.getPratoPeixe());

            //Preço
            HBox precoHB = new HBox();
            precoHB.setAlignment(Pos.CENTER);
            precoHB.setMaxHeight(200.0);
            precoHB.setPrefHeight(100.0);
            precoHB.setPrefWidth(200.0);
            Label precoTitle = new Label();
            precoTitle.setText("Preço: ");
            precoTitle.setStyle("-fx-text-fill: #7D222B;-fx-font-weight: bold;");
            precoTitle.setFont(new Font("Arial", 16.0));
            Label precoDesc = new Label();
            precoDesc.setFont(new Font(16.0));
            precoDesc.setText("" + ref.getPreco() +" €");
            precoHB.getChildren().addAll(precoTitle, precoDesc);

            //Botão Selecionar
            HBox btHB = new HBox();
            btHB.setAlignment(Pos.CENTER);
            btHB.setPrefHeight(100.0);
            btHB.setPrefWidth(200.0);
            Button btnS = new Button();
            btnS.setStyle("-fx-border-color: #8A817A; -fx-background-color: #7D222B;-fx-text-fill: white;");
            btnS.setText("Selecionar");
            btnS.setId(""+ref.getIdRefeicao());  //Estou atribuir o id do botão igual ao id da refeição
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


