package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 *
 * @author admin
 */
public class InvertedIndex {

    private ArrayList<Document> listOfDocument = new ArrayList<Document>();
    private ArrayList<Term> dictionary = new ArrayList<Term>();

    public InvertedIndex() {
    }

    public void addNewDocument(Document document) {
        listOfDocument.add(document);
    }

    public ArrayList<Posting> getUnsortedPostingList() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < listOfDocument.size(); i++) {
            // buat listOfTerm dari document ke -i
            String[] termResult = listOfDocument.get(i).getListofTerm();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < termResult.length; j++) {
                // buat object tempPosting
                Posting tempPosting = new Posting(termResult[j],
                        listOfDocument.get(i));
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getSortedPostingList() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // panggil list yang belum terurut
        list = this.getUnsortedPostingList();
        // urutkan
        Collections.sort(list);
        return list;
    }

    public void makeDictionary() {
        //buat posting list term terurut 

        ArrayList<Posting> list = getSortedPostingList();
        //looping buat list of term atau dictionary
        for (int i = 0; i < list.size(); i++) {
            //cek dictionary kosong atau tidak
            if (dictionary.isEmpty()) {
                //buat term
                Term term = new Term(list.get(i).getTerm());
                //tambah postinglist untuk term
                term.getPostingList().add(list.get(i));

            } else {
                //dictionary sudah ada isinya
                Term tempTerm = new Term(list.get(i).getTerm());
                //perbandingan apakah term sudah ada apa belum ?
                //luaran dari binarusearch adalah posisi
                int position = Collections.binarySearch(dictionary, tempTerm);
                if (position < 0) {
                    //term baru

                    //tambah posting list ke term
                    tempTerm.getPostingList().add(list.get(i));
                    //tambah term ke dictionary
                    dictionary.add(tempTerm);
                } else {
                    //term ada
                    //tambahkan posting list saja dari existing term
                    dictionary.get(position).getPostingList().add(list.get(i));
                    //urutkan posting listnya
                    Collections.sort(dictionary.get(position).getPostingList());
                }
                //urutkan term dictionary
                Collections.sort(dictionary);

            }

        }
    }
}
