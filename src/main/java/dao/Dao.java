package dao;

import java.util.List;

import domain.AlivePeople;
import domain.DiedPeople;

public interface Dao {
    public List<AlivePeople> findAllAlivePeople();
    public List<DiedPeople> findAllDiedPeople();
    public AlivePeople findAlivePeopleById(int id);
    public boolean insertAlivePeople(List<AlivePeople> peoples);
    public boolean insertDiedPeople(List<DiedPeople> peoples);
    public int updateAlivePeople(AlivePeople ap);
    public int deleteAlivePeople(int id, String table);
}
