package common.assets;

import common.assets.DbDetails;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class LocalDataStore implements Serializable {
    private static final long serialVersionUID = 5864896800675704551L;
    private List<DbDetails> list;
    private Map<String, String> map;

    public List<DbDetails> getList() {
        return list;
    }

    public void setList(List<DbDetails> list) {
        this.list = list;
    }

//    public Map<String, String> getMap() {
//        return map;
//    }
//
//    public void setMap(Map<String, String> map) {
//        this.map = map;
//    }
}

