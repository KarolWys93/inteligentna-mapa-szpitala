package bean.pwr.imskamieskiego.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import bean.pwr.imskamieskiego.data.LocalDB;
import bean.pwr.imskamieskiego.data.map.dao.EdgeDao;
import bean.pwr.imskamieskiego.data.map.entity.EdgeEntity;
import bean.pwr.imskamieskiego.model.map.Edge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

public class MapGraphRepositoryTest {

    private MapGraphRepository graphRepository;
    @Mock private LocalDB localDB;
    @Mock private EdgeDao edgeDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMapOfListsOfEdgesForIDsList() {
        List<EdgeEntity> edgesFrom1 = Arrays.asList(
                new EdgeEntity(1, 1, 2, 1),
                new EdgeEntity(2, 1, 3, 1));

        List<EdgeEntity> edgesFrom2 = Arrays.asList(
                new EdgeEntity(3, 2, 1, 1),
                new EdgeEntity(4, 2, 3, 1));

        List<EdgeEntity> edges = new ArrayList<>();
        edges.addAll(edgesFrom1);
        edges.addAll(edgesFrom2);

        Map<Integer, List<Edge>> expected = new Hashtable<>();
        expected.put(1, new ArrayList<>(edgesFrom1));
        expected.put(2, new ArrayList<>(edgesFrom2));

        when(edgeDao.getOutgoingEdges(anyList())).thenReturn(edges);
        when(localDB.getEdgeDao()).thenReturn(edgeDao);


        graphRepository = new MapGraphRepository(localDB);
        Map<Integer, List<Edge>> result = graphRepository.getOutgoingEdges(anyList());

        assertEquals(expected, result);
    }

    @Test
    public void getEmptyMapWhenAllPointsDoNotExist() {
        when(edgeDao.getOutgoingEdges(anyList())).thenReturn(new ArrayList<>());
        when(localDB.getEdgeDao()).thenReturn(edgeDao);

        graphRepository = new MapGraphRepository(localDB);
        Map<Integer, List<Edge>> result = graphRepository.getOutgoingEdges(anyList());
        assertTrue(result.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void whenListOfIDsIsNull() {

        when(localDB.getEdgeDao()).thenReturn(edgeDao);

        graphRepository = new MapGraphRepository(localDB);
        graphRepository.getOutgoingEdges(null);

    }
}
