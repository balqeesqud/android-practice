package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dao.TaskDAO;
import model.Task;


@Database(entities = {Task.class}, version = 3)
public abstract class TaskDatabase extends RoomDatabase {
      public abstract TaskDAO taskDAO();
}
