package com.uleos.cryptotools.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Logged
@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (LOG.isInfoEnabled()) {
			LOG.info("[{}] {}", requestContext.getMethod(), requestContext.getUriInfo().getRequestUri());
			String entityBody = getEntityBody(requestContext);
			if (entityBody != null && !entityBody.isEmpty()) {
				LOG.info(getEntityBody(requestContext));
			}
		}
	}

	private static String getEntityBody(ContainerRequestContext requestContext) throws IOException {
		InputStream entityStream = requestContext.getEntityStream();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = entityStream.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();
		StringBuilder ret = new StringBuilder();
		ret.append(baos);
		requestContext.setEntityStream(new ByteArrayInputStream(baos.toByteArray()));
		baos.flush();
		baos.close();
		return ret.toString();
	}
}
