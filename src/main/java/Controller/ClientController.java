/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Cliente;
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
    
    
    
}
