package com.msa.demo.web;

import java.util.Objects;

public class Constants {

	public static boolean DEVMODE =  Objects.equals(System.getProperty("devmode"), "true");
}
