package fr.floriangarcia.reseaubus.dao.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.dao.JsonManager;
import fr.floriangarcia.reseaubus.entities.Bus;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class BusDAOJson extends DAOGenerique<Bus> {

    private final JsonManager jsonManager = JsonManager.getInstance();
    private static BusDAOJson instance = null;
    private final Gson gson;

    public BusDAOJson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static synchronized BusDAOJson getInstance() {
        if (instance == null) {
            instance = new BusDAOJson();
        }
        return instance;
    }

    @Override
    public Bus delete(Bus obj) {
        LinkedList<Bus> lesBus = findAll();
        lesBus.remove(obj);
        saveAll(lesBus);
        obj = null;
        return obj;
    }

    @Override
    public Bus create(Bus obj) {
        LinkedList<Bus> lesBus = findAll();
        lesBus.add(obj);
        saveAll(lesBus);
        return obj;
    }

    @Override
    public Bus update(Bus obj) {
        LinkedList<Bus> lesBus = findAll();
        Bus busReplace = (Bus) lesBus.stream().filter(c -> c == obj);
        if(busReplace != null)
        {
            busReplace = obj;
            saveAll(lesBus);
        }
        return obj;
    }

    @Override
    public void saveAll(LinkedList<Bus> linkedListObj) {
        jsonManager.write(gson.toJson(linkedListObj));
    }

    @Override
    public Bus findById(int cle) {
        LinkedList<Bus> lesBus = findAll();

        return (Bus) lesBus.stream().filter(b -> b.getNumero() == cle);
    }

    @Override
    public LinkedList<Bus> findByName(String name) {
        return null;
    }

    @Override
    public LinkedList<Bus> findAll() {
        LinkedList<Bus> result = new LinkedList<>();
        String json = jsonManager.read();
        if (json.compareTo("")!=0)
        {
            Type type = new TypeToken<LinkedList<Bus>>(){}.getType();
            result = gson.fromJson(json, type);
        }

        return result;
    }
}
