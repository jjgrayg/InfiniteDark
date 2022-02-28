package com.ExtendedDoc.HTMLHandlers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public final class ExtendedDocFactory extends ExtendedDoc {

	@Contract("_ -> new")
	public @NotNull ExtendedDoc createExtendedDoc(String filePath) {
		return new ExtendedDoc(filePath);
	}

	@Contract("_ -> new")
	public @NotNull ExtendedDoc createExtendedDoc(URL filePath) {
		return new ExtendedDoc(filePath);
	}

	public ExtendedDoc loadExtendedDoc(String filePath) {
		ExtendedDoc exDoc = new ExtendedDoc();
		try {
			exDoc = exDoc.createFromJson(filePath);
			return exDoc;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return exDoc;
	}

	public ExtendedDoc loadExtendedDoc(@NotNull URL filePath) {
		ExtendedDoc exDoc = new ExtendedDoc();
		try {
			exDoc = exDoc.createFromJson(filePath.getPath());
			return exDoc;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return exDoc;
	}

	public void saveExtendedDoc(@NotNull ExtendedDoc exDoc) {
		exDoc.saveAsJson();
	}
	public String getJsonExtendedDoc (@NotNull ExtendedDoc exDoc) {
		return exDoc.getJson();
	}

	public ExtendedDoc createFromJSONString(String json){
		ExtendedDoc doc = new ExtendedDoc();
		doc.createFromJsonString(json);
		return doc;
	}
}
