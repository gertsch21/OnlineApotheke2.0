package restTest;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorldState {
	private Date current_time;
	private String state;

	public WorldState(String state) {
		this.state = state;
		this.current_time = new Date();
	}

	public Date getCurrent_time() {
		return current_time;
	}

	public String getState() {
		return state;
	}

}