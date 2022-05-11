package com.tejko.constants;

import com.tejko.models.general.enums.Theme;

public class TejkoConstants {

    public static Integer APP_YAMB_ID = 1;
    public static String APP_YAMB_NAME = "Yamb";
    public static String APP_YAMB_DESCRIPTION = "";

    public static Integer ROLE_ADMIN_ID = 1;
    public static String ROLE_ADMIN_LABEL = "ADMIN";
    public static String ROLE_ADMIN_DESCRIPTION = "";

    public static Integer ROLE_USER_ID = 2;
    public static String ROLE_USER_LABEL = "USER";
    public static String ROLE_USER_DESCRIPTION = "";

    public static Integer ROLE_MODERATOR_ID = 3;
    public static String ROLE_MODERATOR_LABEL = "MODERATOR";
    public static String ROLE_MODERATOR_DESCRIPTION = "";

    public static final int USERNAME_LENGTH_MIN = 3;
    public static final int USERNAME_LENGTH_MAX = 15;
    public static final int PASSWORD_LENGTH_MIN = 8;

    public static final String ORIGIN_DEFAULT = "https://tejko.apps";
    public static final String ORIGIN_WWW = "https://www.tejko.apps";
    public static final String ORIGIN_HEROKU = "https://tejko-apps-client.herokuapp.com";
    public static final String ORIGIN_LOCALHOST = "http://localhost:3000";

    public static final int EXCEPTION_LOG_SIZE = 1024;
    public static final int RECURSION_LIMIT = 5;

    public static final int DEFAULT_VOLUME = 1;
    public static final int VOLUME_MIN = 0;
    public static final int VOLUME_MAX = 3;

    public static final Theme DEFAULT_THEME = Theme.LIGHT;
}
