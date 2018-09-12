package org.terrasdepontevedra.petra.domain.model.walk;

public class Image {

    private int id;
    private String urlMedium;

    public Image(int id, String urlMedium) {
        this.id = id;
        this.urlMedium = urlMedium;
    }

    public Image(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlMedium() {
        if(urlMedium==null){
            return "";
        }
        return urlMedium;
    }

    public void setUrlMedium(String urlMedium) {
        this.urlMedium = urlMedium;
    }






}
