package com.khattab.softxperttask.common.aPIsUtils;

public class InjectorUtils {

    public static TaskClientWebService provideWinchWebService() {
        TaskClientWebService webServiceClient = WebServiceClient.getClient().create(TaskClientWebService.class);
        return webServiceClient;
    }

}