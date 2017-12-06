package org.terrasdepontevedra.petra.data.model.center;

import com.google.gson.annotations.SerializedName;

class ExcerptDto {
    @SerializedName("rendered")
    private final String rendered;

    @SerializedName("protected")
    private final boolean aProtected;

    public ExcerptDto(String rendered, boolean aProtected) {
        this.rendered = rendered;
        this.aProtected = aProtected;
    }

    String getRendered() {
        return rendered;
    }

    public boolean isProtected() {
        return aProtected;
    }
}
