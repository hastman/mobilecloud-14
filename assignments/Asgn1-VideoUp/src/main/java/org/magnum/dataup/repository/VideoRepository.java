package org.magnum.dataup.repository;

import java.io.InputStream;
import java.util.List;

import org.magnum.dataup.model.Video;

/**
 * Interface for control of repository
 * 
 * @author Angel Lopez Gomez
 * */
public interface VideoRepository {

	/**
	 * This method allows to save a new {@link Video} or update an old
	 * {@link Video}
	 * 
	 * @param video
	 * **/
	void saveOrUpdate(final Video video);

	/**
	 * This method returns all videos in the repository
	 * 
	 * @return {@link List<Video>}
	 * */
	List<Video> getAllVideos();

	/**
	 * Find a video in repository from an id
	 * 
	 * @param idVideo
	 * @return {@link Video}
	 * */
	Video findVideoById(final Long idVideo);

	/**
	 * This method save the content of a one video
	 * 
	 * @param idVideo
	 * @param videoContent
	 * 
	 * */
	void saveVideoContent(final Long idVideo, InputStream videoContent);

	/**
	 * This method returns de content of a video from the repository
	 * 
	 * @return {@link InputStream}
	 * **/
	InputStream findContentVideoById(final Long idVideo);

}
