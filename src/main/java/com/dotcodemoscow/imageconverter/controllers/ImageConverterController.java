package com.dotcodemoscow.imageconverter.controllers;

import com.dotcodemoscow.imageconverter.helpers.ImageConverterHelper;
import jankovicsandras.imagetracer.ImageTracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;


@RestController
public class ImageConverterController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "displayImage";
    }


    @PostMapping(value = "/url")
    public ResponseEntity<String> convertFromUrl(@RequestParam("url") String link){
        try {
            HashMap<String, Float> options = ImageConverterHelper.getConverterOptions();

            URL url = new URL(link);
            BufferedImage c = ImageIO.read(url);
            ImageTracer.ImageData imageData = ImageTracer.loadImageData(c);
            byte[][] palette = ImageConverterHelper.getPalette();
            ImageTracer.IndexedImage image = ImageTracer.imagedataToTracedata(imageData, options, palette);
            return new ResponseEntity<String>(Arrays.deepToString(image.array), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/file")
    public ResponseEntity<String> convertFromFile(@RequestParam("file") MultipartFile image){
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            HashMap<String, Float> options = ImageConverterHelper.getConverterOptions();
            ImageTracer.ImageData imageData = ImageTracer.loadImageData(bufferedImage);
            byte[][] palette = ImageConverterHelper.getPalette();
            ImageTracer.IndexedImage ii = ImageTracer.imagedataToTracedata(imageData, options, palette);
            return new ResponseEntity<String>(Arrays.deepToString(ii.array), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

