package persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {

	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
			this.dataSource=dataSource;
		}

	public void dropDatabase(){
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;"
					+ "drop table if exists messaggio_privato;"
					+ "drop table if exists partecipazione;"
					+ "drop table if exists esibizione;"
					+ "drop table if exists acquisto;"
					+ "drop table if exists interesse;"
					+ "drop table if exists artista;"
					+ "drop table if exists experience;"
					+ "drop table if exists prodotto;"
					+ "drop table if exists evento;"
					+ "drop table if exists location;"
					+ "drop table if exists utente;";
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed drop database");
			
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createDatabase(){
		
		Connection connection = dataSource.getConnection();
		try {
			
			String delete = "create SEQUENCE sequenza_ID;"
					+ "create table location(\"nome\" varchar(255) primary key, citta varchar(255), stato varchar(255),lat float(20),lng float(20));" 
					+ "create table evento(\"id\" int primary key, data Date, location varchar(255) REFERENCES location(\"nome\"));"
					+ "create table utente (\"nickname\" varchar(255) primary key, nome varchar(255), cognome varchar(255),citta_residenza varchar(255), mail varchar(255), password varchar(255), avatar varchar(255), bio text, moderatore boolean);"
					+ "create table artista (\"nome\" varchar(255) primary key, sito_web varchar(255),immagine varchar(255),genere varchar(255),copertina varchar(255),bio varchar(255));"
					+ "create table experience(\"id\" int primary key, limite_adesione Date,posti int, organizzatore varchar(255) REFERENCES utente(\"nickname\"), evento int REFERENCES evento(\"id\"), descrizione text, pernottamento boolean, viaggio boolean, concerto boolean);" 
					+ "create table prodotto (\"id\" int primary key, nome varchar(255), prezzo int, colore varchar(255), tipo varchar(255),descrizione text,quantita int, realizzatore varchar(255) REFERENCES utente(\"nickname\"),immagine varchar(255), taglia varchar(255));"
					+ "create table messaggio_privato(\"id\" int primary key,mittente varchar(255) REFERENCES utente(\"nickname\"), data Date, oggetto varchar(255), testo varchar(255),destinatario varchar(255) REFERENCES utente(\"nickname\"));"
					+ "create table partecipazione(\"id\" int primary key, partecipante varchar(255) REFERENCES utente(\"nickname\"),exp int REFERENCES experience(\"id\"));"
					+ "create table esibizione(\"id\" int primary key, artista varchar(255) REFERENCES artista(\"nome\"), evento int REFERENCES evento(\"id\"));"
					+ "create table acquisto(\"id\" int primary key, utente varchar(255) REFERENCES utente(\"nickname\"),prodotto int REFERENCES prodotto(\"id\"), quantita int);"
					+ "create table interesse(\"id\" int primary key, utente varchar(255) REFERENCES utente (\"nickname\"),evento int REFERENCES evento(\"id\"));";
				PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed create database");
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			}
		}
	}


	public  void resetDatabase() {
			
			/*Connection connection = dataSource.getConnection();
			try {
				String delete = "delete FROM utente";
				PreparedStatement statement = connection.prepareStatement(delete);
				
				statement.executeUpdate();

				delete = "delete FROM artista";
				statement = connection.prepareStatement(delete);
				
				delete = "delete FROM location";
				statement = connection.prepareStatement(delete);
				
				delete="delete FROM experience";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM prodotto";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM evento";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM messaggio_privato";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM messaggio_pubblico";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM genere_musicale";
				statement=connection.prepareStatement(delete);
				
				delete="delete FROM "
				
				statement.executeUpdate();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					
					throw new PersistenceException(e.getMessage());
				}
			}*/
		}
}
