package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;

class SelfDto {
    @SerializedName("href")
    private final String href;

    public SelfDto(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
