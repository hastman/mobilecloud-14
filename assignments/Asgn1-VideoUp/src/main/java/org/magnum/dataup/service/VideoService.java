package org.magnum.dataup.service;

import java.io.InputStream;
import java.util.List;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;

/**
 * Interface for method definition for video services
 * 
 * @author Angel Lopez Gomez
 * */
public interface VideoService {

	static final String FORMAT_URL_VIDEO = "%s/video/%s/data";

	/**
	 * This method add new {@link Video} to repository
	 * 
	 * @param video
	 * @return {@link Video}
	 * */
	Video addVideo(final Video video);

	/**
	 * This method returns all videos in repository
	 * 
	 * @return {@link List<Video>}
	 * */
	List<Video> allVideos();

	/**
	 * This method return a {@link Video} from an id
	 * 
	 * @param idVideo
	 * @return {@link Video}
	 * */
	Video videoById(final Long idVideo);

	/**
	 * This method add content to the {@link Video} adding the content type and
	 * the binary data to the repository
	 * 
	 * @param video
	 * @param videoContent
	 * @return {@link VideoStatus}
	 * */
	VideoStatus addContentToViedo(final Video video,
			final InputStream videoContent);

	/**
	 * This method allows access to the video content from an id
	 * 
	 * @param idVideo
	 * @return {@link InputStream}
	 * */
	InputStream getVideoContent(final Long idVideo);

}
