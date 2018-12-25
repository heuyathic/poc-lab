package net.heuyathic.lab.media;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

import static org.bytedeco.javacpp.avcodec.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VideoInfoUtilTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    private static final double EXPECTED_DURATION = 5.5;
    private static final double REPEAT_TIME  = 1000.0;
    private static final double REPEAT_TIME_HEVC  = 10.0;

    // Correctness tests
    @Test
    public void should_get_3g2_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.3g2"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_3gp_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.3gp"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_asf_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.asf"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_avi_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.avi"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_dv_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.dv"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_flv_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.flv"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_hevc_video_duration() {
        double actual = VideoInfoUtil.calcDurationByFpsWithFFmpeg(getPath("example.hevc"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_hevc_video_duration_advanced() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.hevc"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_mov_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.mov"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_mp4_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.mp4"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_mpeg_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.mpeg"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_mpg_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.mpg"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    @Test
    public void should_get_wmv_video_duration() {
        double actual = VideoInfoUtil.getDurationWithFFmpeg(getPath("example.wmv"));
        assertThat(Math.abs(EXPECTED_DURATION - actual)).isLessThan(0.5);
    }

    // performance tests for 1MB MP4
    @Test
    public void performance_test_for_3g2_641KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.3g2"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3g2 641KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_3gp_641KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.3gp"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3gp 641KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_asf_459KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.asf"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("asf 459KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_avi_450KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.avi"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("avi 450KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_dv_21MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.dv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("dv 21MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_flv_476KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.flv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("flv 476KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_hevc_139KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME_HEVC; i++) {
            VideoInfoUtil.calcDurationByFpsWithFFmpeg(getPath("1m/sample_1mb.hevc"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME_HEVC;
        log.info("hevc 139KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(304);
    }

    @Test
    public void performance_test_for_mov_625KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.mov"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mov 625KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(9);
    }

    @Test
    public void performance_test_for_mp4_1MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.mp4"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mp4 1MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(11);
    }

    @Test
    public void performance_test_for_mpeg_641KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.mpeg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpeg 641KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_mpg_641KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.mpg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpg 641KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_wmv_459KB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("1m/sample_1mb.wmv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("wmv 459KB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    // performance tests for 10MB MP4
    @Test
    public void performance_test_for_3g2_6MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.3g2"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3g2 6MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_3gp_6MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.3gp"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3gp 6MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_asf_4MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.asf"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("asf 4MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_avi_4MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.avi"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("avi 4MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_dv_239MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.dv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("dv 239MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_flv_4MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.flv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("flv 4MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_hevc_2MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME_HEVC; i++) {
            VideoInfoUtil.calcDurationByFpsWithFFmpeg(getPath("10m/sample_10mb.hevc"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME_HEVC;
        log.info("hevc 2MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(2000);
    }

    @Test
    public void performance_test_for_mov_6MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.mov"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mov 6MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(9);
    }

    @Test
    public void performance_test_for_mp4_10MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.mp4"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mp4 10MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(11);
    }

    @Test
    public void performance_test_for_mpeg_6MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.mpeg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpeg 6MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_mpg_6MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.mpg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpg 6MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_wmv_4MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("10m/sample_10mb.wmv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("wmv 4MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    // performance tests for 30MB MP4
    @Test
    public void performance_test_for_3g2_17MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.3g2"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3g2 17MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_3gp_17MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.3gp"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("3gp 17MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_asf_9MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.asf"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("asf 9MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(3);
    }

    @Test
    public void performance_test_for_avi_9MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.avi"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("avi 9MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_dv_660MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.dv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("dv 660MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_flv_11MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.flv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("flv 11MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }

    @Test
    public void performance_test_for_hevc_4MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME_HEVC; i++) {
            VideoInfoUtil.calcDurationByFpsWithFFmpeg(getPath("30m/sample_30mb.hevc"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME_HEVC;
        log.info("hevc 4MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5300);
    }

    @Test
    public void performance_test_for_mov_17MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.mov"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mov 17MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(9);
    }

    @Test
    public void performance_test_for_mp4_32MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.mp4"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mp4 30MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(11);
    }

    @Test
    public void performance_test_for_mpeg_16MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.mpeg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpeg 16MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_mpg_16MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.mpg"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("mpg 16MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(5);
    }

    @Test
    public void performance_test_for_wmv_9MB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_TIME; i++) {
            VideoInfoUtil.getDurationWithFFmpeg(getPath("30m/sample_30mb.wmv"));
        }
        long end = System.currentTimeMillis();
        double avgPerf = (end - start) / REPEAT_TIME;
        log.info("wmv 9MB avg time = " + avgPerf + "ms");
        assertThat(avgPerf).isLessThan(4);
    }


    // convert video test
    @Test
    public void convert_video_demo() throws Exception {
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "3g2", AV_CODEC_ID_MPEG4, AV_CODEC_ID_AAC, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "3gp", AV_CODEC_ID_MPEG4, AV_CODEC_ID_AAC, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "asf", AV_CODEC_ID_MSMPEG4V3, AV_CODEC_ID_AAC, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "avi", AV_CODEC_ID_H264, -1, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "dv", AV_CODEC_ID_DVVIDEO, AV_CODEC_ID_PCM_S16LE, 720, 576);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "flv", AV_CODEC_ID_FLV1, AV_CODEC_ID_AAC, 640, 480);
        //VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "hevc", AV_CODEC_ID_HEVC,  640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "mov", AV_CODEC_ID_H264, AV_CODEC_ID_AAC, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "mpeg", AV_CODEC_ID_MPEG2VIDEO, AV_CODEC_ID_MP2, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "mpg", AV_CODEC_ID_MPEG2VIDEO, AV_CODEC_ID_AAC, 640, 480);
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "wmv", AV_CODEC_ID_WMV2, AV_CODEC_ID_AAC, 640, 480);
    }

    @Test
    public void hevcConverter() throws Exception {
        VideoInfoUtil.convertVideo(getPath("1m/SampleVideo_720x480_1mb.mp4"), "hevc", AV_CODEC_ID_HEVC,  640, 480);
    }

    private String getPath(String videoName){
        File file = new File(classLoader.getResource("video/" + videoName).getFile());
        return file.getAbsolutePath();
    }
}
