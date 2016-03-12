package com.organics;

import com.organics.service.Search;

import java.io.IOException;

/**
 * Created by organics on 2016/3/12.
 */
public class Console {
    public static void main(String[] args) throws IOException {
//        DockerSearch getSearch = new DockerSearch() {};
//        System.out.println(getSearch.SearchAll());

        Search search = new Search();
        search.searchStats();
    }
}
