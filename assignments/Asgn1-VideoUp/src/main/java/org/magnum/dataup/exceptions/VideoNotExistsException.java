package org.magnum.dataup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Video not Exists in repository")
public class VideoNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5892669742064205977L;

}
