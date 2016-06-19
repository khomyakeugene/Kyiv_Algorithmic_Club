package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано повний шлях до файла в Unix системі.
Наприклад /home/../var/./lib//file.txt
Необхідно повернути спрощений варіант. (/var/lib/file.txt)
 */
import java.util.Arrays;

public class UnixPath {
    private final static char CATALOG_DELIMITER = '/';
    private final static char[] CURRENT_CATALOG_MASK = {'.'};
    private final static char[] PARENT_CATALOG_MASK = {'.', '.'};

    public String simplify(String input) {
        char[] source = (input + (input.charAt(input.length()-1) != CATALOG_DELIMITER ?
                Character.toString(CATALOG_DELIMITER) : "")).toCharArray();
        char[] result = new char[source.length];

        int j = 0;
        if(source.length > 0) {
            result[0] = source[0];
            j = 1;
            int startCurrentSubCatalog = (source[0] == CATALOG_DELIMITER) ? 1 :0;
            for (int i = 1; i < source.length; i++) {
                if (source[i] == CATALOG_DELIMITER) {
                    // Simplify double delimiters
                    if (result[j-1] == CATALOG_DELIMITER) continue;
                    // Current sub-catalog name
                    char[] subCatalogName = Arrays.copyOfRange(result, startCurrentSubCatalog, j);
                    // "Current" catalog?
                    if (Arrays.equals(subCatalogName, CURRENT_CATALOG_MASK)) {
                        j = startCurrentSubCatalog;
                        continue;
                    } else {
                        // Parent catalog?
                        if (Arrays.equals(subCatalogName, PARENT_CATALOG_MASK)) {
                            // Find start position of previous sub-catalog
                            for(j = startCurrentSubCatalog-2; j >=0 && result[j] != CATALOG_DELIMITER; j--);
                            if ( j < 0) j = 0;

                        }
                    }
                    // New start position of current sub-catalog name
                    startCurrentSubCatalog = j+1;
                }
                result[j++] = source[i];
            }
            // According to the lecturer's point of view, it is always obligatory to "cut" "end-of-line" CATALOG_DELIMITER
            if (j > 1 && result[j-1] == CATALOG_DELIMITER) {
                j--;
            }
        }

        // It is obligatory to set concrete sting length!
        return new String(Arrays.copyOf(result, j));
    }
}