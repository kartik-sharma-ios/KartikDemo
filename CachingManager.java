public class CachingManager {

    public static void saveAppContext(Application context) {

        ApplicationCache applicationCache = ApplicationCache.getInstance();
        applicationCache.setAppContext(context);
    }

    public static Context getAppContext() {

        ApplicationCache applicationCache = ApplicationCache.getInstance();
        return applicationCache.getAppContext();
    }

    public static void removeApplicationCache() {

        ApplicationCache.getInstance().deleteAppCache();
    }
}
