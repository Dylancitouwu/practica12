package Model;

public interface parametrizable {
	public final String ip="localhost",
			port="3306",
			name="shop",
			user="root",
			psw="root",
			driver="com.mysql.cj.jdbc.Driver";
	public default String getURL() {
		return String.format("jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false&user=%s&password=%s",
						ip,port,name,user,psw);
	}
	
}
