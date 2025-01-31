package org.jfm.controller.rest.filters;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorDetails;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.exceptions.SqsException;

import jakarta.ws.rs.core.Response;

public class ExceptionMapper {
    @ServerExceptionMapper
    public Response mapException(EntityNotFoundException t) {
        return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(ParamException t) {
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(ErrorSqlException t) {
        return Response.status(Response.Status.BAD_GATEWAY)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(SqsException t) {
        return Response.status(Response.Status.BAD_GATEWAY)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

}
