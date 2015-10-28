package be.gling.businessApp.model.util;

import android.content.Context;
import android.graphics.Bitmap;

import be.gling.businessApp.model.dto.AccountDTO;
import be.gling.businessApp.model.dto.BusinessDTO;
import be.gling.businessApp.model.dto.LoginSuccessDTO;
import be.gling.businessApp.model.service.AccountService;
import be.gling.businessApp.model.util.externalRequest.ImageLoader;
import be.gling.businessApp.view.util.ImageTreatment;

import java.util.Date;

/**
 * Created by florian on 11/11/14.
 * Store data
 */
public class Storage {

    private final static long MAX_DELAY = 30 * 60 * 1000;

    private static Date lastLoading;
    private static AccountDTO account;
    private static BusinessDTO business;
    private static String authenticationKey;


    public static void store(Context context, LoginSuccessDTO connection) {

        business = connection.getBusiness();
        account = connection.getAccount();

        authenticationKey = connection.getAuthenticationKey();

        AccountService.storeService(context, connection);
        lastLoading = new Date();
    }

    public static boolean isConnected() {
        return account != null;
    }

    public static void clean(Context context) {
        account = null;
        business = null;
        authenticationKey = null;
        AccountService.clearKey(context);
    }

    public static boolean testStorage() {
        if (account == null) {
            return false;
        }
        return true;
    }


    /*
     * authentication key
     */
    public static String getAuthenticationKey() {
        return authenticationKey;
    }


    public static void setAuthenticationKey(String authenticationKey) {
        Storage.authenticationKey = authenticationKey;
    }


    public static AccountDTO getAccount() {
        return account;
    }

    public static BusinessDTO getBusiness() {
        return business;
    }

}

