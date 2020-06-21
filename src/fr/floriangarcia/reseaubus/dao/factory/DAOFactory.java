package fr.floriangarcia.reseaubus.dao.factory;

import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.entities.Bus;

public abstract class DAOFactory {

    public enum Source {
        MYSQL,
        JSON
    }

    public abstract DAOGenerique<Bus> getBusDAO();

    public static DAOFactory getFactory(Source source)
    {
        switch(source)
        {
            case MYSQL:
            case JSON:
                return DAOJsonFactory.getInstance();
            default:
                return null;
        }
    }

}
