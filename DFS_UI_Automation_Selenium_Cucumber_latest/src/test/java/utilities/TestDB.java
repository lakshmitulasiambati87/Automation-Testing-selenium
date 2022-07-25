package utilities;

import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataBaseConnection db = new DataBaseConnection();
		try {
			//db.getDBresult();
			db.multipleDBResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
