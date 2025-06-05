package com.github.simaodiazz.database.service.plugin.test;

import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.TestOnly;

@ApiStatus.Internal
@TestOnly
public final class ConnectionEstablishedTest {

	private final DataSourceExecutor executor;

	private ConnectionEstablishedTest(DataSourceExecutor executor) {
		this.executor = executor;
	}

	public static ConnectionEstablishedTest prepare(DataSourceExecutor executor) {
		return new ConnectionEstablishedTest(executor);
	}

	public void run() {
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GOLD + "[TEST] Trying to connect to database...");
		executor.write("SELECT 1");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[TEST] Connection established.");
	}
}
