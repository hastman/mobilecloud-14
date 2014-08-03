package org.magnum.dataup;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.magnum.dataup.exceptions.VideoNotExistsException;
import org.magnum.dataup.exceptions.VideoServerErrorException;
import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(VideoSvcApi.VIDEO_SVC_PATH)
public class VideoController {

	private static final String ID_VARIABLE = "id";
	private static final String VIDEO_ID_DATA_PATH = "/{id}/data";
	@Autowired
	private VideoService videoService;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Video addVideo(@RequestBody final Video video) {
		return videoService.addVideo(video);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<Video> getAllVideos() {
		return videoService.allVideos();
	}

	@RequestMapping(value = VIDEO_ID_DATA_PATH, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public VideoStatus addDataFile(@PathVariable(ID_VARIABLE) long id,
			MultipartHttpServletRequest file) {
		Video video = videoService.videoById(id);
		if (video == null) {
			throw new VideoNotExistsException();
		}
		video.setContentType(file.getContentType());
		MultipartFile fileContent = file.getFile("data");
		try {
			VideoStatus status = videoService.addContentToViedo(video,
					fileContent.getInputStream());
			return status;
		} catch (IOException e) {
			throw new VideoServerErrorException();
		}

	}

	@RequestMapping(value = VIDEO_ID_DATA_PATH, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public void getDataFile(@PathVariable(ID_VARIABLE) long id,
			HttpServletResponse response) {
		Video video = videoService.videoById(id);
		if (video == null) {
			throw new VideoNotExistsException();
		}
		response.setContentType(video.getContentType());
		response.setHeader("Content-Disposition", "attachment; filename="
				+ video.getTitle());

		try {
			FileCopyUtils.copy(videoService.getVideoContent(id),
					response.getOutputStream());
		} catch (IOException e) {
			throw new VideoServerErrorException();
		}

	}
}
