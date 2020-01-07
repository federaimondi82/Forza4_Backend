package net.studionotturno;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
	
	
	 /**Permette di sapere se una email e' gia' presente nel database
	 * @param email
	 * @return
	 */
	@GetMapping(value="/users/email/{email}")
	 public boolean checkEmail(@PathVariable String email) {
		 System.out.println("ci passa1");
		 String query="select id from user where email='"+email+"'";
		 int i=0;
		 ResultSet result=null;
		 try {
			 result=DBConnection.getInstance().sendQuery(query);
			 while(result.next()) {
				 i=result.getInt(1);
				 return true;
			 }
			 
		 }catch(SQLException e){
			 return false;
		 }
		 System.out.println("ci passa2");
		return false;
		 
	 }
	
	/**Permette di sapere se un nickname e' gia' presente nel database
	 * @param email
	 * @return
	 */
	@GetMapping(value="/users/nickname/{nickname}")
	 public boolean checkNickname(@PathVariable String nickname) {
		 
		 String query="select id from user where nickname='"+nickname+"'";
		 int i=0;
		 ResultSet result=null;
		 try {
			 result=DBConnection.getInstance().sendQuery(query);
			 while(result.next()) {
				 i=result.getInt(1);
				 return true;
			 }
			 
		 }catch(SQLException e){
			 return false;
		 }
		return false;
		 
	 }
	
	/**
	 * viene inserito un nuovo utente sul database
	 * i controlli vengono effettuti con altre query dal clinet verso il server
	 * se passano i controlli allora si effettua la chiamata per l quesri di inserimento
	 * @param name
	 * @param lastname
	 * @param email
	 * @param pass
	 * @param nickname
	 * @return
	 */
	@PostMapping(value="/users/{name}:{lastname}:{email}:{pass}:{nickname}")
	public boolean signup(@PathVariable String name,@PathVariable String lastname,
			@PathVariable String email,@PathVariable String pass,@PathVariable String nickname) {
		
		String query="insert into user(name,lastname,email,pass,nickname,victory,losed,draw) values"
				+ "('"+name+"','"+lastname+"','"+email+"','"+pass+"','"+nickname+"',0,0,0)";
		
		//System.out.println(query);
		boolean check=false;
		try {
			check=DBConnection.getInstance().insertData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	@GetMapping(value="/users/pass/{pass}")
	public String checkPass(@PathVariable String pass) {
		
		//TODO decifra i dati e ritorna la string decifrata,se sono uguali allora va bene	
		/*try {
			
			String key="sDFp!d";
			Key secret=new SecretKeySpec(key.getBytes(), "AES");
		
			Cipher cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE,secret);
			
			byte[] out=cipher.doFinal(pass.toString().getBytes());
			
			System.out.println(out.toString());
			
			return out.toString();
			
		} catch (NoSuchAlgorithmException |NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		*/
		return pass;
	}

	@GetMapping(value="/users/signin/{email}:{pass}")
	public String signin(@PathVariable String email,@PathVariable String pass) {
		User u=new User();	
		try {
			String query="select * from user where email='"+email+"' and pass='"+pass+"'";
					
			ResultSet result=DBConnection.getInstance().sendQuery(query);
			
			
			while(result.next()) {
				u.id(result.getInt(1))
					.name(result.getString(2))
					.lastname(result.getString(3))
					.email(result.getString(4))
					.pass(result.getString(5))
					.nickname(result.getString(6))
					.victory(result.getInt(7))
					.losed(result.getInt(8))
					.draw(result.getInt(9));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return u.toString();
	}	
	
}
