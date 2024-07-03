package com.login;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class MyDatabase {  

	SQLiteDatabase mDb;
	DbHelper mDbHelper;
	Context mContext;
	private static final String DB_NAME="login";//nome del db
	private static final int DB_VERSION=1; //numero di versione del nostro db
	
	public MyDatabase(Context ctx){
		mContext=ctx;
		mDbHelper=new DbHelper(ctx, DB_NAME, null, DB_VERSION);	  //quando istanziamo questa classe, istanziamo anche l'helper (vedi sotto)	
	}
	
	public void open(){  //il database su cui agiamo Ã¨ leggibile/scrivibile
		mDb=mDbHelper.getWritableDatabase();
		
	}
	
	public void close(){ //chiudiamo il database su cui agiamo
		mDb.close();
	}
	
	
	//i seguenti 2 metodi servono per la lettura/scrittura del db. aggiungete e modificate a discrezione
	
	public void insertName(String nome) throws SQLiteConstraintException{ 
		//metodo per inserire i dati
		ContentValues cv=new ContentValues();
	
		cv.put(LoginMetaData.NOME_UTENTE, nome);//come posso recuperare la sua id?
		//facendo una query...
		
	try{
		mDb.insertOrThrow(LoginMetaData.LOGIN_TABLE, null, cv);
	}catch (SQLiteConstraintException e) {
		// TODO: handle exception
		System.out.println("nome già inserito");
		throw new SQLiteConstraintException();
	}
	
	}
	public void insertPunteggio(int punteggio, int row_id){
		mDb=mDbHelper.getWritableDatabase();
		//metodo per inserire i dati
		ContentValues cv=new ContentValues();
	
		cv.put(LoginMetaData.PUNTEGGIO, punteggio);//come posso recuperare la sua id?
		//facendo una query...
		 mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null);
	
	}
	public int getPunteggio(String nome){

		mDb=mDbHelper.getWritableDatabase();
		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int punteggio=cursor.getColumnIndex(MyDatabase.LoginMetaData.PUNTEGGIO);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))
					System.out.println(cursor.getString(punteggio));
					  return cursor.getInt(punteggio);
			  }while(cursor.moveToNext());
		  }
//Chiudo il cursore
 if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
	   }
	return 0;   
		
	}
	
	public void insertComment1(String commento, int row_id){
		mDb=mDbHelper.getWritableDatabase();
		//metodo per inserire i dati
		ContentValues cv=new ContentValues();
	
		cv.put(LoginMetaData.COMMENTO1, commento);//come posso recuperare la sua id?
		//facendo una query...
		 mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null);
	
	}
	public void insertComment2(String commento, int row_id){
		mDb=mDbHelper.getWritableDatabase();
		//metodo per inserire i dati
		ContentValues cv=new ContentValues();
	
		cv.put(LoginMetaData.COMMENTO2, commento);//come posso recuperare la sua id?
		//facendo una query...
		 mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null);
	
	}
	public String getComment1(String nome){


		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int commento1=cursor.getColumnIndex(MyDatabase.LoginMetaData.COMMENTO1);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))
					System.out.println(cursor.getString(commento1));
					  return cursor.getString(commento1);
			  }while(cursor.moveToNext());
		  }
//Chiudo il cursore
  if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
	   }
	return null;   
		
	}
	public String getComment2(String nome){


		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int commento2=cursor.getColumnIndex(MyDatabase.LoginMetaData.COMMENTO2);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))
					System.out.println(cursor.getString(commento2));
					  return cursor.getString(commento2);
			  }while(cursor.moveToNext());
		  }
//Chiudo il cursore
 if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
	   }
	return null;   
		
	}
	public void insertData(String data, int row_id){
		mDb=mDbHelper.getWritableDatabase();
		//metodo per inserire i dati
		ContentValues cv=new ContentValues();
	
		cv.put(LoginMetaData.DATA, data);//come posso recuperare la sua id?
		//facendo una query...
		 mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null);
	
	}
	public String getData(String nome){


		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int data=cursor.getColumnIndex(MyDatabase.LoginMetaData.DATA);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))
					System.out.println(cursor.getString(data));
					  return cursor.getString(data);
			  }while(cursor.moveToNext());
		  }
