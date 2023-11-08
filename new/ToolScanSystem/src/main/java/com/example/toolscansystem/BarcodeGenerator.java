package com.example.toolscansystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeGenerator {

    @FXML
    private TextField barcodeTextBox;

    @FXML
    private AnchorPane rootPane;
    public void onBarcodeGenerateButtonClick(ActionEvent actionEvent) throws BarcodeException {

        String image_name = barcodeTextBox.getText() + ".png";
        String myString = barcodeTextBox.getText();
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(25f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            //write to png file
            FileOutputStream fos = new FileOutputStream(".\\"+image_name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            System.out.println("Done!");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
