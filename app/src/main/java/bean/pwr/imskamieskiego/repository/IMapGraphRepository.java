/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package bean.pwr.imskamieskiego.repository;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

import bean.pwr.imskamieskiego.model.map.Edge;
import bean.pwr.imskamieskiego.model.map.MapPoint;

/**
 * This interface represents repository of data from graph.
 */
public interface IMapGraphRepository {

    /**
     * Returns the point on the map represented by the given ID.
     * @param id id of point
     * @return MapPoint object. Can be null, when object for passed ID does not exist.
     */
    MapPoint getPointByID(int id);

    /**
     * Returns the list of point on the map represented by the given ID list.
     * @param id list of point's ID
     * @return List of MapPoint. Can be empty, when IDs don't match to any point.
     */
    List<MapPoint> getPointByID(@NonNull List<Integer> id) throws NullPointerException;

    /**
     * Returns all edges, which start from point with given ID
     * @param pointID ID of point
     * @return List of edges. If point with given ID hasn't any edge, return empty list.
     */
    List<Edge> getOutgoingEdges(int pointID);


    /**
     * Returns a list of all edges for all points in the list. If the point with the given ID
     * does not exist, it will not be added to the result map. If all the points in the list
     * do not exist, the map will be empty.
     * @param pointID list of point IDs
     * @return Map where key is point ID and value is list of edges for this ID.
     */
    Map<Integer, List<Edge>> getOutgoingEdges(@NonNull List<Integer> pointID) throws NullPointerException;

}
