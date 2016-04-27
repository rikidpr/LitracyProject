package an.dpr.livetracking.services.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonDTOList {

    private List<PersonDTO> list;

    public List<PersonDTO> getList() {
	if (list == null)
	    list = new ArrayList<PersonDTO>();
	return list;
    }

    public void add(PersonDTO dto) {
	getList().add(dto);
    }
}
