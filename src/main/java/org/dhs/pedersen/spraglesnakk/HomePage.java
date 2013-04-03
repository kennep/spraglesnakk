package org.dhs.pedersen.spraglesnakk;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.Subscribe;
import org.apache.wicket.core.util.string.JavaScriptUtils;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public HomePage() {
		final TextField<String> typearea = new TextField<String>("typearea",
				new Model<String>(""));
		typearea.setOutputMarkupId(true);
		
		String usernameString = (String)Session.get().getAttribute("username");
		
		final HiddenField<String> username = new HiddenField<String>("username",
				new Model<String>(usernameString));
		
		Form<Void> form = new Form<Void>("form");
        add(form);
        form.add(typearea);
        form.add(username);
        form.add(new AjaxSubmitLink("send", form)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form)
            {
            	System.out.println("****DEBUG: onUpdate");
            	if(typearea.getModelObject() == null) {
            		return;
            	}
				ChatMessage message = new ChatMessage(username.getModelObject(),
						typearea.getModelObject());

				Session.get().setAttribute("username", username.getModelObject());
				typearea.setModelObject("");
				target.add(typearea);
				
				EventBus.get().post(message);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
            }
        });

        /*
		typearea.add(new AjaxFormComponentUpdatingBehavior("onkeydown") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				System.out.println("****DEBUG: onUpdate");
				ChatMessage message = new ChatMessage(typearea.getModelObject());

				typearea.setModelObject("");
				target.add(typearea);
				
				EventBus.get().post(message);
			}

			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
				super.updateAjaxAttributes(attributes);

				AjaxCallListener myAjaxCallListener = new AjaxCallListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public CharSequence getPrecondition(Component component) {
						return "return (attrs.event.keyCode == 13 && !attrs.event.shiftKey?true:false);";
					}
					
					@Override
					public CharSequence getBeforeSendHandler(Component component) {
						return "$(attrs.event.target).attr('disabled', 'disabled');";
					}
				};
				attributes.getAjaxCallListeners().add(myAjaxCallListener);
			}

		});*/
		
	}
	
	@Subscribe
    public void receiveMessage(AjaxRequestTarget target, ChatMessage message)
    {
		System.out.println("****DEBUG: Message: " + message.getMessage());

		String msgEscaped = escape(message.getMessage());
		String unameEscaped = escape(message.getUsername());
		
		String javascript = "var div=$('#chatHistory'); div.append('<div><b>" + unameEscaped + " sa:</b> " + msgEscaped + "</div>'); div.scrollTop(div[0].scrollHeight);";

		target.appendJavaScript(javascript);
    }
	
	private String escape(CharSequence input) {
		String msgEscaped = JavaScriptUtils.escapeQuotes(input).toString();
		msgEscaped = msgEscaped.replace("\n", "<br />\\n");
		msgEscaped = msgEscaped.replace("\r", "\\r");
		msgEscaped = StringEscapeUtils.escapeHtml4(msgEscaped);		
		return msgEscaped;
	}
	
}
