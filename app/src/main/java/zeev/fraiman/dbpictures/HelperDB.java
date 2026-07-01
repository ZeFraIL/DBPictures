package zeev.fraiman.dbpictures;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class HelperDB extends SQLiteOpenHelper {

    private static final String DBNAME = "places.db";

    public static final String PLACE_TABLE="Places";
    public static final String PLACE_COMMENT="About_place";
    public static final String PLACE_PICTURE="Place_picture";

    public HelperDB(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st1="CREATE TABLE IF NOT EXISTS "+PLACE_TABLE;
        st1+=" ( "+PLACE_COMMENT+" TEXT, ";
        st1+=PLACE_PICTURE+" BLOB );";
        db.execSQL(st1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
