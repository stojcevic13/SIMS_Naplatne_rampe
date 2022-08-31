import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import users.Leader;

public class TollStation {
	
	private int id;
	private String location;
	private List<TollBooth> tollBooths;
	private Leader leader;
	
	public TollStation() {
		this.tollBooths = new ArrayList<TollBooth>();
	}

	public TollStation(int id, String location, List<TollBooth> tollBooths, Leader leader) {
		this();
		this.id = id;
		this.location = location;
		this.tollBooths = tollBooths;
		this.leader = leader;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<TollBooth> getTollBooths() {
		return tollBooths;
	}

	public void setTollBooths(List<TollBooth> tollBooths) {
		this.tollBooths = tollBooths;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, leader, location, tollBooths);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TollStation other = (TollStation) obj;
		return id == other.id && Objects.equals(leader, other.leader) && Objects.equals(location, other.location)
				&& Objects.equals(tollBooths, other.tollBooths);
	}

	@Override
	public String toString() {
		return "TollStation [id=" + id + ", location=" + location + ", tollBooths=" + tollBooths.size() + ", leader=" + leader.getJmbg()
				+ "]";
	}

	
}
