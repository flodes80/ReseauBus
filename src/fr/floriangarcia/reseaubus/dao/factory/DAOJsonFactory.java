package fr.floriangarcia.reseaubus.dao.factory;

import fr.floriangarcia.reseaubus.dao.entities.ArretDAOJson;
import fr.floriangarcia.reseaubus.dao.entities.BusDAOJson;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.dao.entities.LigneBusDAOJson;
import fr.floriangarcia.reseaubus.dao.entities.ReseauBusDAOJson;
import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.Bus;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;

public class DAOJsonFactory extends DAOFactory{
    private static DAOJsonFactory instance = null;

    public static synchronized DAOJsonFactory getInstance()
    {
        if (instance==null) {
            instance= new DAOJsonFactory();
        }
        return instance;
    }

    @Override
    public DAOGenerique<Bus> getBusDAO() {
        return BusDAOJson.getInstance();
    }

    @Override
    public DAOGenerique<Arret> getArretDAO() {
        return ArretDAOJson.getInstance();
    }

    @Override
    public DAOGenerique<LigneBus> getLigneBusDAO() {
        return LigneBusDAOJson.getInstance();
    }

    @Override
    public DAOGenerique<ReseauBus> getReseauBusDAO() {
        return ReseauBusDAOJson.getInstance();
    }
}
