/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author puspaningtyas
 */
public class Document {

    private int id;
    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String[] getListofTerm() {
        String value = this.getContent();
        value = value.replaceAll("[.,?!]", "");
        return value.split(" ");
    }

    public ArrayList<Posting> getListofPosting() {
        //panggil dungsi getlistOfterm
        String tempString[] = getListofTerm();
        //buat objek arratlist<posting> result untuk menanmpung
        ArrayList<Posting> result = new ArrayList<Posting>();
        //buat looping sebanyak listofterm 
        for (int i = 0; i < tempString.length; i++) {
            //di dalam looping
            //jika term pertama maka
            if (i == 0) {
                //buat object termposting
                Posting temPosting = new Posting(tempString[0], this);
                //set atribut document
                //tambah ke arraylist result
                result.add(temPosting);
            } else {
                //lainnya
                //sortir arralist result
                Collections.sort(result);
                //cek apakah term sudah ada 
                //gunakan fungsi search dengan luaran indeks obyek
                //gunakan objek temPosting
                Posting temPosting = new Posting(tempString[i], this);
                int indexCari = Collections.binarySearch(result,temPosting);
                //jika hasil kurang dari(obyek tidak ada)
                if (indexCari < 0) {
                    //buat objek temposting
                    //tambahkan ke arraylist result
                    result.add(temPosting);
                }else{
                    int tempNumber =result.get(indexCari).getNumberOfTerm();
                }
            }

            
        }

        return result;
    }

}
