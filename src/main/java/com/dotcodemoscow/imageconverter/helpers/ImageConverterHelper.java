package com.dotcodemoscow.imageconverter.helpers;

import java.util.HashMap;

public class ImageConverterHelper {

    public static HashMap<String, Float> getConverterOptions(){
        HashMap<String,Float> options = new HashMap<String,Float>();
        options.put("ltres",1f);
        options.put("qtres",1f);
        options.put("pathomit",8f);

        // Color quantization
        options.put("colorsampling",1f); // 1f means true ; 0f means false: starting with generated palette
        options.put("numberofcolors",16f);
        options.put("mincolorratio",0.02f);
        options.put("colorquantcycles",3f);

        // SVG rendering
        options.put("scale",1f);
        options.put("roundcoords",1f); // 1f means rounded to 1 decimal places, like 7.3 ; 3f means rounded to 3 places, like 7.356 ; etc.
        options.put("lcpr",0f);
        options.put("qcpr",0f);
        options.put("desc",1f); // 1f means true ; 0f means false: SVG descriptions deactivated
        options.put("viewbox",0f); // 1f means true ; 0f means false: fixed width and height

        // Selective Gauss Blur
        options.put("blurradius",0f); // 0f means deactivated; 1f .. 5f : blur with this radius
        options.put("blurdelta",20f); // smaller than this RGB difference will be blurred
        return options;
    }

    public static byte[][] getPalette(){
        byte[][] palette = new byte[8][4];
        for(int colorcnt=0; colorcnt < 8; colorcnt++){
            palette[colorcnt][0] = (byte)( -128 + colorcnt * 32); // R
            palette[colorcnt][1] = (byte)( -128 + colorcnt * 32); // G
            palette[colorcnt][2] = (byte)( -128 + colorcnt * 32); // B
            palette[colorcnt][3] = (byte)127; 		      // A
        }
        return palette;
    }
}
