package dataAccess;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Apustua;
import domain.Bezeroa;
import domain.Event;
import domain.Pronostikoa;
import domain.PronostikoaContainer;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import utility.TestUtilityDataAccess;

class DataAccessTest {

	private static DataAccess sut= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	private static TestUtilityDataAccess testDA = new TestUtilityDataAccess();
	
	private Event ev;
	private Question q;
	private Pronostikoa p;
	private Bezeroa b;
	private Vector<Apustua> a;
	//int t;
	//int t2;
	//private Apustua ap;
	
	@Test
	@DisplayName("Test 1: ...") 
	public void test1() {

		try {

			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2022");
			String eventText = "Event Text";
			String queryText = "Zeinek irabaziko du partidua?";
			Float betMinimum = 2f;
			//PRONOSTICO
			String d="Pronostic description";
			int kuota=2000;
			ev = new Event(2, "Eibar-Barcelona", oneDate);
			q= new Question(queryText, betMinimum,  ev);
			String izena="Andrea";
			String abizena1="Garcia";
			String abizena2="Iche";
			String erabiltzaileIzena="Juan";
			String pasahitza="1234";
			String telefonoZbkia="635274008";
			String email="garciaiche@gmail.com";
			Date jaiotzeData=sdf.parse("02/03/2000");
			b=new Bezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, email, jaiotzeData);
			ev = new Event(2, "Eibar-Barcelona", oneDate);
			q= ev.addQuestion(queryText, 1);;
			p = q.addPronostic(d, kuota);
			a=p.getApustuak();
				
			ArrayList<Pronostikoa> pronostikoak= new ArrayList<Pronostikoa>();
		
			testDA.open();
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			p=testDA.createPronostic(q, d, kuota);
			testDA.close();
		}
		catch(Exception e) {
			
		}
			
			
		
	}

	
	

}
