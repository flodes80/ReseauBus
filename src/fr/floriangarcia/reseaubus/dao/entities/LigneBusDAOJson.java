package fr.floriangarcia.reseaubus.dao.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.dao.JsonManager;
import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.LigneBus;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class LigneBusDAOJson extends DAOGenerique<LigneBus> {

    private final JsonManager jsonManager = JsonManager.getInstance();
    private static LigneBusDAOJson instance = null;
    private final Gson gson;

    public LigneBusDAOJson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static synchronized LigneBusDAOJson getInstance() {
        if (instance == null) {
            instance = new LigneBusDAOJson();
        }
        return instance;
    }

    @Override
    public LigneBus delete(LigneBus obj) {
        LinkedList<LigneBus> lesLignesBus = findAll();
        lesLignesBus.remove(obj);
        saveAll(lesLignesBus);
        obj = null;
        return obj;
    }

    @Override
    public LigneBus create(LigneBus obj) {
        LinkedList<LigneBus> lesLignesBus = findAll();
        lesLignesBus.add(obj);
        saveAll(lesLignesBus);
        return obj;
    }

    @Override
    public LigneBus update(LigneBus obj) {
        LinkedList<LigneBus> lesLignesBus = findAll();
        LigneBus ligneBusReplace = (LigneBus) lesLignesBus.stream().filter(c -> c == obj);
        if(ligneBusReplace != null)
        {
            ligneBusReplace = obj;
            saveAll(lesLignesBus);
        }
        return obj;
    }

    @Override
    public void saveAll(LinkedList<LigneBus> linkedListObj) {
        jsonManager.write(gson.toJson(linkedListObj));
    }

    @Override
    public LigneBus findById(int cle) {
        return null;
    }

    @Override
    public LinkedList<LigneBus> findByName(String name) {
        LinkedList<LigneBus> lesLignesBus = findAll();

        return (LinkedList<LigneBus>) lesLignesBus.stream().filter(l -> l.getNom().equalsIgnoreCase(name)).collect(Collectors.toList());

    }

    @Override
    public LinkedList<LigneBus> findAll() {
        LinkedList<LigneBus> result = new LinkedList<>();
        String json = jsonManager.read();
        if (json.compareTo("")!=0)
        {
            Type type = new TypeToken<LinkedList<LigneBus>>(){}.getType();
            result = gson.fromJson(json, type);
        }

        return result;
    }
}
