package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;


class CuriesDto {
    @SerializedName("name")
    private final String name;

    @SerializedName("href")
    private final String href;

    @SerializedName("templated")
    private final boolean templated;

    public CuriesDto(String name, String href, boolean templated) {
        this.name = name;
        this.href = href;
        this.templated = templated;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public boolean isTemplated() {
        return templated;
    }
}
