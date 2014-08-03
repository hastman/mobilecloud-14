package org.magnum.dataup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Video not Exists in repository")
public class VideoServerErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7459193595550422907L;

}
