package com.github.simaodiazz.database.service;

import com.github.simaodiazz.database.service.initializer.DatabaseServiceInitializer;
import com.github.simaodiazz.database.service.service.DatabaseService;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * No exposing a single instance of the DatabaseService for now
 */
public final class DatabaseServicePlugin extends JavaPlugin {

    private DatabaseService service;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        this.service = DatabaseServiceInitializer.initialize(this);
    }

    @Override
    public void onEnable() {
        this.getServer().getServicesManager().register(DatabaseService.class, this.service, this, ServicePriority.Highest); // Crucial to register the service
    }

    public DatabaseService getService() {
        return service;
    }
}
