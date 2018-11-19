package bean.pwr.imskamieskiego.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import java.io.InputStream;


import bean.pwr.imskamieskiego.data.LocalDB;
import bean.pwr.imskamieskiego.repository.FloorDataRepository;

public class FloorViewModel extends AndroidViewModel {
    private FloorDataRepository floorDataRepository;
    private int currentFloor=0;


    private MutableLiveData<Integer> selectedFloor = new MutableLiveData<>();
    private LiveData<InputStream> mapImageStream = Transformations.switchMap(selectedFloor,
            floor -> floorDataRepository.getMapImage(floor)
    );
    private LiveData<Bitmap> floorBitmap = Transformations.map(mapImageStream, stream ->
            stream != null ? BitmapFactory.decodeStream(stream) : null
    );

    public FloorViewModel(@NonNull Application application) {
        super(application);
        LocalDB localDB = LocalDB.getDatabase(application.getApplicationContext());
        floorDataRepository = new FloorDataRepository(localDB, application.getApplicationContext());
    }

    /**
     * Get currently selected floor number
     * @return selected floor number
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Returns live data which contains list of floor names
     * @return live data with strings array with floor names
     */
    public LiveData<String[]> getFloorsList(){
        return floorDataRepository.getFloorNames();
    }

    /**
     * Returns live data with bitmap of currently selected floor
     * @return live data with bitmap
     */
    public LiveData<Bitmap> getFloorBitmap() {
        return floorBitmap;
    }

    /**
     * Set floor as selected
     * @param floor number of selected floor
     */
    public void setSelectedFloor(int floor) {
        selectedFloor.postValue(floor);
        this.currentFloor = floor;
    }
}
