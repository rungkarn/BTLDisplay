/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.apress.proandroidmedia.ch1.cameraintent;

/**
 * <p>
 * </p><p>
 * Created on 11 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
class FruitItem {

    final String name;

  //  final int image;
    final String mUri;

//    FruitItem(String name, int image, String uri) {
    FruitItem(String name, String uri) {
        this.name = name;
       // this.image = image;
        this.mUri = uri;
    }

}
