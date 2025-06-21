package ar.edu.utn.frbb.tup.persistence.dao;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBaseDao {
    protected static Map<String, Map<Integer, Object>> poorMansDatabase = new HashMap<>();
    protected abstract String getEntityName();

    protected Map<Integer, Object> getInMemoryDatabase() {
        if (poorMansDatabase.get(getEntityName()) == null) {
            poorMansDatabase.put(getEntityName(),new HashMap<>());
        }
        return poorMansDatabase.get(getEntityName());   
    } 
}
