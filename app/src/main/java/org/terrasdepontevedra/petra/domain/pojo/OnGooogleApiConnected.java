package org.terrasdepontevedra.petra.domain.pojo;

import com.google.android.gms.common.api.GoogleApiClient;


public class OnGooogleApiConnected {

    public final GoogleApiClient googleApiClient;

    public OnGooogleApiConnected(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }
}
