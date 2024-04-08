/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Cliente;
import Models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.mycompany.mavenproject1.MainExample.imprimirCliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author gabri
 */
public class ClientController {
    
    private Gson gson;
    private Properties props=new Properties();
    
    
    public ClientController() throws FileNotFoundException, IOException{
        gson=new Gson();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
        fis.close();
    }
    
    
    public List<Cliente> obtenerClientes(){
         OkHttpClient client = new OkHttpClient();
         List<Cliente> clientes=new ArrayList<>();
        try{
            Request req = new Request.Builder().url(props.getProperty("URL_CLIENTES")).build();
            Response response = client.newCall(req).execute();
            String json = response.body().string();
            System.out.println(json);
            TypeToken<List<Cliente>> collectionType = new TypeToken<List<Cliente>>(){};
            clientes = gson.fromJson(json, collectionType);
            
        }catch(IOException e){
            System.out.println("error : "+e);
        }
        return clientes;
        
    }
    
    public boolean validarUsuario(String username,String password){
        OkHttpClient client = new OkHttpClient();
        List<Usuario> usuarios=new ArrayList<>();
        boolean isValid=false;
        try{
            Request req = new Request.Builder().url(props.getProperty("URL_USUARIOS")).build();
            Response response = client.newCall(req).execute();
            String json = response.body().string();
            System.out.println(json);
            TypeToken<List<Usuario>> collectionType = new TypeToken<List<Usuario>>(){};
            usuarios = gson.fromJson(json, collectionType);
            for(Usuario user:usuarios){
                if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                    isValid=true;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("error : "+e);
            JOptionPane.showMessageDialog(null, "Error al consultar la lista de usuarios: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            
        }
        return isValid;
    }
    
    
    
}
