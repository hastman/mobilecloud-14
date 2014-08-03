package org.magnum.dataup.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Repository;

@Repository("inMemory")
/**
 * Implementation class of {@link VideoRepository} this class store in memory the data
 * @author Angel Lopez Gomez
 * */
public class InMemoryRepository implements VideoRepository {

	Map<Long, Video> tableVideoInMemory;
	Map<Long, InputStream> tableVideoContentInMemory;

	@PostConstruct
	public void init() {
		tableVideoInMemory = new HashMap<>();
		tableVideoContentInMemory = new HashMap<>();

	}

	@Override
	public void saveOrUpdate(Video video) {
		tableVideoInMemory.put(video.getId(), video);
	}

	@Override
	public List<Video> getAllVideos() {
		return new ArrayList<>(tableVideoInMemory.values());
	}

	@Override
	public Video findVideoById(Long idVideo) {

		return tableVideoInMemory.get(idVideo);
	}

	@Override
	public void saveVideoContent(Long idVideo, InputStream videoContent) {
		tableVideoContentInMemory.put(idVideo, videoContent);
	}

	@Override
	public InputStream findContentVideoById(Long idVideo) {
		return tableVideoContentInMemory.get(idVideo);
	}

}
