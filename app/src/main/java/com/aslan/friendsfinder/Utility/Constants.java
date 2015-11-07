package com.aslan.friendsfinder.utility;

/**
 * Created by Vishnuvathsasarma on 04-Nov-15.
 */
public class Constants {
    public static final String NEARBY_FRIENDS = "nearby friends";

    private Constants() {
    }

    public static final class MessagePassingCommands {
        //constants for message passing and identifying
        public static final int START_LOCATION_TRACKING = 0;
        public static final int STOP_LOCATION_TRACKING = 1;
        public static final int GET_ALL_CONTACTS = 2;
        public static final int EXPORT_LOCATION_DATA_TO_SD_CARD = 3;
        public static final int NEARBY_FRIENDS_RECEIVED = 4;
    }
}
