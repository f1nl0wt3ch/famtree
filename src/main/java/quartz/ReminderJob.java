package quartz;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.Dao;
import dao.DaoImpl;
import domain.AlivePeople;
import service.EmailUtil;

public class ReminderJob implements Job {
	EmailUtil util = new EmailUtil();
	Dao dao = new DaoImpl();
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<AlivePeople> alives = dao.findAllAlivePeople();
		for(AlivePeople alive : alives) {
			String content=null;
			String subject=null;
			String senderName="";
			String sender=null;
			util.sendHTMLEmail(content, subject, alive.getEmail(), senderName, sender);
		}
	}

}
