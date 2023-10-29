package com.nikoarap.gametime.utils

class Constants {
    companion object {
        const val API_ENDPOINT = "https://618d3aa7fe09aa001744060a.mockapi.io/api/sports/"
        const val EMPTY_STRING = ""
        const val REALM_ID = "gameTime.realm.dev"
        const val REALM_SCHEMA_VER = 0L
        const val BAD_REQUEST = 400
        const val VALUE_ZERO = 0
        const val VALUE_ONE = 1
        const val VALUE_ZERO_LONG = 0L
        const val FLOAT_DEGREES_0 = 0f
        const val FLOAT_DEGREES_180 = 180f
        const val ICON = "icon"
        const val IMAGE = "image"
        const val SECTION_COLUMN_WEIGHT = .8f
        const val VS_VALUE = "VS"
        const val SCORE_REGEX = "-"
        const val SPACER_SCORE_SPACER_REGEX = " - "
        const val TIMER_SDF_FORMAT = "HH:mm:ss"
        const val MINUTES_IN_HOUR = 60
        const val SECONDS_IN_MINUTE = 60
        const val MILLIS_IN_SECOND = 1000
        const val SECONDS_IN_HOUR = 3600
        const val ONE_SECOND_DELAY = 1000L
        const val SECONDS_IN_DAY = 86400
        const val EVENT_STARTED = "EVENT STARTED"
        const val MAX_EVENTS_PER_ROW = 4
        const val EVENT_ITEM_LAYOUT_WEIGHT = 1f
        const val NO_FAVOURITES_YET = "You haven't added any favorite sports yet. Click on the star located in the sport header to add your favorite sports."
        const val NO_EVENTS_PLANNED = "There are no sport events for this category yet"

        //sport types, used for enums
        const val SOCCER = "SOCCER"
        const val BASKETBALL = "BASKETBALL"
        const val TENNIS = "TENNIS"
        const val TABLE_TENNIS = "TABLE TENNIS"
        const val ESPORTS = "ESPORTS"
        const val BASEBALL = "BASEBALL"
        const val HANDBALL = "HANDBALL"
        const val BEACH_VOLLEYBALL = "BEACH VOLLEYBALL"
        const val DARTS = "DARTS"

        //nav bar items, used for enums
        const val HOME = "Home"
        const val FAVORITES = "Favorites"

    }
}