package fr.floriangarcia.reseaubus.dao.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.dao.JsonManager;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class ReseauBusDAOJson extends DAOGenerique<ReseauBus> {

    private final JsonManager jsonManager = JsonManager.getInstance();
    private static ReseauBusDAOJson instance = null;
    private final Gson gson;

    public ReseauBusDAOJson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static synchronized ReseauBusDAOJson getInstance() {
        if (instance == null) {
            instance = new ReseauBusDAOJson();
        }
        return instance;
    }

    @Override
    public ReseauBus delete(ReseauBus obj) {
        LinkedList<ReseauBus> lesReseaux = findAll();
        lesReseaux.remove(obj);
        saveAll(lesReseaux);
        obj = null;
        return obj;
    }

    @Override
    public ReseauBus create(ReseauBus obj) {
        LinkedList<ReseauBus> lesReseaux = findAll();
        lesReseaux.add(obj);
        saveAll(lesReseaux);
        return obj;
    }

    @Override
    public ReseauBus update(ReseauBus obj) {
        LinkedList<ReseauBus> lesReseaux = findAll();
        ReseauBus reseauBusReplace = (ReseauBus) lesReseaux.stream().filter(c -> c == obj);
        if(reseauBusReplace != null)
        {
            reseauBusReplace = obj;
            saveAll(lesReseaux);
        }
        return obj;
    }

    @Override
    public void saveAll(LinkedList<ReseauBus> linkedListObj) {
        jsonManager.write(gson.toJson(linkedListObj));
    }

    @Override
    public ReseauBus findById(int cle) {
        return null;
    }

    @Override
    public LinkedList<ReseauBus> findByName(String name) {
        return null;
    }

    @Override
    public LinkedList<ReseauBus> findAll() {
        LinkedList<ReseauBus> result = new LinkedList<>();
        String json = jsonManager.read();
        if (json.compareTo("")!=0)
        {
            Type type = new TypeToken<LinkedList<ReseauBus>>(){}.getType();
            result = gson.fromJson(json, type);
        }

        return result;
    }
}
