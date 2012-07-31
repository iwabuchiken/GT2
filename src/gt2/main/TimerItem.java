package gt2.main;

public class TimerItem {

//	int db_id;
	String message;

	long duration;
	long created_at;
	
	/****************************************
	 * Constructors
	 ****************************************/
	public TimerItem(String message, long duration, long created_at) {
		
		this.message = message;
		this.duration = duration;
		
		this.created_at = created_at;
		
	}//public FileItem()

	public String getMessage() {
		return message;
	}

	public long getDuration() {
		return duration;
	}

	public long getCreated_at() {
		return created_at;
	}

	/****************************************
	 * Methods
	 ****************************************/
	
}//public class FileItem
