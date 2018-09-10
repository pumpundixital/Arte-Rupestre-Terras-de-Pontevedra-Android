package org.terrasdepontevedra.petra.util;

public class DeepLinkUtils {

    public static String getSlugFromLink(String link){
        String[] split = link.split("/");
        return split[split.length-1];
    }

}
