package org.terrasdepontevedra.petra.util.extensions;

import java.util.List;

public class CollectionExtension {
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
