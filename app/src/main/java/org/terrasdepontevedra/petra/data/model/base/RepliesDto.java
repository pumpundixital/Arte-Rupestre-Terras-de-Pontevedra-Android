package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;


class RepliesDto {
    @SerializedName("embeddable")
    private final boolean embeddable;

    @SerializedName("href")
    private final String href;

    public RepliesDto(boolean embeddable, String href) {
        this.embeddable = embeddable;
        this.href = href;
    }

    public boolean isEmbeddable() {
        return embeddable;
    }

    public String getHref() {
        return href;
    }
}
