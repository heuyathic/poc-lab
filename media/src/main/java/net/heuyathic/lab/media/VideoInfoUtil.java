package net.heuyathic.lab.media;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.avformat;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import static org.bytedeco.javacpp.avcodec.AV_CODEC_ID_AAC;
import static org.bytedeco.javacpp.avformat.av_guess_format;

@Slf4j
public class VideoInfoUtil {

    public static final double TIME_BASE = 1000000.0;

    /**
     * get video duration except .hevc
     * @param videoPath
     * @return
     */
    public static double getDurationWithFFmpeg(String videoPath) {
        double duration = -1;
        try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();
            duration = grabber.getLengthInTime() / TIME_BASE;
            grabber.stop();
        } catch (Exception e) {
            log.error("error: ", e);
        }
        return duration;
    }

    /**
     * calculate duration for .hevc video
     * @param videoPath
     * @return
     */
    public static double calcDurationByFpsWithFFmpeg(String videoPath) {
        double duration = -1;
        try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();
            long frameCount = 0;
            Frame frame = grabber.grab();
            while (Objects.nonNull(frame)) {
                frame = grabber.grab();
                frameCount++;
            }
            double fps = grabber.getVideoFrameRate();
            duration = frameCount / fps;
            grabber.stop();
        } catch (Exception e) {
            log.error("error: ", e);
        }
        return duration;
    }

    public static String getMimeType(String suffix, String videoPath) {
        avformat.AVOutputFormat f = av_guess_format(suffix, videoPath, null);
        return f.mime_type().getString();
    }

    public static void convertVideo(String videoPath, String outputFormat, int videoCodec, int audioCodec, int imageWidth, int imageHeight) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/Users/yachen.huang/Testspace/sample." + outputFormat, imageWidth, imageHeight, 1);
        recorder.setFrameRate(30);
        recorder.setFormat(outputFormat);
        if (videoCodec >= 0)
            recorder.setVideoCodec(videoCodec);
        if (audioCodec >= 0)
            recorder.setAudioCodec(audioCodec);
        if (outputFormat.equals("dv")) {
            recorder.setFrameRate(25);
            recorder.setAudioChannels(2);
            recorder.setAudioBitrate(44000);
        }
        recorder.start();
        Frame frame = grabber.grab();
        while (frame != null) {
            recorder.record(frame);
            frame = grabber.grab();
        }
        recorder.stop();
        grabber.stop();
    }

    public static void convertVideo(String videoPath, String outputFormat, int videoCodec, int imageWidth, int imageHeight) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/Users/yachen.huang/Testspace/sample." + outputFormat, imageWidth, imageHeight, 2);
        recorder.setFrameRate(30);
        recorder.setFormat("hevc");
        if (videoCodec >= 0)
            recorder.setVideoCodec(videoCodec);
        recorder.setAudioCodec(AV_CODEC_ID_AAC);
        recorder.start();
        Frame frame = grabber.grab();
        while (frame != null) {
            recorder.record(frame);
            frame = grabber.grab();
        }
        recorder.stop();
        grabber.stop();
    }

    public static void convertVideo(String videoPath, String outputFormat, int imageWidth, int imageHeight) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/Users/yachen.huang/Testspace/sample." + outputFormat, imageWidth, imageHeight, 1);
        recorder.setFrameRate(30);
        recorder.setFormat(outputFormat);
        recorder.start();
        Frame frame = grabber.grab();
        while (frame != null) {
            recorder.record(frame);
            frame = grabber.grab();
        }
        recorder.stop();
        grabber.stop();
    }
}
