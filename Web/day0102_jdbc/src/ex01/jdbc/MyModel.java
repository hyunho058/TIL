package ex01.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel {
	Object[][] data;  //type 상관없이 처리해주기 위해 Object를 사용
	String[] columnName;
	int rows, cols; //레코드 행과 열의 개수를 저장하는 변수

	@Override
	public int getColumnCount() { //필드(열)의 개수
		return columnName.length;
	}

	@Override
	public int getRowCount() { //레코드 개수 알아내기
		return data.length;
	}
	
	// 레코드 개수 알아내기 - user method
	public void getRowCount(ResultSet rsScroll) { 
		try {
			rsScroll.last();  //레코드의 마지막
			rows = rsScroll.getRow(); //레코드 개수 리턴
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	//DB에 저장되 ㄴ데이터를 활용
	public void setData(ResultSet rs) {
		try {
			
			String title;
			
			ResultSetMetaData rsmd = rs.getMetaData(); 
			cols = rsmd.getColumnCount(); 
			columnName = new String[cols];
			
			for (int i = 0; i < cols; i++) {
				columnName[i] = rsmd.getColumnName(i + 1); 
			}
			
			data = new Object[rows][cols];
			int r =0;
			
			while( rs.next() ) {
				
				for (int i = 0; i < cols; i++) {
					
					if( i != 1 ) data[r][i] = rs.getObject(i + 1); // number type
					else data[r][i] = Util.toKor((String)rs.getObject(i + 1)); // varchar2 type <-- �ѱ۱���ó�� 
				} // for end
				
				r++; // 
				
			} //while end
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
