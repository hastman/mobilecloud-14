package org.magnum.dataup.service;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.model.VideoStatus.VideoState;
import org.magnum.dataup.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
/**
 * Implementation class from {@link VideoService}
 * @author Angel Lopez Gomez
 * */
public class VideoServiceImpl implements VideoService {

	@Autowired
	@Qualifier("inMemory")
	private VideoRepository videoRepository;

	private static AtomicLong idGenerator = new AtomicLong(0L);

	@Override
	public Video addVideo(Video video) {
		video.setId(idGenerator.incrementAndGet());
		videoRepository.saveOrUpdate(video);
		video.setDataUrl(obtainURLForVideo(video));
		return video;
	}

	@Override
	public List<Video> allVideos() {
		return videoRepository.getAllVideos();
	}

	@Override
	public Video videoById(Long idVideo) {
		return videoRepository.findVideoById(idVideo);
	}

	@Override
	public VideoStatus addContentToViedo(Video video, InputStream videoContent) {
		videoRepository.saveOrUpdate(video);
		videoRepository.saveVideoContent(video.getId(), videoContent);
		return new VideoStatus(VideoState.READY);
	}

	@Override
	public InputStream getVideoContent(Long idVideo) {
		return videoRepository.findContentVideoById(idVideo);
	}

	/**
	 * This method generates de url to obtain the data from one video
	 * 
	 * @param video
	 * @return URL formatted
	 * */
	private String obtainURLForVideo(final Video video) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String base = "http://"
				+ request.getServerName()
				+ ((request.getServerPort() != 80) ? ":"
						+ request.getServerPort() : "");
		return String.format(FORMAT_URL_VIDEO, base, video.getId());
	}

}
