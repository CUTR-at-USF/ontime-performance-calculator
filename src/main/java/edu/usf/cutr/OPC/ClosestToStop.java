/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC;

public class ClosestToStop {
    private final String dbTable;
    
    public ClosestToStop(String dbTable) {
        this.dbTable = dbTable;
    }

    /* forms an update statement to update closest_to_stop field
      we can merge two select statements into one once we try to bring all the records, as we don't need to do 'ORDER BY [oid]'.
      By grouping trip_id, closest_stop_id and, date we calculate MIN(distance_to_stop) and update
      closest_to_stop on condition which recognizes unique record using trip_id, closest_stop_id, date and, min_dist*/
    public final String updateClosestToStopField() {
        String innerQuery =   " (SELECT MIN([distance_to_stop]) AS [min_dist],"+
                                        " [trip_id],"+
                                        " [closest_stop_id],"+
                                        " result.[date] AS [date] \n" +
                              " FROM \n" +
                                      "(SELECT TOP(10000)  [oid],"+ //tested on top 10000 rows; need to change to * to reteive all elements
                                           " [distance_to_stop],"+
                                           " [trip_id],"+
                                           " [closest_stop_id],"+
                                           " CAST([timestamp] AS DATE) AS [date] \n" +
                                      " FROM "+dbTable+" \n" +
                                      " WHERE [timestamp] >= ?"+
                                          " AND [timestamp]<= ? \n" +
                                      " ORDER BY [oid] DESC) AS result \n" +
                              " GROUP BY [trip_id]," +
                                  " [closest_stop_id]," +
                                  " result.[date]) AS groupbyResult \n";
        
        String whereCondition = dbTable+".[trip_id] = groupbyResult.[trip_id] \n" +
                                    " AND "+dbTable+".[closest_stop_id] = groupbyResult.[closest_stop_id] \n" +
                                    " AND CAST("+dbTable+".[timestamp] AS DATE) = groupbyResult.[date] \n";
        
        String whenCondition = dbTable+".[distance_to_stop] = groupbyResult.[min_dist]";
        
        String sqlQuery =   " UPDATE " +dbTable+"\n"+
                            " SET [closest_to_stop] = \n" + //closest_to_stop = 1 for the minimum of all distance_to_stop and 0 for others
                            " (CASE WHEN "+whenCondition+" THEN 1 ELSE 0 END)"+
                            " FROM \n" +innerQuery+
                            " WHERE "+whereCondition;
        
        return sqlQuery;                 
    }
}
