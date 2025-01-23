package org.jfm.controller.rest.filters;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorDetails;

import jakarta.ws.rs.core.Response;

public class ExceptionMapper {
    @ServerExceptionMapper
    public Response mapException(EntityNotFoundException t) {
        return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }
}
