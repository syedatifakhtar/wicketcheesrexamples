package com.syedatifakhtar.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.syedatifakhtar.BasePage;

public class SaveMusicPage extends BasePage{
	private String resultMessage="Saved, Track: ";
	public SaveMusicPage(PageParameters pageParams) {
		super(pageParams);
		if(pageParams!=null)
		{
			String titleName=pageParams.get("titleName").toString();
			String authorName=pageParams.get("authorName").toString();
			if(titleName!=null && authorName!=null) {
				resultMessage	=	resultMessage + titleName + " By: " + authorName;
			}
		}
		add(new Label("resultMessage",resultMessage));
	}

}
