/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package articlesectionclassifier.beans.datastore;

import articlesectionclassifier.beans.TokenData;
import articlesectionclassifier.util.SectionData.SectionType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jameseales
 */
public class ClassifierDataStoreReader {

    /**
     *
     * @param inputStream
     * @param dataStore
     * @throws Exception
     */
    public static void readDataStore(InputStream inputStream, ClassifierDataStore dataStore) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            boolean classDataDone = false;
            try {
                while ((line = br.readLine()) != null) {
                    if (classDataDone) {
                        //process token data
                        String[] bits = line.split("\t");
                        if (bits.length != 6) {
                            throw new Exception("datastore dump file is invalid: " + line);
                        }
                        if (line.equals("token\tintroduction\tmethods\tresults\tdiscussion\tcofv")) {
                            continue;
                        }
                        String token = bits[0];
                        double introV = Double.parseDouble(bits[1]);
                        double methV = Double.parseDouble(bits[2]);
                        double resV = Double.parseDouble(bits[3]);
                        double discV = Double.parseDouble(bits[4]);
                        double cofv = Double.parseDouble(bits[5]);


                        TokenData b = new TokenData(token, cofv, introV, methV, resV, discV);
                        dataStore.getTokenData().put(token, b);


                    } else {
                        //process class data
                        String[] bits = line.split("\t");
                        if (bits.length != 2) {
                            if (line.equals("class_data")) {
                                continue;
                            } else if (line.equals("token_data")) {
                                classDataDone = true;
                                continue;
                            } else {
                                throw new Exception("datastore dump file is invalid: " + line);
                            }

                        }

                        String cl = bits[0];
                        double v = Double.parseDouble(bits[1]);
                        if (cl.equals(SectionType.INTRODUCTION.toString())) {
                            dataStore.setIntroductionClassProb(v);
                        } else if (cl.equals(SectionType.METHODS.toString())) {
                            dataStore.setMethodsClassProb(v);
                        } else if (cl.equals(SectionType.RESULTS.toString())) {
                            dataStore.setResultsClassProb(v);
                        } else if (cl.equals(SectionType.DISCUSSION.toString())) {
                            dataStore.setDiscussionClassProb(v);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassifierDataStoreReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
