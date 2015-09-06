/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.service;

/**
 *
 * @author fabiano
 */
public interface IDummyComponent <K, T> {
    public String getValue(final K key, final T value);

    public String defaultValue(final K key, final T value);
}
