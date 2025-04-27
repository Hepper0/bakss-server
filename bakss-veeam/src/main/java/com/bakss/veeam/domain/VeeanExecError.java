package com.bakss.veeam.domain;

public class VeeanExecError extends Exception{
    public VeeanExecError(String error) {
        super(error);
    }
}