//Chiudo il cursore
 if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
	   }
	return null;   
		
	}
	public void delete_profilo(int row_id){
		mDb=mDbHelper.getWritableDatabase();
		mDb.delete(LoginMetaData.LOGIN_TABLE, LoginMetaData.ID+ "=" + row_id, null);
		//ma dopo devo ricaricare lo spinner!!!!!!!!!!!
	}
	public boolean  update_utente_attivo(int row_id,boolean valore){
		mDb=mDbHelper.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(LoginMetaData.UTENTE_ATTIVO, valore);
		return mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null)>0;
		}
	
	public boolean  update_gioco_finito(int row_id,int valore){
		mDb=mDbHelper.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(LoginMetaData.FINE_GIOCO, valore);
		return mDb.update(LoginMetaData.LOGIN_TABLE, cv, LoginMetaData.ID+ "=" + row_id, null)>0;
		}
	public int recupera_id(String nome){
		
		
		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int id_utente=cursor.getColumnIndex(MyDatabase.LoginMetaData.ID);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))return cursor.getInt(id_utente);
			  }while(cursor.moveToNext());
		  }
 // Chiudo il cursore
    if (cursor != null && !cursor.isClosed())
	  {
 	  cursor.close();  
  	   }
	return 0;   
		
	}
	//recupera _id da andare ad aggiornare

	public int get_fine_gioco(String nome){
		
		
		 Cursor cursor = this.fetchnomi();
		//this.startManagingCursor(cursor);
		int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
	      int flag_fine_gioco=cursor.getColumnIndex(MyDatabase.LoginMetaData.FINE_GIOCO);
		  if(cursor.moveToFirst()) {
			  do {
				  if(cursor.getString(nome_int).equalsIgnoreCase(nome))
					System.out.println( cursor.getInt(flag_fine_gioco));
					  return cursor.getInt(flag_fine_gioco);
			  }while(cursor.moveToNext());
		  }
// Chiudo il cursore
   if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
 	   }
	return 0;   
		
	}
	public Cursor fetchnomi(){ //metodo per fare la query di tutti i dati
		return mDb.query(LoginMetaData.LOGIN_TABLE, null,null,null,null,null,null);		
	}
	

	static class LoginMetaData {  // i metadati della tabella, accessibili ovunque
		static final String LOGIN_TABLE = "login";
		static final String ID = "_id";
		static final String PUNTEGGIO="punteggio";
		static final String UTENTE_ATTIVO = "utente_attivo";
		static final String NOME_UTENTE = "nome";
		static final String FINE_GIOCO="fine_gioco";
		static final String COMMENTO1="commento1";
		static final String COMMENTO2="commento2";
		static final String DATA="data";
	}

	private static final String LOGIN_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "  //codice sql di creazione della tabella
			+ LoginMetaData.LOGIN_TABLE + " ( " 
			+ LoginMetaData.ID+ " integer primary key autoincrement,  "
			+LoginMetaData.UTENTE_ATTIVO+ " boolean, "
			+LoginMetaData.NOME_UTENTE+" text not null, "
			+LoginMetaData.PUNTEGGIO+ " integer, "
			+LoginMetaData.FINE_GIOCO+ " integer, "
			+LoginMetaData.COMMENTO1+" text, "
			+LoginMetaData.COMMENTO2+" text, "
			+LoginMetaData.DATA+" text, "
			+ "UNIQUE(nome)); ";
	
	
	private class DbHelper extends SQLiteOpenHelper { //classe che ci aiuta nella creazione del db

		public DbHelper(Context context, String name, CursorFactory factory,int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) { //solo quando il db viene creato, creiamo la tabella
			_db.execSQL(LOGIN_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			//qui mettiamo eventuali modifiche al db, se nella nostra nuova versione della app, il db cambia numero di versione

		}

	}


}
