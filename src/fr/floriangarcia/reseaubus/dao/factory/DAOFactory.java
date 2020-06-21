package fr.floriangarcia.reseaubus.dao.factory;

import fr.floriangarcia.reseaubus.dao.DAOGenerique;
import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.Bus;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;

public abstract class DAOFactory {

    public enum Source {
        MYSQL,
        JSON
    }

    public abstract DAOGenerique<Bus> getBusDAO();

    public abstract DAOGenerique<Arret> getArretDAO();

    public abstract DAOGenerique<LigneBus> getLigneBusDAO();

    public abstract DAOGenerique<ReseauBus> getReseauBusDAO();

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
