package bean.pwr.imskamieskiego.model.map;

public interface Edge {

    /**
     * ID of start point of edge
     * @return
     */
    int getFrom();

    /**
     * ID of end point of edge
     * @return
     */
    int getTo();

    /**
     * Length of edge.
     * @return
     */
    int getLength();

}
