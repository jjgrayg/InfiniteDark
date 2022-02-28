package com.InfiniteDark.controllers;

import com.InfiniteDark.handlers.StageBehaviorHandler;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@PostMapping("/advanceStage1")
	public void advanceStage1(@CookieValue(name = "sessionID", defaultValue = "") String sessionID){
		StageBehaviorHandler.updateToStage1(sessionID);
	}
}
