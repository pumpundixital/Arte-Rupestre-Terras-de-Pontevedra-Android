package org.terrasdepontevedra.petra.data.model.place;

import com.google.gson.annotations.SerializedName;

public class TitleDto {
    @SerializedName("rendered")
    private final String rendered;

    public TitleDto(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }
}
