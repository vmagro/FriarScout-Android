package io.vinnie.friarscout;

/**
 * Created by vmagro on 3/3/15.
 */
public class Util {

    public static String emailToKey(String email) {
        return email.replace('.', ',');
    }

    public static String emailFromKey(String key) {
        return key.replace(',', '.');
    }

}
