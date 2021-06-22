package com.example.carpartsapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CarRepository {

    private MutableLiveData<List<Car>> searchModels =
            new MutableLiveData<>();
    private LiveData<List<Car>> allCars;
    private CarDao carDao;
    private CarPartDao carPartDao;

    public LiveData<List<Car>> getAllCars() {
        return allCars;
    }

    public MutableLiveData<List<Car>> getSearchResults() {
        return searchModels;
    }

    public CarRepository(Application application) {
        CarRoomDatabase db = CarRoomDatabase.getDatabase(application);
        carDao = db.carDao();
        allCars = carDao.getAllCars();
    }

    public void InsertCar(Car newCar) { new InsertAsyncTask(carDao).execute(newCar); }

    public void UpdateCar(Car updateCar) { new UpdateCarAsyncTask(carDao).execute(updateCar);}

    public void DeleteCar(Car deleteCar) {new DeleteCarAsyncTask(carDao).execute(deleteCar);}

    public void findCarModel(String model) {
        QueryAsyncTask task = new QueryAsyncTask(carDao);
        task.delegate = this;
        task.execute(model);
    }

    private void asyncFinished(List<Car> results) {
        searchModels.setValue(results);
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Car>> {

        private CarDao asyncTaskDao;
        private CarRepository delegate = null;

        QueryAsyncTask(CarDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Car> doInBackground(final String... params) {
            return asyncTaskDao.findCar(params[0]);
        }

        @Override
        protected void onPostExecute(List<Car> result) {
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Car, Void, Void> {

        private CarDao asyncTaskDao;

        InsertAsyncTask(CarDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Car... params) {
            asyncTaskDao.InsertCar(params[0]);
            return null;
        }
    }

    private static class UpdateCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private CarDao asyncTaskDao;

        private UpdateCarAsyncTask(CarDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Car... params) {
            // below line is use to update
            // our modal in dao.
            asyncTaskDao.UpdateCar(params[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private CarDao asyncTaskDao;

        private DeleteCarAsyncTask(CarDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Car... params) {
            // below line is use to delete
            // our course modal in dao.
            asyncTaskDao.DeleteCar(params[0]);
            return null;
        }
    }
}
