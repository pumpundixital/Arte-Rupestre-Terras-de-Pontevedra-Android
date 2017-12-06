package org.terrasdepontevedra.petra.data.model.place;


import com.google.gson.annotations.SerializedName;

import org.terrasdepontevedra.petra.util.extensions.StringExtensions;

public class ContentDto {
    @SerializedName("rendered")
    private final String rendered;

    @SerializedName("protected")
    private final boolean aProtected;

    public ContentDto(String rendered, boolean aProtected) {
        this.rendered = rendered;
        this.aProtected = aProtected;
    }

    public String getRendered() {
        return StringExtensions.replace(rendered, "\r\n", "\n");
    }

    public boolean isProtected() {
        return aProtected;
    }
}