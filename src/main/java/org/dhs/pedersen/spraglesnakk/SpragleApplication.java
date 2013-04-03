package org.dhs.pedersen.spraglesnakk;

import org.apache.wicket.Page;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.protocol.http.WebApplication;

public class SpragleApplication extends WebApplication {

	private EventBus eventBus;

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

    public static SpragleApplication get()
    {
        return (SpragleApplication)WebApplication.get();
    }
    
	@Override
	public void init() {
		super.init();
		eventBus = new EventBus(this);
	}
}
