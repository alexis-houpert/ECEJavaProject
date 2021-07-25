package Model;

import Application.Constantes;
import Model.DataAccessLayer.DalShopItem;
import Model.Shop.Booking;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Static class that represent data of the pie Chart
 */
public class DataPieChart {
   private static Integer cptAudi = 0;
    private static Integer cptMercedes = 0;
    private static Integer cptPorsche = 0;
    private static Integer cptVolkswagen = 0;
    private static Integer cptOther = 0;


    public static void calculData()
    {
        List<Booking> allBooking = new ArrayList<>();
        try {
            allBooking = DalShopItem.getAllBooking();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cptAudi = 0;
        cptMercedes = 0;
        cptVolkswagen = 0;
        cptPorsche = 0;
        cptOther = 0;

        for (Booking book : allBooking)
        {
            if(book.getCar().getBrand().toLowerCase(Locale.ROOT).equals(Constantes.AUDI.toLowerCase(Locale.ROOT)))
            {
                cptAudi++;
            }
            else if (book.getCar().getBrand().toLowerCase(Locale.ROOT).equals(Constantes.MERCEDES.toLowerCase(Locale.ROOT)))
            {
                cptMercedes++;
            }
            else if (book.getCar().getBrand().toLowerCase(Locale.ROOT).equals(Constantes.PORSCHE.toLowerCase(Locale.ROOT)))
            {
                cptPorsche++;
            }
            else if (book.getCar().getBrand().toLowerCase(Locale.ROOT).equals(Constantes.VOLKSWAGEN.toLowerCase(Locale.ROOT)))
            {
                cptVolkswagen++;
            }
            else if (book.getCar().getBrand().equals(""))
            {
                cptOther++;
            }
        }
    }

    public static Integer getCptAudi() {
        return cptAudi;
    }

    public void setCptAudi(Integer cptAudi) {
        this.cptAudi = cptAudi;
    }

    public static Integer getCptMercedes() {
        return cptMercedes;
    }

    public void setCptMercedes(Integer cptMercedes) {
        this.cptMercedes = cptMercedes;
    }

    public static Integer getCptPorsche() {
        return cptPorsche;
    }

    public void setCptPorsche(Integer cptPorsche) {
        this.cptPorsche = cptPorsche;
    }

    public static Integer getCptVolkswagen() {
        return cptVolkswagen;
    }

    public void setCptVolkswagen(Integer cptVolkswagen) {
        this.cptVolkswagen = cptVolkswagen;
    }

    public static Integer getCptOther() {
        return cptOther;
    }

    public void setCptOther(Integer cptOther) {
        this.cptOther = cptOther;
    }


}
