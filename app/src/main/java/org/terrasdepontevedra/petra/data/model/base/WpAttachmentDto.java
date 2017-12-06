package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;

class WpAttachmentDto {
    @SerializedName("href")
    private final String href;

    public WpAttachmentDto(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
