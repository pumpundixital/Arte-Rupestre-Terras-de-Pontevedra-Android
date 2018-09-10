package org.terrasdepontevedra.petra.domain.model.walk;


public class Relation {

    private int order;
    private int categoryPlaceId;
    private int categoryItineraryId;


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCategoryPlaceId() {
        return categoryPlaceId;
    }

    public void setCategoryPlaceId(int categoryPlaceId) {
        this.categoryPlaceId = categoryPlaceId;
    }

    public int getCategoryItineraryId() {
        return categoryItineraryId;
    }

    public void setCategoryItineraryId(int categoryItineraryId) {
        this.categoryItineraryId = categoryItineraryId;
    }


    public static Relation mapper(RelationDto relationDto){
        Relation relation = new Relation();
        if(relationDto.get_wpcf_belongs_itinerario_id().get(0)!=null) {
            relation.setCategoryItineraryId((Integer.parseInt(relationDto.get_wpcf_belongs_itinerario_id().get(0))));
        }
        if(relationDto.get_wpcf_belongs_lugar_id().get(0)!=null) {
            relation.setCategoryPlaceId((Integer.parseInt(relationDto.get_wpcf_belongs_lugar_id().get(0))));
        }
        relation.setOrder(Integer.parseInt(relationDto.getWpcforden().get(0)));
        return relation;
    }
}
