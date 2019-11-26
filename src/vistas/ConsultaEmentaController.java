package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Refeicao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaEmentaController {

    private PaneOrganizer po;
    private ArrayList<Refeicao> ementa;
    int i = 0;
    @FXML
    private HBox hBoxEmenta;


    public ConsultaEmentaController(PaneOrganizer po) {
        this.po = po;
    }

    public void initialize() {
        try {
            this.ementa = (ArrayList<Refeicao>) po.getControlador().getEmenta();
        } catch (SQLException e) {

        }

        for (Refeicao ref : ementa) {
            VBox principal = new VBox();
            //Data
            HBox dataHB = new HBox();
            Label dataLB = new Label();
            dataLB.setText(ref.getData().toString());
            dataHB.getChildren().add(dataLB);
            //Almoço/Jantar
            HBox tipoHB = new HBox();
            Label tipoLB = new Label();
            tipoLB.setText((ref.getAlmocoJantar() == 1) ? "Almoço" : "Jantar");  //Confirmar isto depois
            tipoHB.getChildren().add(tipoLB);

            //Sopa
            Label sopaTitle = new Label();
            sopaTitle.setText("Sopa");
            Label sopaDesc = new Label();
            sopaTitle.setText(ref.getSopa());

            //Prato Carne
            HBox carneHB = new HBox();
            Label carneTitle = new Label();
            carneTitle.setText("Prato Carne");
            ImageView favIcon = new ImageView();
            File file = new File("../m6.png");
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
            btnS.setId("id" + i++);
            btnS.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(btnS.getId());  //apenas para teste
                }
            });
            btHB.getChildren().addAll(btnS);

            principal.getChildren().addAll(principal, dataHB, tipoHB, sopaTitle, sopaDesc, carneHB, carneDesc, peixeHB, peixeDesc, precoHB, btHB);
            hBoxEmenta.getChildren().add(principal);
        }


    }
}



