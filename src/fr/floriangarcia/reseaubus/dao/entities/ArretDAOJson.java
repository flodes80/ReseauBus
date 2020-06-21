package fr.floriangarcia.reseaubus.dao.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.dao.JsonManager;
import fr.floriangarcia.reseaubus.entities.Arret;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ArretDAOJson extends DAOGenerique<Arret> {

    private final JsonManager jsonManager = JsonManager.getInstance();
    private static ArretDAOJson instance = null;
    private final Gson gson;

    public ArretDAOJson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static synchronized ArretDAOJson getInstance() {
        if (instance == null) {
            instance = new ArretDAOJson();
        }
        return instance;
    }

    @Override
    public Arret delete(Arret obj) {
        LinkedList<Arret> lesArrets = findAll();
        lesArrets.remove(obj);
        saveAll(lesArrets);
        obj = null;
        return obj;
    }

    @Override
    public Arret create(Arret obj) {
        LinkedList<Arret> lesArrets = findAll();
        lesArrets.add(obj);
        saveAll(lesArrets);
        return obj;
    }

    @Override
    public Arret update(Arret obj) {
        LinkedList<Arret> lesArrets = findAll();
        Arret arretReplace = (Arret) lesArrets.stream().filter(c -> c == obj);
        if(arretReplace != null)
        {
            arretReplace = obj;
            saveAll(lesArrets);
        }
        return obj;
    }

    @Override
    public void saveAll(LinkedList<Arret> linkedListObj) {
        jsonManager.write(gson.toJson(linkedListObj));
    }

    @Override
    public Arret findById(int cle) {
        return null;
    }

    @Override
    public LinkedList<Arret> findByName(String name) {
        LinkedList<Arret> lesArrets = findAll();

        return (LinkedList<Arret>) lesArrets.stream().filter(a -> a.getNom().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public LinkedList<Arret> findAll() {
        LinkedList<Arret> result = new LinkedList<>();
        String json = jsonManager.read();
        if (json.compareTo("")!=0)
        {
            Type type = new TypeToken<LinkedList<Arret>>(){}.getType();
            result = gson.fromJson(json, type);
        }

        return result;
    }
}
