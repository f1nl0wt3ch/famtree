package domain;

public class Relation {
	private int id;
	private AlivePeople alive;
	private DiedPeople die;
	private String relation;
	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Relation(int id, AlivePeople alive, DiedPeople die) {
		super();
		this.id = id;
		this.alive = alive;
		this.die = die;
	}
	public Relation(int id, AlivePeople alive, DiedPeople die, String relation) {
		super();
		this.id = id;
		this.alive = alive;
		this.die = die;
		this.relation = relation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AlivePeople getAlive() {
		return alive;
	}
	public void setAlive(AlivePeople alive) {
		this.alive = alive;
	}
	public DiedPeople getDie() {
		return die;
	}
	public void setDie(DiedPeople die) {
		this.die = die;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	

}
