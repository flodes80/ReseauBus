package fr.floriangarcia.reseaubus.dao;

import java.io.*;

public class JsonManager {
    private static JsonManager instance = null;
    private static String fileName;

    private JsonManager() {
    }

    public static synchronized JsonManager getInstance() {
        if (instance == null) {
            instance = new JsonManager();
        }
        return instance;
    }

    public String read() {
        String json = "";
        try{
            InputStream ips=new FileInputStream(fileName);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                System.out.println(ligne);
                json+=ligne;
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return json;
    }

    public void write(String json) {
        // Ecriture sur le support physique
        File fichier =  new File(fileName) ;
        Writer writer = null ;
        try {

            // ouverture d'un flux de sortie sur un fichier
            // a pour effet de créer le fichier
            writer =  new FileWriter(fichier) ;
            writer.write(json) ;
        }  catch (IOException e) {
            System.out.println("Impossible d'écrire dans le fichier " + e.getMessage()) ;
            e.printStackTrace() ;

        }  finally {
            if (writer != null) {
                try {
                    writer.close() ;
                }  catch (IOException e) {
                    System.out.println("Erreur " + e.getMessage()) ;
                    e.printStackTrace() ;
                }
            }
        }
    }

    public static void setFileName(String fileName) {
        JsonManager.fileName = fileName;
    }
}
