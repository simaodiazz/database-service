package com.github.simaodiazz.database.service.plugin.test;

import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.TestOnly;

@ApiStatus.Internal
@TestOnly
public final class DropDemoTableAsyncTest {

	private final DataSourceExecutor executor;

	private DropDemoTableAsyncTest(DataSourceExecutor executor) {
		this.executor = executor;
	}

	public static DropDemoTableAsyncTest prepare(DataSourceExecutor executor) {
		return new DropDemoTableAsyncTest(executor);
	}

	public void run() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TEST] Dropping demo table...");
		executor.writeAsync("DROP TABLE IF EXISTS demo");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GREEN + "[TEST] Demo table dropped successfully.");
	}
}
