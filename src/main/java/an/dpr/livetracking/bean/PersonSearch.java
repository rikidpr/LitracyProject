package an.dpr.livetracking.bean;

public class PersonSearch {

    private String document;

    public PersonSearch() {}
    
    public PersonSearch(String document) {
	setDocument(document);
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
