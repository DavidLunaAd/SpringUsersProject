package com.curso.spring.controllers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.curso.spring.dao.UsuarioDao;
import com.curso.spring.models.Usuario;
import com.curso.spring.utils.JWTUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	final String auth = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key=";
	final String key = "AIzaSyBMAMUxS-XyTjLySe6jAs6rf8cmhWzIdgI";
	
	 private static final String BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken";
	    private static final String OPERATION_AUTH = "verifyPassword";
	    private static final String OPERATION_REFRESH_TOKEN = "token";
	    private static final String OPERATION_ACCOUNT_INFO = "getAccountInfo";

	    private String firebaseKey;

	    private static AuthController instance = null;

	    protected AuthController() {
	       firebaseKey = "AIzaSyBMAMUxS-XyTjLySe6jAs6rf8cmhWzIdgI";
	    }

	    public static AuthController getInstance() {
	      if(instance == null) {
	         instance = new AuthController();
	      }
	      return instance;
	    }
	 	//@RequestMapping(value="/login", method = RequestMethod.POST)
	    public String auth(@RequestBody String username, String password) throws Exception { 

	        HttpURLConnection urlRequest = null;
	        String token = null;

	        try {
	            URL url = new URL(BASE_URL+OPERATION_AUTH+"?key="+firebaseKey);
	            urlRequest = (HttpURLConnection) url.openConnection();
	            urlRequest.setDoOutput(true);
	            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            OutputStream os = urlRequest.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
	            osw.write("{\"email\":\""+username+"\",\"password\":\""+password+"\",\"returnSecureToken\":true}");
	            osw.flush();
	            osw.close();

	            urlRequest.connect();

	            JsonParser jp = new JsonParser(); //from gson
	            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
	            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

	            token = rootobj.get("idToken").getAsString();

	        } catch (Exception e) {
	            return null;
	        } finally {
	            urlRequest.disconnect();
	        }

	        return token;
	    }

	    public String getAccountInfo(String token) throws Exception {

	        HttpURLConnection urlRequest = null;
	        String email = null;

	        try {
	            URL url = new URL(BASE_URL+OPERATION_ACCOUNT_INFO+"?key="+firebaseKey);
	            urlRequest = (HttpURLConnection) url.openConnection();
	            urlRequest.setDoOutput(true);
	            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            OutputStream os = urlRequest.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
	            osw.write("{\"idToken\":\""+token+"\"}");
	            osw.flush();
	            osw.close();
	            urlRequest.connect();

	            JsonParser jp = new JsonParser(); //from gson
	            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
	            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

	            email = rootobj.get("users").getAsJsonArray().get(0).getAsJsonObject().get("email").getAsString();

	        } catch (Exception e) {
	            return null;
	        } finally {
	            urlRequest.disconnect();
	        }

	        return email;

	    }
//	@Autowired 
//	private UsuarioDao usuarioDao; 
//	
//	@Autowired
//	private JWTUtil jwtutil;
//	
//	@RequestMapping(value="api/login",  method = RequestMethod.POST)
//	public String login(@RequestBody Usuario usuario) {
//		
//		Usuario usuarioLog = usuarioDao.obtenerUsuario(usuario);
//		if (usuarioLog != null) {
//			String tokenJwt = jwtutil.create(String.valueOf(usuarioLog.getId()), usuarioLog.getEmail());
//			return tokenJwt;
//		}
//		return "FAIL";
//	}

}
