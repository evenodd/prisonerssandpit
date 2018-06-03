package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Converter used by the app database to process java.sql.Date objects
 */

class Converters {
    /**
     * Converts Long timestamps into Date objects
     * @param value the timestamp to covert
     * @return converted Date object
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Converts Date objects into Long timestamps
     * @param date the date object to convert
     * @return the equivalent timestamp value
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}