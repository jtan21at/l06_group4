package L01;
import java.io.FileInputStream;
import java.io.*;


public class PPMImage {
    File image;
    private String magicNumber;
    private static int width;
    private static int height;
    private int maxColorValue;
    static char[] raster = new char[width * height * 3];


    public PPMImage(String ImageFileName){

        try{
            raster = readImage(ImageFileName);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    private char[] readImage(String imageFileName){
        char in;
        int pl = 0;
        char arr[] = new char[300];
        try{

            FileInputStream fin = new FileInputStream(imageFileName);
            do{
                in = (char) fin.read();


                fin.close();
            }
            while((pl = fin.read()) != 1);

        }catch(IOException e){
            e.printStackTrace();
        }

        return arr;
    }

    public File writeFile(String outputImageFileName) {
        File a = null;
        try {
            a = new File(outputImageFileName);
            DataOutputStream output = new DataOutputStream(new FileOutputStream("temp.dat"));
            output.writeUTF(magicNumber + "\n");
            output.writeUTF(width + " " + height + "\n");
            output.writeUTF(maxColorValue + "\n");
            for (int i = 0; i < raster.length; i++) {
                output.writeByte(raster[i]);
            }
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public static void grayscale(){

        try{
            for(int i = 0; i < raster.length; i++){

                //goes through each char of raster and converts into a double to manipulate it and converts it back into a char
                double r = (double) raster[i] + .299;
                raster[i] = (char) r;
                i++;
                double g = (double) raster[i] + .587;
                raster[i] = (char) g;
                i++;
                double b = (double) raster[i] + .114;
                raster[i] = (char) b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void sepia(){

        try{
            for(int i = 0; i < raster.length; i++){

                //goes through each char of raster and converts into a double to manipulate it and convert it back into a char
                double r1 = (double) raster[i] + .393;
                raster[i] = (char) r1;
                i++;
                double g1 = (double) raster[i] + .769;
                raster[i] = (char) g1;
                i++;
                double b1 = (double) raster[i] + .189;
                raster[i] = (char) b1;

                i++;

                double r2 = (double) raster[i] + .349;
                raster[i] = (char) r2;
                i++;
                double g2 = (double) raster[i] + .686;
                raster[i] = (char) g2;
                i++;
                double b2 = (double) raster[i] + .168;
                raster[i] = (char) b2;

                i++;

                double r3 = (double) raster[i] + .272;
                raster[i] = (char) r3;
                i++;
                double g3 = (double) raster[i] + .534;
                raster[i] = (char) g3;
                i++;
                double b3 = (double) raster[i] + .131;
                raster[i] = (char) b3;

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void negative() {}













}
