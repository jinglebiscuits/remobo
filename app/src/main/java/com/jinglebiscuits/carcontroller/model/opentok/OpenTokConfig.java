package com.jinglebiscuits.carcontroller.model.opentok;

import android.webkit.URLUtil;

public class OpenTokConfig {
    // *** Fill the following variables using your own Project info from the OpenTok dashboard  ***
    // ***                      https://dashboard.tokbox.com/projects                           ***

    public final String mSecret = "721ccdafb93a4856aed600e470d27ef5e889b666";

    // Replace with your OpenTok API key
    public static final String API_KEY = "46181182";
    // Replace with a generated Session ID
    public static final String SESSION_ID = "1_MX40NjE4MTE4Mn5-MTUzNzEzMzAwMDg2OH5CRENoNFVncnhSZXA5UElNdFlMVmJOYmp-UH4";
    // Replace with a generated token (from the dashboard or using an OpenTok server SDK)
    public static final String TOKEN =
      "T1==cGFydG5lcl9pZD00NjE4MTE4MiZzaWc9NTQxOTVlYjQxZDczNmQ1MDdkYTE2ZDQ0YjdkOWJlYjExZjgxMWU0ZDpzZXNzaW9uX2lkPTFfTVg0ME5qRTRNVEU0TW41LU1UVXpOekV6TXpBd01EZzJPSDVDUkVOb05GVm5jbmhTWlhBNVVFbE5kRmxNVm1KT1ltcC1VSDQmY3JlYXRlX3RpbWU9MTUzNzEzMzAzMiZub25jZT0wLjU1ODQ0NzM1NjE0NTQ4MjQmcm9sZT1wdWJsaXNoZXImZXhwaXJlX3RpbWU9MTUzOTcyNTAzOSZpbml0aWFsX2xheW91dF9jbGFzc19saXN0PQ==";
    public static final String PUBLISHER_TOKEN =
      "T1==cGFydG5lcl9pZD00NjE4MTE4MiZzaWc9NTQxOTVlYjQxZDczNmQ1MDdkYTE2ZDQ0YjdkOWJlYjExZjgxMWU0ZDpzZXNzaW9uX2lkPTFfTVg0ME5qRTRNVEU0TW41LU1UVXpOekV6TXpBd01EZzJPSDVDUkVOb05GVm5jbmhTWlhBNVVFbE5kRmxNVm1KT1ltcC1VSDQmY3JlYXRlX3RpbWU9MTUzNzEzMzAzMiZub25jZT0wLjU1ODQ0NzM1NjE0NTQ4MjQmcm9sZT1wdWJsaXNoZXImZXhwaXJlX3RpbWU9MTUzOTcyNTAzOSZpbml0aWFsX2xheW91dF9jbGFzc19saXN0PQ==";
    public static final String SUBSCRIBER_TOKEN =
      "T1==cGFydG5lcl9pZD00NjE4MTE4MiZzaWc9OTQxMzBlZGQ1MzBiNjQzNmUxYjdiMzBhYWEwM2I2MjkzMTUxNWRhODpzZXNzaW9uX2lkPTFfTVg0ME5qRTRNVEU0TW41LU1UVXpOekV6TXpBd01EZzJPSDVDUkVOb05GVm5jbmhTWlhBNVVFbE5kRmxNVm1KT1ltcC1VSDQmY3JlYXRlX3RpbWU9MTUzNzEzMzE1NSZub25jZT0wLjI2NTkyNDU1MTEzOTExNTUmcm9sZT1zdWJzY3JpYmVyJmV4cGlyZV90aW1lPTE1Mzk3MjUxNjEmaW5pdGlhbF9sYXlvdXRfY2xhc3NfbGlzdD0=";

    /*                           ***** OPTIONAL *****
     If you have set up a server to provide session information replace the null value
     in CHAT_SERVER_URL with it.

     For example: "https://yoursubdomain.com"
    */
    public static final String CHAT_SERVER_URL = null;
    public static final String SESSION_INFO_ENDPOINT = CHAT_SERVER_URL + "/session";

    // *** The code below is to validate this configuration file. You do not need to modify it  ***

    public static String webServerConfigErrorMessage;
    public static String hardCodedConfigErrorMessage;

    public static boolean areHardCodedConfigsValid() {
        if (OpenTokConfig.API_KEY != null && !OpenTokConfig.API_KEY.isEmpty()
          && OpenTokConfig.SESSION_ID != null && !OpenTokConfig.SESSION_ID.isEmpty()
          && OpenTokConfig.TOKEN != null && !OpenTokConfig.TOKEN.isEmpty()) {
            return true;
        } else {
            hardCodedConfigErrorMessage = "API KEY, SESSION ID and TOKEN in OpenTokConfig.java cannot be null or empty.";
            return false;
        }
    }

    public static boolean isWebServerConfigUrlValid() {
        if (OpenTokConfig.CHAT_SERVER_URL == null || OpenTokConfig.CHAT_SERVER_URL.isEmpty()) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must not be null or empty";
            return false;
        } else if (!(URLUtil.isHttpsUrl(OpenTokConfig.CHAT_SERVER_URL) || URLUtil.isHttpUrl(OpenTokConfig.CHAT_SERVER_URL))) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must be specified as either http or https";
            return false;
        } else if (!URLUtil.isValidUrl(OpenTokConfig.CHAT_SERVER_URL)) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java is not a valid URL";
            return false;
        } else {
            return true;
        }
    }
}
