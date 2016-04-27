package an.dpr.livetracking.services.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDTOList {

    private List<ParticipantDTO> list;

    public List<ParticipantDTO> getList() {
	if (list == null)
	    list = new ArrayList<ParticipantDTO>();
	return list;
    }

    public void add(ParticipantDTO dto) {
	getList().add(dto);
    }
}
