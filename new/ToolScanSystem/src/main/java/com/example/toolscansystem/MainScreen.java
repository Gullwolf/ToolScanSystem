package com.example.toolscansystem;

import com.itextpdf.text.pdf.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;


import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;


public class MainScreen {
    @FXML
    private AnchorPane rootPane;

    public void onMoveToolButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("moveTool.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onCloseButtonClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onBarcodeGenerateButtonClick(ActionEvent actionEvent) throws BarcodeException {

        String image_name = "TEST.png";
        String myString = "TEST STRING";
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
}



