package service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.AlivePeople;
import domain.DiedPeople;

public interface CommonService {
	public String getValueByKey(String key);
    public List<AlivePeople> findAllAlivePeople();
    public List<DiedPeople> findAllDiedPeople();
    public HashMap<String, String> findAllParamter(String lang);
    public Timestamp convertStringToTimestamp(String dateStr);
    public String handleRequestFromClient(HttpServletRequest request) throws IOException;
}
