package org.terrasdepontevedra.petra.domain.model.walk;


public class Relation {

    private int order;
    private int categoryPlaceId;


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



    public static Relation mapper(RelationDto relationDto){
        Relation relation = new Relation();
        relation.setCategoryPlaceId(relationDto.getID());
        return relation;
    }
}
