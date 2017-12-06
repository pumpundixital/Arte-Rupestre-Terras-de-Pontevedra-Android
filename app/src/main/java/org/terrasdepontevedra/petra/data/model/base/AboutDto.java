package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;


class AboutDto {
    @SerializedName("href")
    private final String href;

    public AboutDto(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
