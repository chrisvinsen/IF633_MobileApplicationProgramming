package id.ac.umn.w09_28917.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.umn.w09_28917.dao.MahasiswaDAO;
import id.ac.umn.w09_28917.models.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class MahasiswaRoomDatabase extends RoomDatabase {
    public abstract MahasiswaDAO daoMahasiswa();

    private static MahasiswaRoomDatabase INSTANCE;

    public static MahasiswaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MahasiswaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MahasiswaRoomDatabase.class,
                            "dbMahasiswa"
                    ).addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
}
