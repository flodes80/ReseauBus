package fr.floriangarcia.reseaubus.dao.factory;

import fr.floriangarcia.reseaubus.dao.entities.BusDAOJson;
import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.entities.Bus;

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
}
