package id.ac.umn.w09_28917.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.umn.w09_28917.dao.MahasiswaDAO;
import id.ac.umn.w09_28917.models.Mahasiswa;
import id.ac.umn.w09_28917.room.MahasiswaRoomDatabase;

public class MahasiswaRepository {
    private MahasiswaDAO daoMahasiswa;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    public MahasiswaRepository(Application app) {
        MahasiswaRoomDatabase db = MahasiswaRoomDatabase.getDatabase(app);
        daoMahasiswa = db.daoMahasiswa();
        daftarMahasiswa = daoMahasiswa.getAllMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mhs) {
        new insertAsyncTask(daoMahasiswa).execute(mhs);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(daoMahasiswa).execute();
    }

    public void delete(Mahasiswa mhs) {
        new deleteAsyncTask(daoMahasiswa).execute(mhs);
    }

    public void update(Mahasiswa mhs) {
        new updateAsyncTask(daoMahasiswa).execute(mhs);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        insertAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.insert(mahasiswas[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAllAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDaoMahasiswa.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.delete(mahasiswas[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncDaoMahasiswa;
        updateAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.update(mahasiswas[0]);
            return null;
        }
    }
}
