package com.github.simaodiazz.database.service.plugin.test;

import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.TestOnly;

@ApiStatus.Internal
@TestOnly
public final class CreatingDemoTableTest {

	private final DataSourceExecutor executor;

	private CreatingDemoTableTest(DataSourceExecutor executor) {
		this.executor = executor;
	}

	public static CreatingDemoTableTest prepare(DataSourceExecutor executor) {
		return new CreatingDemoTableTest(executor);
	}

	public void run() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TEST] Creating demo table...");
		executor.write("CREATE TABLE IF NOT EXISTS demo (id INT)");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[TEST] Demo table created.");
	}
}
