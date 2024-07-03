package xml.explore;

import java.util.ArrayList;

public class lettura_del_parser_data {
//leggo il tipo della tappa(prima colonna) che poi dovrò andare a leggere nel context
	private MyParserTemplate parserData=new MyParserTemplate();
	public void getTipoTappa(){
		
		ArrayList<MyTemplate> Tipotappe =parserData.getParsedData();
		System.out.println(Tipotappe.get(1));
	}
}
