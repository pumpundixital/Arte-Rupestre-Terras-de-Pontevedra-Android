package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;


class WpTermDto {
    @SerializedName("taxonomy")
    private final String taxonomy;

    @SerializedName("embeddable")
    private final boolean embeddable;

    @SerializedName("href")
    private final String href;

    public WpTermDto(String taxonomy, boolean embeddable, String href) {
        this.taxonomy = taxonomy;
        this.embeddable = embeddable;
        this.href = href;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public boolean isEmbeddable() {
        return embeddable;
    }

    public String getHref() {
        return href;
    }
}
