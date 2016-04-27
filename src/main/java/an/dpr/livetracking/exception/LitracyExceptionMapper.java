package an.dpr.livetracking.exception;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
/**
 * Esta clase se encarga de mapear las excepciones que lanza el rest (en este caso las LitracyException)
 * y adaptar la respuesta segun el tipo de pete
 * @author saez
 *
 */
@Provider
public class LitracyExceptionMapper implements ExceptionMapper<LitracyException>{

    @Inject private Logger log;
    
    @Override
    public Response toResponse(LitracyException exception)
    {
	log.debug("inicio "+exception.getMessage());
	return Response
		.status(Response.Status.BAD_REQUEST)
		.entity(exception.getMessage())
		.type( MediaType.TEXT_PLAIN)
		.build();
    }
}