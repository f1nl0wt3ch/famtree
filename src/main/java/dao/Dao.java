package dao;

import java.util.List;

import domain.AlivePeople;

public interface Dao {
    public List<AlivePeople> findAllAlivePeople(String table);
    public int insertAlivePeople(List<AlivePeople> peoples, String table);
    public int updateAlivePeople(AlivePeople ap);
    public int deleteAlivePeople(AlivePeople ap);
}
