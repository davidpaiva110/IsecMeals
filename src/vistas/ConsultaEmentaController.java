package vistas;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.Favoritos;
import modelo.Refeicao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static jdk.nashorn.internal.objects.Global.Infinity;


public class ConsultaEmentaController {

    public static final int PRATO_CARNE_TYPE = 0;
    public static final int PRATO_PEIXE_TYPE = 1;

    private PaneOrganizer po;
    private ArrayList<Refeicao> ementa;
    private ArrayList<Favoritos> favoritos;
    int i = 0;
    @FXML private HBox hBoxEmenta;
    @FXML private Label lbSaldo;
    public ConsultaEmentaController(PaneOrganizer po) {
        this.po = po;
        try {
            ementa = po.controlador.getEmenta();
            favoritos = po.controlador.getFavoritos();
        } catch (SQLException e) {
            ementa=new ArrayList<>();
        }
    }

    public void initialize() {
        try {
            double saldo = po.getControlador().getSaldo();
            DecimalFormat df = new DecimalFormat("0.00");
            String saldoFormatado = df.format(saldo);
            lbSaldo.setText(saldoFormatado + "€");
        } catch (SQLException e) {
            lbSaldo.setText("Indisponível");
        }
        hBoxEmenta.getChildren().clear();
        if(ementa.size()==0){
            Label label=new Label("Não existem refeições disponíveis!");
            hBoxEmenta.getChildren().add(label);
        }
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
            tipoLB.setText((ref.getAlmocoJantar() == 0) ? "Almoço" : "Jantar");  //Confirmar isto depois
            tipoHB.getChildren().add(tipoLB);

            //Sopa
            Label sopaTitle = new Label();
            sopaTitle.setStyle("-fx-text-fill: #7D222B;-fx-font-weight: bold;");
            sopaTitle.setFont(new Font("Arial", 18.0));
            sopaTitle.setText("Sopa");
            Label sopaDesc = new Label();
            sopaDesc.setText(ref.getSopa());

            //===================== Prato Carne =====================
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
            File file = new File("src/m6.png");
            Image image = new Image(file.toURI().toString());
            favIcon.setImage(image);
            carneHB.getChildren().addAll(carneTitle, favIcon);

            // Verificar se o prato de carne é um favorito
            for (Favoritos fav : favoritos){
                if(fav.getPrato().equals(ref.getPratoCarne())){
                    File files = new File("src/m3.png");
                    Image images = new Image(files.toURI().toString());
                    favIcon.setImage(images);
                    favIcon.setId("true");
                    break;
                }
            }

            //Tratar o click da imagem no prato de carne
            favIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    File files;
                    Image images;
                    if (Boolean.parseBoolean(favIcon.getId()) == false) {
                        try {
                            po.getControlador().addFavorito(ref.getPratoCarne(), PRATO_CARNE_TYPE);  //0 - prato de carne | 1 - prato de peixe
                        } catch (SQLException e) {
                            // e.printStackTrace();
                            // System.out.println("impossivel adicionar favorito");
                        }
                        files = new File("src/m3.png");
                        images = new Image(files.toURI().toString());
                        favIcon.setImage(images);
                        favIcon.setId("true");
                    } else if (Boolean.parseBoolean(favIcon.getId()) == true){
                        for (Favoritos fav : favoritos) {
                            if (fav.getPrato().equals(ref.getPratoCarne())) {
                                try {
                                    po.getControlador().RemoveFavorito(fav.getIdFavorito());
                                    favIcon.setImage(image);
                                    favIcon.setId("false");
                                } catch (Exception e) {
                                    //e.printStackTrace();
                                }
                            }
                        }
                    }
                    try {
                        favoritos = po.getControlador().getFavoritos();
                    } catch (SQLException e) {
                    }
                }
            });
            Label carneDesc = new Label();
            carneDesc.setText(ref.getPratoCarne());

            //===================== Prato Peixe =====================
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
            favIcon2.setFitHeight(30.0);
            favIcon2.setFitWidth(30.0);
            favIcon2.setPickOnBounds(true);
            favIcon2.setPreserveRatio(true);
            peixeHB.getChildren().addAll(peixeTitle, favIcon2);

            // Verificar se o prato de peixe é um favorito
            for (Favoritos fav : favoritos){
                if(fav.getPrato().equals(ref.getPratoPeixe())){
                    File files = new File("src/m3.png");
                    Image images = new Image(files.toURI().toString());
                    favIcon2.setImage(images);
                    favIcon2.setId("true");
                    break;
                }
            }
            //Tratar o click da imagem no prato de peixe
            favIcon2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    File files;
                    Image images;
                    if (Boolean.parseBoolean(favIcon2.getId()) == false) {
                        try {
                            po.getControlador().addFavorito(ref.getPratoPeixe(), PRATO_PEIXE_TYPE);  //0 - prato de carne | 1 - prato de peixe
                        } catch (SQLException e) {
                            // e.printStackTrace();
                            // System.out.println("impossivel adicionar favorito");
                        }
                        files = new File("src/m3.png");
                        images = new Image(files.toURI().toString());
                        favIcon2.setImage(images);
                        favIcon2.setId("true");
                    } else if (Boolean.parseBoolean(favIcon2.getId()) == true){
                        for (Favoritos fav : favoritos) {
                            if (fav.getPrato().equals(ref.getPratoPeixe())) {
                                try {
                                    po.getControlador().RemoveFavorito(fav.getIdFavorito());
                                    favIcon2.setImage(image);
                                    favIcon2.setId("false");
                                } catch (Exception e) {
                                    //e.printStackTrace();
                                }
                            }
                        }
                    }
                    try {
                        favoritos = po.getControlador().getFavoritos();
                    } catch (SQLException e) {
                    }
                }
            });


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
                    try {
                        po.setComprarSenhaView(ref);
                    } catch (IOException e) {
                    }
                }
            });
            btHB.getChildren().addAll(btnS);
            try {
                if(ref.isJaComprada() || ref.getPreco()>po.getControlador().getSaldo())
                    principal.getChildren().addAll(dataHB, tipoHB, sopaTitle, sopaDesc, carneHB, carneDesc, peixeHB, peixeDesc, precoHB);
                else
                    principal.getChildren().addAll(dataHB, tipoHB, sopaTitle, sopaDesc, carneHB, carneDesc, peixeHB, peixeDesc, precoHB, btHB);
            } catch (SQLException e) {
                principal.getChildren().addAll(dataHB, tipoHB, sopaTitle, sopaDesc, carneHB, carneDesc, peixeHB, peixeDesc, precoHB);
            }
            hBoxEmenta.getChildren().add(principal);
        }


    }
    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setMenuUserView();
    }
}


