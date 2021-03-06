package io.xdag.xdagwallet.config;

import io.xdag.common.util.SPUtil;

/**
 * created by lxm on 2018/7/18.
 * <p>
 * desc : The config for xdag wallet
 */
public class Config {

    private static final String DEFAULT_POOL = "xdagscan.com:13654";
    public static final String CONFIG_KEY_POOL = "config_key_pool";
    private static final String CONFIG_KEY_IS_USER_BACKUP = "config_key_is_user_backup";
    private static final String CONFIG_KEY_NOT_SHOW_EXPLAIN = "config_key_not_show_explain";


    public static void setPoolAddress(String poolAddress) {
        SPUtil.putString(CONFIG_KEY_POOL, poolAddress);
    }


    public static String getPoolAddress() {
        return SPUtil.getString(CONFIG_KEY_POOL, DEFAULT_POOL);
    }

    public static void setUserBackup(boolean backup) {
        SPUtil.putBoolean(CONFIG_KEY_IS_USER_BACKUP, backup);
    }


    public static boolean isUserBackup() {
        return SPUtil.getBoolean(CONFIG_KEY_IS_USER_BACKUP, false);
    }


    public static void setNotShowExplain(boolean noShowSplash) {
        SPUtil.putBoolean(CONFIG_KEY_NOT_SHOW_EXPLAIN, noShowSplash);
    }


    public static boolean isNotShowExplain() {
        return SPUtil.getBoolean(CONFIG_KEY_NOT_SHOW_EXPLAIN, false);
    }

}
