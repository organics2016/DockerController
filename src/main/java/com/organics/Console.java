package com.organics;

import com.organics.service.Service;

import java.io.IOException;

/**
 * Created by organics on 2016/3/12.
 */
public class Console {
    public static void main(String[] args) throws IOException {
//        DockerSearchStats service = new DockerSearchStats() {};
//        System.out.println(service.SearchAll());

        Service service = new Service();
        service.searchStats();
    }
}
