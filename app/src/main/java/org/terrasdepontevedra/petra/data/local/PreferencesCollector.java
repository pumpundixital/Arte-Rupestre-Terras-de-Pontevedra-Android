package org.terrasdepontevedra.petra.data.local;

public interface PreferencesCollector {

    boolean isFirstTime();

    void setFirstTime(boolean firstTime);

    void setPetraActive(boolean petraActive);

    boolean isPetraActive();

    boolean isContentDownloaded();

    void setContentDownloaded(boolean contentDownloaded);
}
