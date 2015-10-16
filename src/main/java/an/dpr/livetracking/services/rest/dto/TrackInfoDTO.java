package an.dpr.livetracking.services.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for REST services
 * @author andprsoft
 *
 */
public class TrackInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public BigDecimal latitude;
    public BigDecimal longitude;
    public Long timestamp;
    public Long participantId;
    public String referenceSystem;

}
