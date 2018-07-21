package bean.pwr.imskamieskiego.data.map;

import javax.annotation.Nullable;

public interface Location {

    /**
     * ID of the place on the map.
     * @return ID
     */
    int getID();

    /**
     * The short name of the place on the map. For example "Room 404".
     * @return location name
     */
    String getName();

    /**
     * The description of the place. This can be Null.
     * @return location description.
     */
    @Nullable
    String getDescription();
}