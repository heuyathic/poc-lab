package net.heuyathic.lab.media;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.GaussianBlur;

public class Smoother {

    public static void smooth(String filename) {
        opencv_core.Mat image = imread(filename);
        if (image != null) {
            GaussianBlur(image, image, new opencv_core.Size(3, 3), 0);
            imwrite(filename, image);
        }
    }
}
