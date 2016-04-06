package com.keriackus.auction.data.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.keriackus.auction.data.entities.Account;
import com.keriackus.auction.data.entities.Item;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keriackus on 4/1/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "auction.keriackus";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;

    /**
     * Constructor,
     *
     * @param context Context used for database
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource, Item.class);

            insertTestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTestData() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 15);

        Item item;
        try {
            item = new Item("prints", "Keith Hairing Chair", "A very rare Print", 400.0, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("prints", "Bansky signed print", "in a very good condition", 300.0, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("prints", "Jasper Johns", "Call +9783040203 for more information", 250.0, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c.add(Calendar.MINUTE, -10);

            item = new Item("prints", "Andy Warhol Cow", "One Of a Kind", 780, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c.add(Calendar.MINUTE, 110);

            item = new Item("prints", "Tattoo Band Sticker", "condition and state upon bidding request", 20, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("prints", "Joan miro", "You don't want to miss this", 250.0, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


            c.add(Calendar.DAY_OF_YEAR, 1);

            item = new Item("Jewelry", "Deco Diamond Ring", "Very beautiful ring for your woman", 1400, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


            item = new Item("Jewelry", "Lot of 13 bracelets", "Call us on +212328282 for more info", 13, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


            item = new Item("Jewelry", "Graff Diamong Necklace", "One Of the rarest", 560, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.HOUR, 1);
            item = new Item("Vases & Pots", "White Porcelain Vase", "a piece of art, place your bidding now!", 450, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Vases & Pots", "Shibyama Vases", "2 Japanese Vases", 78, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 20);
            item = new Item("Vases & Pots", "Some Cool Vase", "an ancient Vase that you should start bidding on", 900, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Vases & Pots", "Gordon Pot", "From the movie lord of the rings", 213, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);
            item = new Item("Cloths", "A green dress", "used dress from the middle ages", 120, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Cloths", "Shoes", "Christiano Ronaldo's first Shoes", 1293, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Book cases", "3 shelves book case", "A very practical book case for a very cheep price", 8, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Cloths", "Jacket", "lke Nina Gomez' Jacked", 5000, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            ///

            item = new Item("Jewelry", "Lot of 13 bracelets", "Call us on +212328282 for more info", 13, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


            item = new Item("Jewelry", "Graff Diamong Necklace", "One Of the rarest", 560, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.HOUR, 1);
            item = new Item("Vases & Pots", "White Porcelain Vase", "a piece of art, place your bidding now!", 450, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Vases & Pots", "Shibyama Vases", "2 Japanese Vases", 78, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 20);
            item = new Item("Vases & Pots", "Some Cool Vase", "an ancient Vase that you should start bidding on", 900, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Vases & Pots", "Gordon Pot", "From the movie lord of the rings", 213, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 10);
            item = new Item("Automobiles", "Ferrari Xl23", "Feel The Real, used by doctors!", 45560, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Fasion", "Designer Custom Comforter Sham Pillows","Stunning Custom Brocade and Sateen/Silk Duvet 91x80 with 102x86 down comforter, matching bed skirt, Pillow shams with feather pillow and Accent Pillow. Used as display only. 22 pds. PROVENANCE: A Historic Charlotte Street Home, Charleston, SC", 23, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("posters", " Batman poster", "Last Chance by LiveAuctioneers! You don't want to miss this one.", 70, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, 20);
            item = new Item("Fasion", "Chanel Leather Briefcase", "2017 is here already!", 560, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Fasion", "Patek Philippe Cufflinks", "Condition reports are available upon request. Please email abanoub.keriackus@outlook.com to make a request.", 134, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Fashion", " Judith Leiber Swarovksi Crystal Mouse", "Last Chance by LiveAuctioneers! You don't want to miss this one.", 780, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 45);
            item = new Item("Fasion", "Hermes ms 50", "Bluemarin leather bag", 66, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Fasion", "Tuna fish Flip flops", "flip flops that smells like tuna fish", 3, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Fashion", " Judith Leiber Swarovksi Crystal Mouse", "Last Chance by LiveAuctioneers! You don't want to miss this one.", 780, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Collectibles", "Bowden spacelander Bicycle", "Copake Auction Inc. America!", 5000, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 10);
            item = new Item("collectibles", "MS Pacman", "One of the earliest Pacman Video game station", 330, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Cloths", "Shoes", "Christiano Ronaldo's first Shoes", 1293, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("collectibles", "Fantastic book of uniforms", "Feel the real history", 350, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);

            item = new Item("Collectibles", "Bowden spacelander Bicycle", "Copake Auction Inc. America!", 5000, c.getTimeInMillis());
            getDao(Item.class).createOrUpdate(item);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Account.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Map<Class, Dao> dateAccessObjects = new HashMap<>();


    public Dao<?, ?> getDao(Object data) throws SQLException {
        Class clazz = data.getClass();
        if (dateAccessObjects.get(clazz) == null) {
            dateAccessObjects.put(clazz, getDao(clazz));
        }
        return dateAccessObjects.get(clazz);
    }
}
