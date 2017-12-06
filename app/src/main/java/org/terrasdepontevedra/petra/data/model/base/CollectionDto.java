package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;


class CollectionDto {
    @SerializedName("href")
    private final String href;

    public CollectionDto(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
