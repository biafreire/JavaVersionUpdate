package br.bia.upgrade.objective.localization.bundles;

import java.util.ListResourceBundle;

/**
 * Using property files we were using classes
 * @author fabiana
 *
 */
public class MyBundle_es_ES extends ListResourceBundle {
	 @Override
	    protected Object[][] getContents() {
	        return new Object[][] {
	        	{ "s", "tío" }
	        };
	    }
}