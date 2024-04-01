/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.Cliente;
import Models.Clientes;
import Models.Factura;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author gabri este es una clase de ejemplo para explorar librerias
 */
public class MainExample {

    public static void main(String[] args) {
       
        
        obtenerRegistros("http://localhost:8080/api/v1.0/cliente");
        File file = new File("src/main/resources/config.properties");
        if(file.exists()){
            System.out.println("existe!");
        }else{
            System.out.println("no existe!");
        }
    }

    public static void obtenerRegistros(String url) {
        OkHttpClient client = new OkHttpClient();
        try{
            Request req = new Request.Builder().url(url).build();
            Response response = client.newCall(req).execute();
            String json = response.body().string();
            System.out.println(json);
            imprimirCliente(json);
            
        }catch(IOException e){
            System.out.println("error : "+e);
        }
    }
    
    public static void imprimirCliente(String json){
        Gson gson = new Gson();
        TypeToken<List<Cliente>> collectionType = new TypeToken<List<Cliente>>(){};
        List<Cliente> cli = gson.fromJson(json, collectionType);
        System.out.println("--------------------------");
        System.out.println("Cliente "+cli.get(0).getId());
        System.out.println("nombre "+cli.get(0).getNombre());
        System.out.println("apellido "+cli.get(0).getApellido());
        System.out.println("email "+cli.get(0).getEmail());
        System.out.println("fecha creacion "+cli.get(0).getFechaCreacion());
        System.out.println("Facturas: ");
        for(Factura fact : cli.get(0).getFacturas()){
            System.out.println("++++++++++++++++++");
            System.out.println("    ID Factura: "+fact.getId());
            System.out.println("    Descripcion "+fact.getDescripcion());
            System.out.println("++++++++++++++++++");
        }
        System.out.println("--------------------------");
    }

}

class Persona {

    private String nombre;
    private String apellido;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
